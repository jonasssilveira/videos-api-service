package com.example.vsapi.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "video")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Video {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // Or another suitable strategy
    private Long id;

    @Getter
    @Setter
    private boolean deleted = false;

    @Getter
    @Setter
    private String video;

    @Getter
    @Setter
    private LocalDateTime createdAt;

    @Getter
    @Setter
    private LocalDateTime updatedAt;

    @Getter
    @Setter
    private Long impressions;

    @Getter
    @Setter
    private Long views;

    @Getter
    @Setter
    @OneToOne(mappedBy = "video", cascade = CascadeType.ALL)
    @JsonIgnore
    private Metadata metadata;

    public void increaseViews(){
        this.views+=1;
    }

    public void increaseImpressions(){
        this.impressions+=1;
    }

    public void delete(){
        this.deleted = true;
    }

}
