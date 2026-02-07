package ai.test.java.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Response from the test POST endpoint")
public class TestResponse {

    @Schema(description = "Echoed or processed message")
    private String message;

    @Schema(description = "Whether the request was received successfully")
    private boolean success;

    public TestResponse() {
    }

    public TestResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
