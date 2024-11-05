package tecazuay.edu.ec.encryption_service.service;

public interface StreamCipherService {

     byte[] encrypt(byte[] data);
     byte[] decrypt(byte[] data);
}
