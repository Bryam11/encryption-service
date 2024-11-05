package tecazuay.edu.ec.encryption_service.resources.record;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record RequestHashEncryption(
        @Schema(description = "Data to hash", example = "Hello World")
        @NotNull(message = "Data is required")
        String plainText,

        @Schema(description = "Hash to validate", example = "Hello World")
        @NotNull(message = "Hash is required")
        String hash
) {
}
