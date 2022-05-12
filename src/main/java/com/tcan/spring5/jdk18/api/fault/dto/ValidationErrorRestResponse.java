package com.tcan.spring5.jdk18.api.fault.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ValidationErrorRestResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private final List<FieldErrorDetail> fields = new ArrayList<>();
    private final List<ErrorDetail> errors = new ArrayList<>();

    public void addFieldError(String field, String code, String description) {
        fields.add(new FieldErrorDetail(field, code, description));
    }

    public void addGlobalError(String code, String description) {
        this.errors.add(new ErrorDetail(code, description));
    }
}
