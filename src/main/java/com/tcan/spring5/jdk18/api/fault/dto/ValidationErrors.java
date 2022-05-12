package com.tcan.spring5.jdk18.api.fault.dto;

import lombok.*;

import java.util.Map;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrors {
    private Map<String, ParameterizedError> fieldErrors;
}
