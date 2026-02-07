package ai.test.java.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request body for the test POST endpoint")
public class TestRequest {

    @Schema(description = "Message to echo back", example = "Hello from client")
    private String message;

    public TestRequest() {
    }

    public TestRequest(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
