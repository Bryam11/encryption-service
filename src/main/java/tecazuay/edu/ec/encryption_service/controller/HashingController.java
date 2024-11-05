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
import tecazuay.edu.ec.encryption_service.resources.record.RequestHashEncryption;
import tecazuay.edu.ec.encryption_service.service.HashingService;

@RestController
@Description("Controller for Hashing")
@Tag(name = "hashing")
@RequestMapping("/hashing")
@RequiredArgsConstructor
public class HashingController {

    private final HashingService hashingService;

    @PostMapping(value = "/hash", produces = "application/json")
    @Operation(summary = "Hash text", description = "Hash text using SHA-256")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})
    })
    public ResponseEntity<?> hash(@Valid @RequestBody RequestEncryption requestEncryption) {
        return ResponseEntity.ok(hashingService.hash(requestEncryption.data()));
    }


    @PostMapping(value = "/validate", produces = "application/json")
    @Operation(summary = "Validate hash", description = "Validate hash of a string using SHA-256")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Boolean.class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Boolean.class))})
    })
    public ResponseEntity<?> validate(@Valid @RequestBody RequestHashEncryption requestHashEncryption) {
        return ResponseEntity.ok(hashingService.verify(requestHashEncryption.plainText(), requestHashEncryption.hash()));
    }
}
