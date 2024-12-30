package com.example.video_status_client;

/**
 * Custom exception for the Video Status Client.
 */
public class VideoStatusException extends RuntimeException {
    public VideoStatusException(String message) {
        super(message);
    }

    public VideoStatusException(String message, Throwable cause) {
        super(message, cause);
    }
}
