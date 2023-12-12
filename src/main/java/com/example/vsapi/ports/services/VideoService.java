package com.example.vsapi.ports.services;

import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Video;
import com.example.vsapi.domain.use_case.MetadataUseCase;
import com.example.vsapi.domain.use_case.StaffUseCase;
import com.example.vsapi.domain.use_case.VideoUseCase;
import com.example.vsapi.dto.input.VideoInputDTO;
import com.example.vsapi.dto.output.MetadataDTO;
import com.example.vsapi.dto.output.VideoDTO;
import com.example.vsapi.ports.repository.MetadataRepositoryAdapter;
import com.example.vsapi.ports.repository.StaffRepositoryAdapter;
import com.example.vsapi.ports.repository.VideoRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoService {


    VideoRepositoryAdapter videoRepository;
    VideoUseCase videoUseCase;

    MetadataRepositoryAdapter metadataRepository;
    MetadataUseCase metadataUseCase;

    StaffRepositoryAdapter staffRepository;
    public VideoService(@Autowired VideoRepositoryAdapter videoRepository,
                        @Autowired MetadataRepositoryAdapter metadataRepository,
                        @Autowired StaffRepositoryAdapter staffRepository) {
        this.metadataRepository = metadataRepository;
        this.staffRepository = staffRepository;
        this.metadataUseCase = new MetadataUseCase(metadataRepository, new StaffUseCase(staffRepository));
        this.videoRepository = videoRepository;
        this.videoUseCase = new VideoUseCase(videoRepository);
    }

    public MetadataDTO loadVideo(Long id) {
        this.videoUseCase.setVideo(id);
        Video video = videoUseCase.loadVideo();
        return new MetadataDTO(video.getMetadata());
    }

    public VideoDTO playVideo(Long id) {
        this.videoUseCase.setVideo(id);
        Video video = this.videoUseCase.playVideo();
        return VideoDTO.
                builder().
                video(video.getVideo()).
                views(video.getViews()).
                impressions(video.getImpressions()).
                build();
    }

    public void deleteVideo(Long id) {
        this.videoUseCase.setVideo(id);
        this.videoUseCase.deleteVideo();
    }

    public void saveVideo(VideoInputDTO videoDTO) {
        var videoEntity = this.videoUseCase.mergeVideo(videoDTO.videoDTOToEntity());
        Metadata metadata = videoDTO.metadataDTOToEntity();
        metadata.setVideo(videoEntity);
        this.metadataUseCase.mergeMetadata(metadata);
    }
}
