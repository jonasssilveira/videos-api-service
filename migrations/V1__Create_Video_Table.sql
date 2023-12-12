CREATE TABLE video
(
    id          BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    deleted     BOOLEAN DEFAULT FALSE,
    video       VARCHAR(255),
    created_at  TIMESTAMP,
    updated_at  TIMESTAMP,
    impressions BIGINT,
    views       BIGINT
);
