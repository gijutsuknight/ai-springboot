package ai.test.java.controller;

import ai.test.java.dto.TestRequest;
import ai.test.java.dto.TestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
@Tag(name = "Test API", description = "Endpoints for testing GET and POST")
public class TestController {

    @GetMapping
    @Operation(summary = "Get hello message", description = "Returns a simple greeting message")
    public ResponseEntity<String> getHello() {
        return ResponseEntity.ok("Hello from ai-springboot!");
    }

    @PostMapping
    @Operation(summary = "Post test payload", description = "Accepts a JSON body and returns an echo response")
    public ResponseEntity<TestResponse> postTest(@RequestBody TestRequest request) {
        String message = request.getMessage() != null ? request.getMessage() : "(no message)";
        TestResponse response = new TestResponse("Received: " + message, true);
        return ResponseEntity.ok(response);
    }
}
