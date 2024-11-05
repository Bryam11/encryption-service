package tecazuay.edu.ec.encryption_service.resources.record;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RequestEncryption(
        @Schema(description = "Data to encrypt", example = "Hello World")
        @NotNull(message = "Data is required")
        String data
) {
}
