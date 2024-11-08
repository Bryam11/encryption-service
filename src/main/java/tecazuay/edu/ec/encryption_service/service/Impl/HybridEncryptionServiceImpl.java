package tecazuay.edu.ec.encryption_service.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tecazuay.edu.ec.encryption_service.service.HybridEncryptionService;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class HybridEncryptionServiceImpl implements HybridEncryptionService {

    private static final String ASYMMETRIC_ALGORITHM = "RSA";
    private static final String SYMMETRIC_ALGORITHM = "AES";

    @Value("${encryption.public-key}")
    private String publicKeyStr;

    @Value("${encryption.private-key}")
    private String privateKeyStr;

    @Override
    public String encrypt(String plainText) {
        try {
            // Generacion de clave simetrica
            KeyGenerator keyGen = KeyGenerator.getInstance(SYMMETRIC_ALGORITHM);
            keyGen.init(256);
            SecretKey symmetricKey = keyGen.generateKey();

            // Encriptar el texto plano usando la clave simetrica
            Cipher symmetricCipher = Cipher.getInstance(SYMMETRIC_ALGORITHM);
            symmetricCipher.init(Cipher.ENCRYPT_MODE, symmetricKey);
            byte[] encryptedData = symmetricCipher.doFinal(plainText.getBytes());

            // Encriptar la clave simetrica usando la clave publica
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyStr);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance(ASYMMETRIC_ALGORITHM);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            Cipher asymmetricCipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM);
            asymmetricCipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedKey = asymmetricCipher.doFinal(symmetricKey.getEncoded());

            // Combinar la clave simetrica encriptada y los datos encriptados
            byte[] combined = new byte[encryptedKey.length + encryptedData.length];
            System.arraycopy(encryptedKey, 0, combined, 0, encryptedKey.length);
            System.arraycopy(encryptedData, 0, combined, encryptedKey.length, encryptedData.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }

    @Override
    public String decrypt(String encryptedText) {
        try {
            byte[] combined = Base64.getDecoder().decode(encryptedText);

            // Separar la clave simetrica encriptada y los datos encriptados
            byte[] encryptedKey = new byte[256]; // Assuming RSA 2048-bit key
            byte[] encryptedData = new byte[combined.length - 256];
            System.arraycopy(combined, 0, encryptedKey, 0, 256);
            System.arraycopy(combined, 256, encryptedData, 0, encryptedData.length);

            // Desencriptar la clave simetrica usando la clave privada
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(ASYMMETRIC_ALGORITHM);
            PrivateKey privateKey = keyFactory.generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));

            Cipher asymmetricCipher = Cipher.getInstance(ASYMMETRIC_ALGORITHM);
            asymmetricCipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] symmetricKeyBytes = asymmetricCipher.doFinal(encryptedKey);
            SecretKey symmetricKey = new SecretKeySpec(symmetricKeyBytes, SYMMETRIC_ALGORITHM);

            // Desencriptar los datos usando la clave simetrica
            Cipher symmetricCipher = Cipher.getInstance(SYMMETRIC_ALGORITHM);
            symmetricCipher.init(Cipher.DECRYPT_MODE, symmetricKey);
            byte[] decryptedData = symmetricCipher.doFinal(encryptedData);

            return new String(decryptedData);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }
}