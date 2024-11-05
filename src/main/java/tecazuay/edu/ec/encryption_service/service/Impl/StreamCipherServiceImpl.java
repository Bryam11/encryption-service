package tecazuay.edu.ec.encryption_service.service.Impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tecazuay.edu.ec.encryption_service.service.StreamCipherService;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class StreamCipherServiceImpl implements StreamCipherService {

    private static final String ALGORITHM = "AES/CTR/NoPadding";
    private static final int[] VALID_KEY_LENGTHS = {16, 24, 32}; // Valid AES key lengths in bytes

    @Value("${encryption.aes-key}")
    private String keyStr;

    @Value("${encryption.iv}")
    private String ivStr;

    private byte[] key;
    private byte[] iv;

    @PostConstruct
    public void init() {
        this.key = Base64.getDecoder().decode(keyStr);
        this.iv = Base64.getDecoder().decode(ivStr);
        validateKeyLength(this.key);
    }

    public void validateKeyLength(byte[] keyBytes) {
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
    public byte[] encrypt(byte[] data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error while encrypting", e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new RuntimeException("Error while decrypting", e);
        }
    }
}