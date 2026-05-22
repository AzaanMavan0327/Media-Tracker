package com.azaanmp.media_tracker.repository;

import com.azaanmp.media_tracker.model.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a data access bean component managed inside the Spring context container
public interface MediaRepository extends JpaRepository<MediaItem, Long> {
    // MediaItem: Tells the repository that this interface manages the MediaItem entity.
    // Long: Identifies that the primary key (@Id) type inside MediaItem is a Long integer.
}