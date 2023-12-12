package com.example.vsapi.domain.use_case;

import com.example.vsapi.adapter.VideoRepository;
import com.example.vsapi.domain.entity.Video;
import com.example.vsapi.domain.entity.Metadata;

public class VideoUseCase {

    private VideoRepository videoRepository;
    private Video video;

    public VideoUseCase(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public void increaseImpressions(){
        this.video.increaseImpressions();
        mergeVideo(this.video);
    }

    public void increaseViews(){
        this.video.increaseViews();
        mergeVideo(this.video);
    }

    public void setVideo(Long videoId){
        this.video = videoRepository.getVideoById(videoId);
        if (this.video == null)
            throw new RuntimeException("video not found");
    }

    public Video loadVideo(){
        increaseImpressions();
        return this.video;
    }

    public Video playVideo(){
        increaseViews();
        return this.video;
    }

    public Video mergeVideo(Video video){
        return videoRepository.merge(video);
    }

    public void deleteVideo(){
        this.video.delete();
        mergeVideo(this.video);
    }
}
