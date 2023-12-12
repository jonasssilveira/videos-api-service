package com.example.vsapi.adapter;

import com.example.vsapi.domain.entity.Video;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository {

    Video getVideoById(Long id);
    Video merge(Video video);

    List<Video> getAllVideos();

}
