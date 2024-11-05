package tecazuay.edu.ec.encryption_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tecazuay.edu.ec.encryption_service.service.Impl.SymmetricEncryptionServiceImpl;

import java.lang.reflect.Field;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class SymmetricEncryptionServiceImplTest {

    private SymmetricEncryptionServiceImpl symmetricEncryptionService;

    @BeforeEach
    public void setUp() {
        symmetricEncryptionService = new SymmetricEncryptionServiceImpl();
    }

    private void setPrivateField(Object object, String fieldName, Object value) {
        try {
            Field field = object.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(object, value);
        } catch (Exception e) {
            throw new RuntimeException("Error setting private field", e);
        }
    }

    @Test
    public void testEncryptDecryptWith128BitKey() throws Exception {
        String keyStr = "MTIzNDU2Nzg5MDEyMzQ1Ng=="; // 128-bit key (16 bytes)
        setPrivateField(symmetricEncryptionService, "key", new String(Base64.getDecoder().decode(keyStr)));

        String originalText = "Hello, World!";
        String encryptedText = symmetricEncryptionService.encrypt(originalText);
        String decryptedText = symmetricEncryptionService.decrypt(encryptedText);

        assertEquals(originalText, decryptedText);
    }

    @Test
    public void testEncryptDecryptWith192BitKey() throws Exception {
        String keyStr = "MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0"; // 192-bit key (24 bytes)
        setPrivateField(symmetricEncryptionService, "key", new String(Base64.getDecoder().decode(keyStr)));

        String originalText = "Hello, World!";
        String encryptedText = symmetricEncryptionService.encrypt(originalText);
        String decryptedText = symmetricEncryptionService.decrypt(encryptedText);

        assertEquals(originalText, decryptedText);
    }


    @Test
    public void testEncryptDecryptWith256BitKey() throws Exception {
        String keyStr = "1DIqv/29z46jVKZa9erLRLLKdveqpt1chFwHm5Za1eA="; // 256-bit key (32 bytes)
        setPrivateField(symmetricEncryptionService, "key", keyStr);

        String originalText = "Hello, World!";
        String encryptedText = symmetricEncryptionService.encrypt(originalText);
        String decryptedText = symmetricEncryptionService.decrypt(encryptedText);

        assertEquals(originalText, decryptedText);
    }

    @Test
    public void testInvalidKeyLength() throws Exception {
        String keyStr = "MTIzNDU2Nzg5"; // Invalid key length (12 bytes)
        setPrivateField(symmetricEncryptionService, "key", new String(Base64.getDecoder().decode(keyStr)));

        assertThrows(RuntimeException.class, () -> {
            symmetricEncryptionService.encrypt("Hello, World!");
        });
    }
}