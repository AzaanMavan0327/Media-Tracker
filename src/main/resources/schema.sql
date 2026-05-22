-- This is what goes inside a functional schema.sql file
CREATE TABLE media_items (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             title VARCHAR(255) NOT NULL,
                             type VARCHAR(50),
                             status VARCHAR(50),
                             rating INT,
                             notes VARCHAR(1000)
);