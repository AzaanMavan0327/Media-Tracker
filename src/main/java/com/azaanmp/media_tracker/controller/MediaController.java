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

    // Injects our business logic service layer into our routing layer
    public MediaController(MediaService service) {
        this.service = service;
    }

    // 1. READ ALL - Postman Endpoint: GET http://localhost:8080/api/media
    @GetMapping
    public ResponseEntity<List<MediaItem>> getAllMedia() {
        // ResponseEntity wrapper lets us return both the data list AND an HTTP 200 OK status code
        return ResponseEntity.ok(service.getAllItems());
    }

    // 2. READ BY ID - Postman Endpoint: GET http://localhost:8080/api/media/{id}
    // @PathVariable extracts the dynamic number straight out of the browser URL path string
    @GetMapping("/{id}")
    public ResponseEntity<MediaItem> getMediaById(@PathVariable Long id) {
        return service.getItemById(id)
                .map(ResponseEntity::ok) // If found, wrap item inside a 200 OK header
                .orElse(ResponseEntity.notFound().build()); // If empty, return a clean 404 Not Found error
    }

    // 3. CREATE - Postman Endpoint: POST http://localhost:8080/api/media
    // @RequestBody tells Spring to parse the incoming JSON payload from Postman and deserialize it into a MediaItem object
    @PostMapping
    public ResponseEntity<MediaItem> createMedia(@RequestBody MediaItem item) {
        MediaItem createdItem = service.saveItem(item);
        // Returns a professional '21 Created' response status specifying a brand new record was generated
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    // 4. UPDATE - Postman Endpoint: PUT http://localhost:8080/api/media/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MediaItem> updateMedia(@PathVariable Long id, @RequestBody MediaItem itemDetails) {
        return service.updateItem(id, itemDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE - Postman Endpoint: DELETE http://localhost:8080/api/media/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedia(@PathVariable Long id) {
        if (service.deleteItem(id)) {
            // Returns a standard 204 No Content header, confirming database deletion execution was successful
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}