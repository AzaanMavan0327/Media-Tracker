package com.azaanmp.media_tracker.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Registers this class with JPA so a database table can be modeled after it
@Table(name = "media_items") // Explicitly titles the SQL table database name as 'media_items'
public class MediaItem {

    @Id // Identifies this particular field as the unique Primary Key for database indexing
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Instructs the database engine to auto-increment IDs (1, 2, 3...)
    private Long id;

    private String title;
    private String type;     // Stores the media category (e.g., "Game", "Movie", "Book")
    private String status;   // Tracks your progress (e.g., "BACKLOG", "IN_PROGRESS", "COMPLETED")
    private int rating;      // Holds user ratings valued 1 through 10
    private String notes;    // Holds user reflections, text commentary, or reviews

    // MANDATORY REQUIREMENT: JPA frameworks require an empty, zero-argument constructor
    // to dynamically instantiate objects when pulling data rows out of the database
    public MediaItem() {
    }

    // CONSTRUCTOR OVERLOAD: Used inside our application layer to quickly make ready-to-save objects
    // without manual property setting lines
    public MediaItem(String title, String type, String status, int rating, String notes) {
        this.title = title;
        this.type = type;
        this.status = status;
        this.rating = rating;
        this.notes = notes;
    }

    // ENCAPSULATION: Standard getter and setter methods. Spring uses these behind the scenes
    // to extract properties when translating your objects into JSON files
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}