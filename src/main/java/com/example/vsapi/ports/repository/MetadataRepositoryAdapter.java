package com.example.vsapi.ports.repository;

import com.example.vsapi.adapter.MetadataRepository;
import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.ports.exceptions.NotFoundRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MetadataRepositoryAdapter implements MetadataRepository {

    private final MetadataJpaRepository metadataJpaRepository;

    public MetadataRepositoryAdapter(@Autowired MetadataJpaRepository metadataJpaRepository) {
        this.metadataJpaRepository = metadataJpaRepository;
    }

    @Override
    public Metadata merge(Metadata metadata) {
        return this.metadataJpaRepository.saveAndFlush(metadata);
    }

    @Override
    public Metadata getMetadataById(Long id) {
        return this.metadataJpaRepository.getMetadataById(id).orElseThrow(()-> new NotFoundRequestException("not found"));
    }

    @Override
    public List<Metadata> getAllVideos() {
        return this.metadataJpaRepository.getAllByVideoDeletedFalse();
    }


}
