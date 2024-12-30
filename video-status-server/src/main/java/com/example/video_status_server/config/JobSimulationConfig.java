package com.example.video_status_server.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobSimulationConfig {

    @Value("${job.simulation.delay:1000}")
    private int delay;

    @Value("${job.simulation.pending-count:3}")
    private int pendingCount;

    @Value("${job.simulation.error-probability:0.1}")
    private double errorProbability;

    public int getDelay() {
        return delay;
    }

    public int getPendingCount() {
        return pendingCount;
    }

    public double getErrorProbability() {
        return errorProbability;
    }
}
