package com.example.vsapi.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {

    @Id
    @Setter
    @Getter
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Or another suitable strategy
    private Long id;

    @Setter
    @Getter
    private String title;

    @Getter
    @Setter
    private LocalDateTime createdAt;

    @Getter
    @Setter
    private LocalDateTime updatedAt;

    @Setter
    @Getter
    private String synopsis;

    @Setter
    @Getter
    private String director;

    @Setter
    @Getter
    private LocalDateTime releaseYear;
    //Genre
    @Setter
    @Getter
    private long runningTime;

    @Setter
    @Getter
    private String genre;

    @Setter
    @Getter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id")
    private Video video;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(
            name = "metadata_staff",
            joinColumns = @JoinColumn(name = "metadata_id"),
            inverseJoinColumns = @JoinColumn(name = "staff_id")
    )
    private List<Staff> staffMembers;

}
