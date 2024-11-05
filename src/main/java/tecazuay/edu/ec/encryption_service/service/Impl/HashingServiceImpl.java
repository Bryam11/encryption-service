package tecazuay.edu.ec.encryption_service.service.Impl;

import org.springframework.stereotype.Service;
import tecazuay.edu.ec.encryption_service.service.HashingService;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class HashingServiceImpl implements HashingService {

    private static final String HASH_ALGORITHM = "SHA-256";

    @Override
    public String hash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            byte[] hashBytes = digest.digest(text.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error while hashing", e);
        }
    }

    @Override
    public boolean verify(String text, String hashedText) {
        String newHash = hash(text);
        return newHash.equals(hashedText);
    }
}
