package com.azaanmp.media_tracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication enables Auto-Configuration (Spring scans your project packages and
// automatically instantiates your classes, routes, and database connections based on dependencies)
@SpringBootApplication
public class MediaTrackerApplication {

    public static void main(String[] args) {
        // Launches the framework, spins up the server, and begins monitoring port 8080
        SpringApplication.run(MediaTrackerApplication.class, args);
    }
}