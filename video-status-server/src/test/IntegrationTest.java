package com.example.video_status_server;

import com.example.video_status_client.VideoStatusClient;
import com.example.video_status_client.VideoStatusClientImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationTest {

    @Test
    public void testVideoStatusClient() {
        // Initialize the client with the correct constructor
        VideoStatusClient client = new VideoStatusClientImpl(
                "http://localhost:8080", // Base URL
                5                        // Max retries
        );

        try {
            // Fetch the final job status
            String status = client.fetchJobStatus();

            // Log the status
            System.out.println("Final job status: " + status);

            // Assert valid status
            assertTrue(status.equals("completed") || status.equals("error"), "Invalid job status received.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Integration test failed: " + e.getMessage());
        }
    }
}
