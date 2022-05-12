package com.tcan.spring5.jdk18.api.fault.dto;

import lombok.Getter;

@Getter
public class FieldErrorDetail extends ErrorDetail {
    private final String field;

    public FieldErrorDetail(String field, String code, String description) {
        super(code, description);
        this.field = field;
    }
}
