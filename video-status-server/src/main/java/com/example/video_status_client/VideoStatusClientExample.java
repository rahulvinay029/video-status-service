package com.example.video_status_client;

import reactor.netty.http.HttpResources;

public class VideoStatusClientExample {

    public static void main(String[] args) {
        VideoStatusClient client = new VideoStatusClientImpl("http://localhost:8080", 5);

        try {
            String status = client.fetchJobStatus();
            System.out.println("Final job status: " + status);
        } catch (VideoStatusException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            // Ensure all Netty resources are cleaned up
            HttpResources.disposeLoopsAndConnectionsLater().block();
        }
    }
}
