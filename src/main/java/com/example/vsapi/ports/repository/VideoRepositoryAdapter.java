package com.example.vsapi.ports.repository;

import com.example.vsapi.adapter.VideoRepository;
import com.example.vsapi.domain.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VideoRepositoryAdapter implements VideoRepository {

    private final VideoJpaRepository videoJpaRepository;

    public VideoRepositoryAdapter(@Autowired VideoJpaRepository videoJpaRepository) {
        this.videoJpaRepository = videoJpaRepository;
    }

    @Override
    public Video getVideoById(Long id) {
        return this.videoJpaRepository.getVideoById(id);
    }

    @Override
    public Video merge(Video video) {
        return this.videoJpaRepository.saveAndFlush(video);
    }

    @Override
    public List<Video> getAllVideos() {
        return this.videoJpaRepository.getAllByDeletedFalse();
    }
}
