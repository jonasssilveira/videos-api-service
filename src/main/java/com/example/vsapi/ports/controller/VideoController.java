package com.example.vsapi.ports.controller;

import com.example.vsapi.dto.input.VideoInputDTO;
import com.example.vsapi.dto.output.MetadataDTO;
import com.example.vsapi.dto.output.VideoDTO;
import com.example.vsapi.ports.services.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Example;
import io.swagger.annotations.ExampleProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/videos")
@Api(value = "VideoController", tags = "Videos")
public class VideoController {

    @Autowired
    private VideoService videoService;
    @DeleteMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Delete a video by ID", notes = "Deletes a video based on ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Video deleted successfully"),
            @ApiResponse(code = 404, message = "Video not found")
    })
    public void delete(@PathVariable Long id){
        videoService.deleteVideo(id);
    }

    @GetMapping("play/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Play a video by ID", notes = "Plays a video based on ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully played the video", response = VideoDTO.class, examples = @Example({
                    @ExampleProperty(value = "{\"video\": \"http://example.com/video.mp4\", \"views\": 100, \"impressions\": 50}", mediaType = "application/json")
            })),
            @ApiResponse(code = 404, message = "Video not found")
    })
    public VideoDTO playVideo(@PathVariable Long id){
        return videoService.playVideo(id);
    }

    @GetMapping("load/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Load video metadata by ID", notes = "Loads video metadata based on ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully loaded video metadata", response = MetadataDTO.class, examples = @Example({
                    @ExampleProperty(value = "{\"title\": \"Example Title\", \"runningTime\": 120, \"director\": \"John Doe\", \"mainActor\": \"Jane Smith\", \"synopsis\": \"Example synopsis\", \"releaseYear\": \"2023-01-01T00:00:00\", \"genre\": \"Action\", \"video\": \"http://example.com/video.mp4\", \"staff\": [{\"name\": \"John\", \"main_actor\": true}]}", mediaType = "application/json")
            })),
            @ApiResponse(code = 404, message = "Video metadata not found")
    })
    public MetadataDTO loadVideo(@PathVariable Long id){
        return videoService.loadVideo(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Publish a video", notes = "Publishes a video")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully loaded video metadata", response = MetadataDTO.class, examples = @Example({
                    @ExampleProperty(value = "{\"title\": \"Example Title\", \"runningTime\": 120, \"director\": \"John Doe\", \"mainActor\": \"Jane Smith\", \"synopsis\": \"Example synopsis\", \"releaseYear\": \"2023-01-01T00:00:00\", \"genre\": \"Action\", \"video\": \"http://example.com/video.mp4\", \"staff\": [{\"name\": \"John\", \"main_actor\": true}]}", mediaType = "application/json")
            })),
            @ApiResponse(code = 400, message = "Invalid input for publishing video")
    })
    public void publishVideo(@RequestBody VideoInputDTO video){
        videoService.saveVideo(video);
    }
}