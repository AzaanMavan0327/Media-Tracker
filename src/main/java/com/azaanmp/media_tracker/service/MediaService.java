package com.azaanmp.media_tracker.service;

import com.azaanmp.media_tracker.model.MediaItem;
import com.azaanmp.media_tracker.repository.MediaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Tells Spring this is where core application rules, logic computations, and data transformations reside
public class MediaService {

    private final MediaRepository repository;

    public MediaService(MediaRepository repository) {
        this.repository = repository;
    }

    // CUSTOM VALIDATION CORE: Implements programmatic business rules manually without external annotation packages
    private void validateMediaItem(MediaItem item) {
        if (item.getTitle() == null || item.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title is required and cannot be blank");
        }
        if (item.getType() == null || item.getType().trim().isEmpty()) {
            throw new IllegalArgumentException("Media type (Game, Movie, Book, etc.) is required");
        }
        if (item.getStatus() == null || item.getStatus().trim().isEmpty()) {
            throw new IllegalArgumentException("Status (BACKLOG, IN_PROGRESS, COMPLETED) is required");
        }
        if (item.getRating() < 1 || item.getRating() > 10) {
            throw new IllegalArgumentException("Rating must be strictly between 1 and 10");
        }
    }

    public List<MediaItem> getAllItems() {
        return repository.findAll();
    }

    public Optional<MediaItem> getItemById(Long id) {
        return repository.findById(id);
    }

    public MediaItem saveItem(MediaItem item) {
        validateMediaItem(item); // Enforce checks before attempting a save transaction
        return repository.save(item);
    }

    public Optional<MediaItem> updateItem(Long id, MediaItem updatedDetails) {
        validateMediaItem(updatedDetails); // Enforce checks before attempting an update transaction
        return repository.findById(id).map(existingItem -> {
            existingItem.setTitle(updatedDetails.getTitle());
            existingItem.setType(updatedDetails.getType());
            existingItem.setStatus(updatedDetails.getStatus());
            existingItem.setRating(updatedDetails.getRating());
            existingItem.setNotes(updatedDetails.getNotes());
            return repository.save(existingItem);
        });
    }

    public boolean deleteItem(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}