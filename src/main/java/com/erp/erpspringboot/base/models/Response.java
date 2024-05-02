package com.erp.erpspringboot.base.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> {
    protected T data;
    protected Map<?, ?> meta;

    public Response(T data) {
        this.data = data;
        this.meta = Map.of();
    }
}
