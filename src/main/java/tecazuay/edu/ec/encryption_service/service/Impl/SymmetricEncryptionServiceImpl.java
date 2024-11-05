package tecazuay.edu.ec.encryption_service.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tecazuay.edu.ec.encryption_service.service.SymmetricEncryptionService;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class SymmetricEncryptionServiceImpl implements SymmetricEncryptionService {

    private static final String ALGORITHM = "AES";
    private static final int[] VALID_KEY_LENGTHS = {16, 24, 32}; // Valid AES key lengths in bytes

    @Value("${encryption.aes-key}")
    private String key;

    private void validateKeyLength(byte[] keyBytes) {
        boolean isValidLength = false;
        for (int length : VALID_KEY_LENGTHS) {
            if (keyBytes.length == length) {
                isValidLength = true;
                break;
            }
        }
        if (!isValidLength) {
            throw new RuntimeException("Invalid AES key length: " + keyBytes.length + " bytes. Key must be 16, 24, or 32 bytes.");
        }
    }

    @Override
    public String encrypt(String plainText) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            validateKeyLength(keyBytes);
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }

    @Override
    public String decrypt(String cipherText) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(key);
            validateKeyLength(keyBytes);
            SecretKeySpec secretKey = new SecretKeySpec(keyBytes, ALGORITHM);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decodedBytes = Base64.getDecoder().decode(cipherText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }
}