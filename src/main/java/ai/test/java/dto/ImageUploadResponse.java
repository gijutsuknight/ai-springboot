package ai.test.java.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response after uploading an image")
public class ImageUploadResponse {

    @Schema(description = "Original filename")
    private String fileName;

    @Schema(description = "File size in bytes")
    private long size;

    @Schema(description = "Content type (e.g. image/png)")
    private String contentType;

    @Schema(description = "Whether the upload succeeded")
    private boolean success;

    @Schema(description = "Message or error description")
    private String message;

    public ImageUploadResponse() {
    }

    public ImageUploadResponse(String fileName, long size, String contentType, boolean success, String message) {
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.success = success;
        this.message = message;
    }

    public static ImageUploadResponse success(String fileName, long size, String contentType) {
        return new ImageUploadResponse(fileName, size, contentType, true, "Image uploaded successfully");
    }

    public static ImageUploadResponse fileSuccess(String fileName, long size, String contentType) {
        return new ImageUploadResponse(fileName, size, contentType, true, "File uploaded successfully");
    }

    public static ImageUploadResponse failure(String message) {
        return new ImageUploadResponse(null, 0, null, false, message);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
