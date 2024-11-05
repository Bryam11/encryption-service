package tecazuay.edu.ec.encryption_service.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Slf4j
public class KeyGeneratorUtil {

    private static final String ASYMMETRIC_ALGORITHM = "RSA";
    private static final String SYMMETRIC_ALGORITHM = "AES";
    private static final int KEY_SIZE = 256; // You can change this to 128 or 192 for different key sizes
    private static final int IV_SIZE = 16; // AES block size is 16 bytes

    public static void main(String[] args) {
        try {
            // Generate RSA key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ASYMMETRIC_ALGORITHM);
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.generateKeyPair();

            // Get the public key
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            String publicKeyBase64 = Base64.getEncoder().encodeToString(publicKeyBytes);
            System.out.println("Public Key: " + publicKeyBase64);

            // Get the private key
            byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
            String privateKeyBase64 = Base64.getEncoder().encodeToString(privateKeyBytes);
            System.out.println("Private Key: " + privateKeyBase64);

            // Generate AES key of 256 bits
            KeyGenerator aesKeyGen = KeyGenerator.getInstance(SYMMETRIC_ALGORITHM);
            aesKeyGen.init(128); // You can change this to 128 or 192 for different key sizes
            SecretKey aesKey = aesKeyGen.generateKey();
            String aesKeyBase64 = Base64.getEncoder().encodeToString(aesKey.getEncoded());
            System.out.println("AES Key (256 bits): " + aesKeyBase64);


            // Generate IV
            byte[] iv = new byte[IV_SIZE];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            String ivBase64 = Base64.getEncoder().encodeToString(iv);
            System.out.println("IV: " + ivBase64);

        } catch (NoSuchAlgorithmException e) {
            log.error("Error while generating keys", e);
        }
    }
}