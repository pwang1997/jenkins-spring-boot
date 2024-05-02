package com.erp.erpspringboot.base.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Responses<T> {
    protected List<T> data;
    protected Map<?, ?> meta;

    public Responses(List<T> data) {
        this.data = data;
        this.meta = Map.of();
    }

}
