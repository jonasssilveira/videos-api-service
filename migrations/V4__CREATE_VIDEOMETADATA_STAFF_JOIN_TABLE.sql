CREATE TABLE metadata_staff
(
    metadata_id BIGINT NOT NULL,
    staff_id    BIGINT NOT NULL,
    PRIMARY KEY (metadata_id, staff_id),
    FOREIGN KEY (metadata_id) REFERENCES metadata (id),
    FOREIGN KEY (staff_id) REFERENCES staff (id)
);
