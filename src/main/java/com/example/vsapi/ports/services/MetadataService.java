package com.example.vsapi.ports.services;

import com.example.vsapi.domain.entity.Metadata;
import com.example.vsapi.domain.entity.Staff;
import com.example.vsapi.domain.use_case.MetadataUseCase;
import com.example.vsapi.domain.use_case.StaffUseCase;
import com.example.vsapi.dto.input.MetadataInputDTO;
import com.example.vsapi.dto.output.MetadataDTO;
import com.example.vsapi.ports.repository.MetadataRepositoryAdapter;
import com.example.vsapi.ports.repository.StaffRepositoryAdapter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MetadataService {

    @PersistenceContext
    private EntityManager entityManager;

    MetadataRepositoryAdapter metadataRepository;
    MetadataUseCase metadataUseCase;

    StaffRepositoryAdapter staffRepository;

    public MetadataService(@Autowired MetadataRepositoryAdapter metadataRepository,
                           @Autowired StaffRepositoryAdapter staffRepository) {
        this.metadataRepository = metadataRepository;
        this.staffRepository = staffRepository;
        this.metadataUseCase = new MetadataUseCase(metadataRepository, new StaffUseCase(staffRepository));
    }

    public ArrayList<MetadataDTO> listVideosMetadata() {
        return (ArrayList<MetadataDTO>) this.metadataUseCase.
                listAllVideos().
                stream().
                map(metadata -> {
                    return new MetadataDTO().MetadataDTOList(metadata);
                }).
                collect(Collectors.toList());
    }

    public ResponseEntity<List<MetadataDTO>> getMetadataByQuery(MultiValueMap<String, String> q){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Metadata> query = cb.createQuery(Metadata.class);
        Root<Metadata> root = query.from(Metadata.class);


        Predicate predicate = cb.conjunction();
        Map<String, String> params = q.toSingleValueMap();
        Join<Metadata, Staff> staffJoin = root.join("staffMembers", JoinType.INNER);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = entry.getValue();
            String key = entry.getKey();

            if (value != null && !value.isEmpty()) {
                if ("main_actor".equals(key)) {
                    predicate = cb.and(predicate,cb.equal(staffJoin.get("mainActor"), true));
                    predicate = cb.and(predicate,cb.equal(staffJoin.get("name"), value));
                }
            }
            else
                predicate = cb.and(predicate,cb.equal(root.get(key), value));
        }
        query.select(root).distinct(true).where(predicate);

        List<Metadata> metadataList = entityManager.createQuery(query).getResultList();

        return ResponseEntity.ok(metadataList.
                stream().
                map(MetadataDTO::new).
                collect(Collectors.toList()));

    }

    public ResponseEntity<MetadataDTO> getMetadataById(Long metadataId){
        metadataUseCase.setMetadata(metadataId);
        return ResponseEntity.ok(
                new MetadataDTO(metadataUseCase.getMetadata())
        );


    }

    public ResponseEntity<MetadataDTO> mergeMetadata(Long id, MetadataInputDTO inputDTO) {
        metadataUseCase.setMetadata(id);
        var metadata = metadataUseCase.mergeMetadata(inputDTO.metadataDTOToEntity(id));
        return ResponseEntity.ok(new MetadataDTO(metadata));
    }
}
