package com.example.video_status_server.controller;

import com.example.video_status_server.service.JobSimulationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@RestController
public class VideoStatusController {

    private final JobSimulationService jobSimulationService;

    public VideoStatusController(JobSimulationService jobSimulationService) {
        this.jobSimulationService = jobSimulationService;
    }

    @GetMapping(value = "/status", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getStatus() {
        Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();

        // Start the job simulation
        jobSimulationService.simulateJob(sink);

        return sink.asFlux()
                .doOnCancel(() -> {
                    System.out.println("Client disconnected. Cleaning up resources.");
                    sink.tryEmitComplete();
                })
                .onErrorResume(e -> {
                    System.err.println("Error in SSE stream: " + e.getMessage());
                    return Flux.just("error");
                })
                .doOnTerminate(() -> System.out.println("SSE stream terminated."));
    }
}