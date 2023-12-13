package com.example.vsapi.ports.controller;

import com.example.vsapi.dto.input.MetadataInputDTO;
import com.example.vsapi.dto.output.MetadataDTO;
import com.example.vsapi.ports.services.MetadataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/metadata")
@Api(value = "MetadataController", tags = "Metadata")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all metadata", notes = "Retrieve all metadata")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved metadata", response = MetadataDTO.class, responseContainer = "List", examples = @Example({
                    @ExampleProperty(value = "[{\"title\": \"Example Title 1\", \"runningTime\": 120, \"director\": \"John Doe\", \"mainActor\": \"Jane Smith\", \"synopsis\": \"Example synopsis 1\", \"releaseYear\": \"2023-01-01T00:00:00\", \"genre\": \"Action\", \"video\": \"http://example.com/video1.mp4\", \"staff\": [{\"name\": \"John\", \"main_actor\": true}]}, {\"title\": \"Example Title 2\", \"runningTime\": 90, \"director\": \"Alice Johnson\", \"mainActor\": \"Bob Anderson\", \"synopsis\": \"Example synopsis 2\", \"releaseYear\": \"2022-12-15T00:00:00\", \"genre\": \"Comedy\", \"video\": \"http://example.com/video2.mp4\", \"staff\": [{\"name\": \"Alice\", \"main_actor\": false}, {\"name\": \"Bob\", \"main_actor\": true}]}]", mediaType = "application/json")
            }))
    })
    public ResponseEntity<List<MetadataDTO>> getAll() {
        return metadataService.listVideosMetadata();
    }


    @GetMapping("/q")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get metadata by query parameters", notes = "Retrieve metadata based on query parameters")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved metadata by query parameters", response = MetadataDTO.class, responseContainer = "List", examples = @Example({
                    @ExampleProperty(value = "[{\"title\": \"Example Title 1\", \"runningTime\": 120, \"director\": \"John Doe\", \"mainActor\": \"Jane Smith\", \"synopsis\": \"Example synopsis 1\", \"releaseYear\": \"2023-01-01T00:00:00\", \"genre\": \"Action\", \"video\": \"http://example.com/video1.mp4\", \"staff\": [{\"name\": \"John\", \"main_actor\": true}]}, {\"title\": \"Example Title 2\", \"runningTime\": 90, \"director\": \"Alice Johnson\", \"mainActor\": \"Bob Anderson\", \"synopsis\": \"Example synopsis 2\", \"releaseYear\": \"2022-12-15T00:00:00\", \"genre\": \"Comedy\", \"video\": \"http://example.com/video2.mp4\", \"staff\": [{\"name\": \"Alice\", \"main_actor\": false}, {\"name\": \"Bob\", \"main_actor\": true}]}]", mediaType = "application/json")
            }))
    })
    public ResponseEntity<List<MetadataDTO>> getAllByQuery(@RequestParam MultiValueMap<String, String> queryParams) {
        return metadataService.getMetadataByQuery(queryParams);
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get metadata by ID", notes = "Retrieve metadata based on ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved metadata by ID", response = MetadataDTO.class, examples = @Example({
                    @ExampleProperty(value = "{\"title\": \"Example Title\", \"runningTime\": 120, \"director\": \"John Doe\", \"mainActor\": \"Jane Smith\", \"synopsis\": \"Example synopsis\", \"releaseYear\": \"2023-01-01T00:00:00\", \"genre\": \"Action\", \"video\": \"http://example.com/video.mp4\", \"staff\": [{\"name\": \"John\", \"main_actor\": true}]}", mediaType = "application/json")
            })),
            @ApiResponse(code = 404, message = "Metadata not found")
    })
    public ResponseEntity<MetadataDTO> getMetadataById(@PathVariable Long id) {
        return metadataService.getMetadataById(id);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Update metadata by ID", notes = "Update metadata based on ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated metadata by ID", response = MetadataDTO.class, examples = @Example({
                    @ExampleProperty(value = "{\"title\": \"Updated Title\", \"runningTime\": 150, \"director\": \"Updated Director\", \"mainActor\": \"Updated Actor\", \"synopsis\": \"Updated synopsis\", \"releaseYear\": \"2023-01-01T00:00:00\", \"genre\": \"Updated Genre\", \"video\": \"http://example.com/updated_video.mp4\", \"staff\": [{\"name\": \"Updated Name\", \"main_actor\": true}]}", mediaType = "application/json")
            })),
            @ApiResponse(code = 404, message = "Metadata not found")
    })
    public ResponseEntity<MetadataDTO> updateMetadata(@PathVariable Long id, @RequestBody MetadataInputDTO inputDTO) {
        return metadataService.mergeMetadata(id, inputDTO);
    }
}
