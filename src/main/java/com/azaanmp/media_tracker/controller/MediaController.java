package com.azaanmp.media_tracker.controller;

import com.azaanmp.media_tracker.model.MediaItem;
import com.azaanmp.media_tracker.service.MediaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Tells Spring this class converts returned data structures immediately into clean JSON web output strings
@RequestMapping("/api/media") // Base URL routing marker. All URLs in this file start with this prefix
public class MediaController {

    private final MediaService service;

    public MediaController(MediaService service) {
        this.service = service;
    }

    // 1. READ ALL - Postman Endpoint: GET http://localhost:8080/api/media
    @GetMapping
    public ResponseEntity<List<MediaItem>> getAllMedia() {
        return ResponseEntity.ok(service.getAllItems());
    }

    // 2. READ BY ID - Postman Endpoint: GET http://localhost:8080/api/media/{id}
    @GetMapping("/{id}")
    public ResponseEntity<MediaItem> getMediaById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. CREATE - Postman Endpoint: POST http://localhost:8080/api/media
    @PostMapping
    public ResponseEntity<?> createMedia(@RequestBody MediaItem item) {
        try {
            MediaItem createdItem = service.saveItem(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
        } catch (IllegalArgumentException e) {
            // Catches validation errors thrown by service layer logic and outputs a 400 Bad Request
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 4. UPDATE - Postman Endpoint: PUT http://localhost:8080/api/media/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMedia(@PathVariable Long id, @RequestBody MediaItem itemDetails) {
        try {
            return service.updateItem(id, itemDetails)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            // Catches validation updates errors and drops out safely
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 5. DELETE - Postman Endpoint: DELETE http://localhost:8080/api/media/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        if (service.deleteItem(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}