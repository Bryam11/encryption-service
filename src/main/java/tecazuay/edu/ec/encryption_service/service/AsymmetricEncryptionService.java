package tecazuay.edu.ec.encryption_service.service;

public interface AsymmetricEncryptionService {

    String encrypt(String plainText);

    String decrypt(String cipherText);
}
