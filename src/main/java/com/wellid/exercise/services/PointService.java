package com.wellid.exercise.services;

import com.wellid.exercise.common.PaginationPayload;
import com.wellid.exercise.entities.PointEntity;
import com.wellid.exercise.inputs.PointInput;
import com.wellid.exercise.repositories.PointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PointService
{
    private final PointRepository pointRepository;

    public Page<PointEntity> getAll(PaginationPayload pagination, Sort sorting)
    {
        if (pagination == null) {
            pagination = new PaginationPayload();
        }

        var pageRequest = PageRequest.of(
            pagination.getPage(),
            pagination.getSize(),
            sorting == null ? Sort.unsorted() : sorting
        );

        return this.pointRepository.findAll(pageRequest);
    }

    public Optional<PointEntity> getOne(Integer x, Integer y) {
        var pk = new PointEntity.PointEntityId()
                .setX(x)
                .setY(y);

        return this.pointRepository.findById(pk);
    }

    public PointEntity create(PointInput input)
    {
        var label = new PointEntity()
                .setX(input.getX())
                .setY(input.getY());

        return this.pointRepository.save(label);
    }

    public void clear() {
        this.pointRepository.deleteAll();
    }
}
