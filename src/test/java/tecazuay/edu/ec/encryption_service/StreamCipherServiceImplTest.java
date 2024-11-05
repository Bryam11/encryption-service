package tecazuay.edu.ec.encryption_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import tecazuay.edu.ec.encryption_service.service.Impl.StreamCipherServiceImpl;

import java.lang.reflect.Field;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StreamCipherServiceImplTest {

    private StreamCipherServiceImpl streamCipherService;

    @Value("${encryption.iv}")
    private String ivStr;

    @BeforeEach
    public void setUp() throws Exception {
        streamCipherService = new StreamCipherServiceImpl();
        setPrivateField(streamCipherService, "iv", Base64.getDecoder().decode(ivStr));
    }

    private void setPrivateField(Object object, String fieldName, Object value) throws Exception {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    @Test
    public void testEncryptDecryptWith128BitKey() throws Exception {
        String keyStr = "MTIzNDU2Nzg5MDEyMzQ1Ng=="; // 128-bit key (16 bytes)
        setPrivateField(streamCipherService, "key", Base64.getDecoder().decode(keyStr));
        streamCipherService.validateKeyLength(Base64.getDecoder().decode(keyStr));

        byte[] data = "Hello, World!".getBytes();
        byte[] encryptedData = streamCipherService.encrypt(data);
        byte[] decryptedData = streamCipherService.decrypt(encryptedData);

        assertArrayEquals(data, decryptedData);
    }

    @Test
    public void testEncryptDecryptWith192BitKey() throws Exception {
        String keyStr = "MTIzNDU2Nzg5MDEyMzQ1Njc4OTAxMjM0"; // Corrected 192-bit key (24 bytes)
        setPrivateField(streamCipherService, "key", Base64.getDecoder().decode(keyStr));
        streamCipherService.validateKeyLength(Base64.getDecoder().decode(keyStr));

        byte[] data = "Hello, World!".getBytes();
        byte[] encryptedData = streamCipherService.encrypt(data);
        byte[] decryptedData = streamCipherService.decrypt(encryptedData);

        assertArrayEquals(data, decryptedData);
    }

    @Test
    public void testEncryptDecryptWith256BitKey() throws Exception {
        String keyStr = "YotQFFtbd+8+s6f8kmFfvdszMg4z7volek/eAaEBcYM="; // 256-bit key (32 bytes)
        setPrivateField(streamCipherService, "key", Base64.getDecoder().decode(keyStr));
        streamCipherService.validateKeyLength(Base64.getDecoder().decode(keyStr));

        byte[] data = "Hello, World!".getBytes();
        byte[] encryptedData = streamCipherService.encrypt(data);
        byte[] decryptedData = streamCipherService.decrypt(encryptedData);

        assertArrayEquals(data, decryptedData);
    }

    @Test
    public void testInvalidKeyLength() throws Exception {
        String keyStr = "MTIzNDU2Nzg5"; // Invalid key length (12 bytes)
        setPrivateField(streamCipherService, "key", Base64.getDecoder().decode(keyStr));

        assertThrows(RuntimeException.class, () -> {
            streamCipherService.validateKeyLength(Base64.getDecoder().decode(keyStr));
        });
    }
}