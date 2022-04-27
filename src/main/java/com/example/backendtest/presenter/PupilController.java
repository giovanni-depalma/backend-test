package com.example.backendtest.presenter;

import com.example.backendtest.mapper.PupilMapper;
import com.example.backendtest.presenter.api.PupilRequestResource;
import com.example.backendtest.presenter.api.PupilResource;
import com.example.backendtest.service.PupilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RequestMapping("/pupil")
@RestController
@AllArgsConstructor
public class PupilController {
    private PupilService service;
    private PupilMapper mapper;

    @PostMapping
    @Operation(summary = "Create a pupil", tags = "pupil", responses = {
            @ApiResponse(description = "Success", responseCode = "201", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PupilResource.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content)
    })
    public Mono<PupilResource> create(
            @Valid @RequestBody PupilRequestResource request) {
        return service.create(mapper.toDomain(request)).map(mapper::toResource);
    }


    @PostMapping("/findByExample")
    @Operation(summary = "Find pupils By Example", tags = "pupil", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = PupilResource.class))),
    })
    public Flux<PupilResource> findByExample(@RequestBody PupilRequestResource request) {
        return service.findByExample(mapper.toDomain(request)).map(mapper::toResource);
    }

    @GetMapping("/findByLevel")
    @Operation(summary = "Find pupils by level", tags = "pupil", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PupilResource.class)))),
    })
    public Flux<PupilResource> findByEducationLevel(String level) {
        return service.findByEducationLevel(level).map(mapper::toResource);
    }

    @GetMapping
    @Operation(summary = "Find all", tags = "pupil", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Page.class))),
    })
    public Mono<Page<PupilResource>> findAll(Pageable pageable) {
        var pupils = service.findAll(pageable).map(mapper::toResource);
        var count = service.count();
        return Mono.zip(pupils.collectList(), count).map(t -> PageableExecutionUtils.getPage(t.getT1(), pageable, t::getT2));
    }
}
