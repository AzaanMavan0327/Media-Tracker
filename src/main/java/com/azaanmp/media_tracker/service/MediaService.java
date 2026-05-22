package com.azaanmp.media_tracker.service;

import com.azaanmp.media_tracker.model.MediaItem;
import com.azaanmp.media_tracker.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Tells Spring this is where core application rules, logic computations, and data transformations reside
public class MediaService {

    // Final variable ensures the database link cannot be altered after initialization
    private final MediaRepository repository;

    // CONSTRUCTOR INJECTION (Dependency Injection): Spring handles finding the database repository
    // and passing it into this service automatically when launching the application
    public MediaService(MediaRepository repository) {
        this.repository = repository;
    }

    // Fetches all items out of the database
    public List<MediaItem> getAllItems() {
        return repository.findAll();
    }

    // Uses an 'Optional' container wrapper in case a user searches for an ID that doesn't exist.
    // Prevents the dreaded NullPointerException from crashing your code.
    public Optional<MediaItem> getItemById(Long id) {
        return repository.findById(id);
    }

    // Accepts a Java object and passes it straight into the database layer to be written to disk
    public MediaItem saveItem(MediaItem item) {
        return repository.save(item);
    }

    // Finds an existing item, alters its internal values to match update details, and overwrites it
    public Optional<MediaItem> updateItem(Long id, MediaItem updatedDetails) {
        return repository.findById(id).map(existingItem -> {
            existingItem.setTitle(updatedDetails.getTitle());
            existingItem.setType(updatedDetails.getType());
            existingItem.setStatus(updatedDetails.getStatus());
            existingItem.setRating(updatedDetails.getRating());
            existingItem.setNotes(updatedDetails.getNotes());
            return repository.save(existingItem); // Saves changes back to database
        });
    }

    // Verifies if the object exists prior to attempting deletion to protect database integrity
    public boolean deleteItem(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true; // Deletion was successful
        }
        return false; // Item didn't exist, nothing was deleted
    }
}