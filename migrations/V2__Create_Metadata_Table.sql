CREATE TABLE metadata
(
    id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255),
    created_at   TIMESTAMP,
    updated_at   TIMESTAMP,
    synopsis     TEXT,
    director     VARCHAR(30),
    release_year TIMESTAMP,
    genre        varchar(15),
    running_time BIGINT,
    video_id     BIGINT,
    CONSTRAINT FK_METADATA_VIDEO FOREIGN KEY (video_id) REFERENCES video (id) -- Adjust according to your use case
);