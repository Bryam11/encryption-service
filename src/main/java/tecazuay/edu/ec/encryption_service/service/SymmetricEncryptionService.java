package tecazuay.edu.ec.encryption_service.service;

public interface SymmetricEncryptionService {

    public String encrypt(String plainText);

    public String decrypt(String cipherText);
}
