package com.wellid.exercise.controllers;

import com.wellid.exercise.common.PaginationPayload;
import com.wellid.exercise.entities.PointEntity;
import com.wellid.exercise.exceptions.point.PointNotFoundException;
import com.wellid.exercise.inputs.PointInput;
import com.wellid.exercise.services.LineComputer;
import com.wellid.exercise.services.PointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class PointsController
{
    private final PointService pointService;
    private final LineComputer lineComputer;

    @GetMapping(value = "/point", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PointEntity> readAll(PaginationPayload pagination, Sort sort) {
        return this.pointService.getAll(pagination, sort);
    }

    @GetMapping(value = "/lines/{n}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<StreamingResponseBody> compute(
            @PathVariable("n") Integer n
    ) {
        StreamingResponseBody stream = outputStream -> {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            writer.write("[");

            this.lineComputer.compute(n, writer);

            writer.write("]");
            writer.flush();
        };

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(stream);
    }

    @GetMapping(value = "/point/{x}/{y}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public PointEntity readById(
        @PathVariable("x") Integer x,
        @PathVariable("y") Integer y
    ) {
        return this.pointService.getOne(x, y)
                .orElseThrow(() -> new PointNotFoundException(String.format("(%d, %d)", x, y)));
    }

    @PostMapping(value = "/point", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public PointEntity create(
        @RequestBody
        @Valid PointInput payload
    ) {
        return this.pointService.create(payload);
    }

    @DeleteMapping("/space")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clear() {
        this.pointService.clear();
    }
}
