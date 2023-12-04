package com.wellid.exercise.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationPayload
{
    private Integer page;
    private Integer size;

    public PaginationPayload(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PaginationPayload() {
        this.page = 0;
        this.size = 25;
    }
}
