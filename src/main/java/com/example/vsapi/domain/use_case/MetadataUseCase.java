package com.example.vsapi.domain.use_case;

import com.example.vsapi.adapter.MetadataRepository;
import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.ports.exceptions.NotFoundRequestException;

import java.util.List;
import java.util.stream.Collectors;

public class MetadataUseCase {

    MetadataRepository metadataRepository;
    StaffUseCase staffUseCase;

    Metadata metadata;

    public MetadataUseCase(MetadataRepository videoMetadataRepository, StaffUseCase staffUseCase) {
        this.metadataRepository = videoMetadataRepository;
        this.staffUseCase = staffUseCase;
    }

    public List<Metadata> listAllVideos() {
        return metadataRepository.getAllVideos();
    }

    public Metadata mergeMetadata(Metadata metadata) {
        metadata.setStaffMembers(metadata.getStaffMembers().stream().map(staff -> staffUseCase.merge(staff))
                .collect(Collectors.toList()));
        return metadataRepository.merge(metadata);
    }

    public void setMetadata(Long metadataId) {
        this.metadata = metadataRepository.getMetadataById(metadataId);
        if (this.metadata == null)
            throw new NotFoundRequestException("not found");
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

}
