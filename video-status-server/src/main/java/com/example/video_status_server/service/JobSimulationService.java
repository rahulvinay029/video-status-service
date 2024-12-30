package com.example.video_status_server.service;

import com.example.video_status_server.config.JobSimulationConfig;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class JobSimulationService {

    private final JobSimulationConfig config;

    public JobSimulationService(JobSimulationConfig config) {
        this.config = config;
    }

    public void simulateJob(Sinks.Many<String> sink) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        System.out.println("Simulating job status...");
        try {
            int delay = config.getDelay();
            int pendingCount = config.getPendingCount();

            for (int i = 0; i < pendingCount; i++) {
                final int step = i;

                scheduler.schedule(() -> {
                    if (sink.currentSubscriberCount() > 0) {
                        System.out.println("Emitting: pending");
                        sink.tryEmitNext("pending");
                    } else {
                        System.out.println("No subscribers detected during pending emission.");
                    }
                }, delay * step, TimeUnit.MILLISECONDS);
            }

            scheduler.schedule(() -> {
                if (sink.currentSubscriberCount() > 0) {
                    String finalStatus = Math.random() < config.getErrorProbability() ? "error" : "completed";
                    System.out.println("Emitting: " + finalStatus);
                    sink.tryEmitNext(finalStatus);
                    sink.tryEmitComplete();
                } else {
                    System.out.println("No subscribers detected during final emission.");
                }
                scheduler.shutdown();
            }, delay * pendingCount, TimeUnit.MILLISECONDS);

        } catch (Exception e) {
            System.err.println("Error during job simulation: " + e.getMessage());
            sink.tryEmitError(e);
            scheduler.shutdownNow();
        }
    }
}
