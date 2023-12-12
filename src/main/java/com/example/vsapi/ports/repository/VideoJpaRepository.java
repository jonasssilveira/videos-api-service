package com.example.vsapi.ports.repository;

import com.example.vsapi.domain.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VideoJpaRepository extends JpaRepository<Video, Long> {

    Video getVideoById(Long id);

    List<Video> getAllByDeletedFalse();

}
