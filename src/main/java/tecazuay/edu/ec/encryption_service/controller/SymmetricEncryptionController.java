package tecazuay.edu.ec.encryption_service.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tecazuay.edu.ec.encryption_service.resources.record.RequestEncryption;
import tecazuay.edu.ec.encryption_service.service.SymmetricEncryptionService;

@RestController
@Description("Controller for Symmetric Encryption")
@Tag(name = "symmetric-encryption")
@RequiredArgsConstructor
public class SymmetricEncryptionController {

    private final SymmetricEncryptionService symmetricEncryptionService;

    @PostMapping(value = "/encrypt", produces = "application/json")
    @Operation(summary = "Encrypt data", description = "Encrypt data using symmetric encryption")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> encrypt(@Valid @RequestBody RequestEncryption requestEncryption) {
        return ResponseEntity.ok(symmetricEncryptionService.encrypt(requestEncryption.data()));
    }

    @PostMapping(value = "/decrypt", produces = "application/json")
    @Operation(summary = "Decrypt data", description = "Decrypt data using symmetric encryption")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> decrypt(@Valid @RequestBody RequestEncryption requestEncryption) {
        return ResponseEntity.ok(symmetricEncryptionService.decrypt(requestEncryption.data()));
    }
}
