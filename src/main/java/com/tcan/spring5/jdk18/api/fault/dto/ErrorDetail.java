package com.tcan.spring5.jdk18.api.fault.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ErrorDetail {
    private final String code;
    private final String description;
}
