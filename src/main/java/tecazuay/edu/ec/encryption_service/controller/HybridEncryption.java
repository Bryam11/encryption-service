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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tecazuay.edu.ec.encryption_service.resources.record.RequestEncryption;
import tecazuay.edu.ec.encryption_service.service.HybridEncryptionService;

@RestController
@Description("Controller for Hybrid Encryption")
@Tag(name = "hybrid-encryption")
@RequestMapping("/hybrid-encryption")
@RequiredArgsConstructor
public class HybridEncryption {

    private final HybridEncryptionService hybridEncryptionService;

    @PostMapping(value = "/encrypt", produces = "application/json")
    @Operation(summary = "Encrypt text", description = "Encrypt text using hybrid encryption")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> encrypt(@Valid @RequestBody RequestEncryption requestEncryption) {
        return ResponseEntity.ok(hybridEncryptionService.encrypt(requestEncryption.data()));
    }

    @PostMapping(value = "/decrypt", produces = "application/json")
    @Operation(summary = "Decrypt text", description = "Decrypt text using hybrid encryption")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> decrypt(@Valid @RequestBody RequestEncryption requestEncryption) {
        return ResponseEntity.ok(hybridEncryptionService.decrypt(requestEncryption.data()));
    }
}
