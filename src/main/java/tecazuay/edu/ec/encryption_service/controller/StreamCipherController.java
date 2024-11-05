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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tecazuay.edu.ec.encryption_service.resources.record.RequestEncryption;
import tecazuay.edu.ec.encryption_service.service.StreamCipherService;

import java.util.Base64;

@RestController
@Description("Controller for Stream Cipher")
@Tag(name = "stream-cipher")
@RequestMapping("/stream-cipher")
@RequiredArgsConstructor
public class StreamCipherController {

    private final StreamCipherService streamCipherService;

    @PostMapping(value = "/encrypt", produces = "application/json")
    @Operation(summary = "Encrypt data", description = "Encrypt data using stream cipher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = byte[].class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})
    })
    public byte[] encrypt(@Valid @RequestBody RequestEncryption requestEncryption) {
        return streamCipherService.encrypt(Base64.getDecoder().decode(requestEncryption.data()));
    }

    @PostMapping(value = "/decrypt", produces = "application/json")
    @Operation(summary = "Decrypt data", description = "Decrypt data using stream cipher")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = byte[].class))}),
            @ApiResponse(responseCode = "500", description = "Internal error", content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = String.class))})
    })
    public byte[] decrypt(@Valid @RequestBody RequestEncryption requestEncryption) {
        return streamCipherService.decrypt(Base64.getDecoder().decode(requestEncryption.data()));
    }
}
