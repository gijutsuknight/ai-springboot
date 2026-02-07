package ai.test.java.controller;

import ai.test.java.dto.ImageUploadResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@RestController
@RequestMapping("/api/upload")
@Tag(name = "File Upload", description = "Upload image or any file")
public class ImageUploadController {

    private static final Set<String> ALLOWED_IMAGE_TYPES = Set.of(
            "image/jpeg", "image/png", "image/gif", "image/webp", "image/bmp"
    );

    private final Path imageUploadDir;
    private final Path fileUploadDir;

    public ImageUploadController() {
        this.imageUploadDir = Paths.get("uploads", "images").toAbsolutePath().normalize();
        this.fileUploadDir = Paths.get("uploads", "files").toAbsolutePath().normalize();
    }

    @PostMapping(value = "/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload an image", description = "Accepts a multipart image file (JPEG, PNG, GIF, WebP, BMP)")
    public ResponseEntity<ImageUploadResponse> uploadImage(
            @Parameter(description = "Image file", required = true, schema = @Schema(type = "string", format = "binary"))
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ImageUploadResponse.failure("No file provided"));
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_IMAGE_TYPES.contains(contentType.toLowerCase())) {
            return ResponseEntity.badRequest()
                    .body(ImageUploadResponse.failure(
                            "Invalid file type. Allowed: JPEG, PNG, GIF, WebP, BMP. Got: " + contentType));
        }

        try {
            Files.createDirectories(imageUploadDir);
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isBlank()) {
                originalFilename = "image." + contentType.split("/")[1];
            }
            Path targetFile = imageUploadDir.resolve(sanitizeFileName(originalFilename));
            file.transferTo(targetFile.toFile());

            return ResponseEntity.ok(ImageUploadResponse.success(
                    originalFilename,
                    file.getSize(),
                    contentType));
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(ImageUploadResponse.failure("Upload failed: " + e.getMessage()));
        }
    }

    @PostMapping(value = "/file", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload any file", description = "Accepts a multipart file of any type and saves it")
    public ResponseEntity<ImageUploadResponse> uploadFile(
            @Parameter(description = "File to upload", required = true, schema = @Schema(type = "string", format = "binary"))
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(ImageUploadResponse.failure("No file provided"));
        }

        try {
            Files.createDirectories(fileUploadDir);
            String originalFilename = file.getOriginalFilename();
            if (originalFilename == null || originalFilename.isBlank()) {
                originalFilename = "upload_" + System.currentTimeMillis();
            }
            Path targetFile = fileUploadDir.resolve(sanitizeFileName(originalFilename));
            file.transferTo(targetFile.toFile());

            String contentType = file.getContentType() != null ? file.getContentType() : "application/octet-stream";
            return ResponseEntity.ok(ImageUploadResponse.fileSuccess(
                    originalFilename,
                    file.getSize(),
                    contentType));
        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body(ImageUploadResponse.failure("Upload failed: " + e.getMessage()));
        }
    }

    private static String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
