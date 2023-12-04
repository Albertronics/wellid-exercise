package com.wellid.exercise.modules.point.controllers;

import com.wellid.exercise.common.PaginationPayload;
import com.wellid.exercise.exceptions.point.PointNotFoundException;
import com.wellid.exercise.modules.point.entities.PointEntity;
import com.wellid.exercise.modules.point.models.inputs.PointInput;
import com.wellid.exercise.modules.point.services.PointService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/point")
@RequiredArgsConstructor
public class PointsController
{
    private final PointService pointService;

    @GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public Iterable<PointEntity> readAll(PaginationPayload pagination, Sort sort) {
        return this.pointService.getAll(pagination, sort);
    }

    @GetMapping(value = "/{x}/{y}", produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.OK)
    public PointEntity readById(
        @PathVariable("x") Integer x,
        @PathVariable("y") Integer y
    ) {
        return this.pointService.getOne(x, y)
                .orElseThrow(() -> new PointNotFoundException(String.format("(%d, %d)", x, y)));
    }

    @PostMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseStatus(HttpStatus.CREATED)
    public PointEntity create(
        @RequestBody
        @Valid PointInput payload
    ) {
        return this.pointService.create(payload);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clear() {
        this.pointService.clear();
    }
}
