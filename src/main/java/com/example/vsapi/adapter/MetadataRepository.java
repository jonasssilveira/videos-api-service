package com.example.vsapi.adapter;

import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Video;

import java.util.List;

public interface MetadataRepository {

    List<Metadata> getAllVideos();

    Metadata merge(Metadata metadata);

    Metadata getMetadataById(Long id);


}
