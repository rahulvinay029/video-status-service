package com.example.video_status_client;

/**
 * Interface for the Video Status Client.
 */
public interface VideoStatusClient {

    /**
     * Fetches the status of a video translation job.
     *
     * @return The final job status ("completed" or "error").
     * @throws VideoStatusException if the operation fails.
     */
    String fetchJobStatus() throws VideoStatusException;
}
