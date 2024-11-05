package tecazuay.edu.ec.encryption_service.service;

public interface HybridEncryptionService {

    public String encrypt(String plainText);
    public String decrypt(String encryptedText);
}
