package com.example.video_status_client;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Implementation of the Video Status Client.
 */
public class VideoStatusClientImpl implements VideoStatusClient {

    private final WebClient webClient;
    private final int maxRetries;

    public VideoStatusClientImpl(String baseUrl, int maxRetries) {
        this.webClient = WebClient.create(baseUrl);
        this.maxRetries = maxRetries;
    }

    @Override
    public String fetchJobStatus() throws VideoStatusException {
        for (int retries = 0; retries < maxRetries; retries++) {
            try {
                // Fetch the status using WebClient
                Mono<String> responseMono = webClient.get()
                        .uri("/status")
                        .retrieve()
                        .bodyToMono(String.class);

                // Subscribe to the response asynchronously
                String status = responseMono.block();
                if (status.startsWith("data:")) {
                    status = status.substring(5).trim(); // Remove "data:" prefix
                }

                System.out.println("Received status: " + status);

                // Return final status if it's terminal
                if ("completed".equalsIgnoreCase(status) || "error".equalsIgnoreCase(status)) {
                    return status;
                }

                // Sleep between retries
                Thread.sleep(1000); // 1 second delay
            } catch (Exception e) {
                throw new VideoStatusException("Error fetching job status: " + e.getMessage(), e);
            }
        }

        throw new VideoStatusException("Max retries exceeded");
    }
}
