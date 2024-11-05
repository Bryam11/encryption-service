package tecazuay.edu.ec.encryption_service.service;

public interface HashingService {

    String hash(String text);

    boolean verify(String text, String hashedText);
}
