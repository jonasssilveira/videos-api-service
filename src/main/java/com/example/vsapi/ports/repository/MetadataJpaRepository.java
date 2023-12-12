package com.example.vsapi.ports.repository;

import com.example.vsapi.domain.entity.Metadata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MetadataJpaRepository extends JpaRepository<Metadata, Long> {

    List<Metadata> getAllByVideoDeletedFalse();

    Optional<Metadata> getMetadataById(Long id);

}
