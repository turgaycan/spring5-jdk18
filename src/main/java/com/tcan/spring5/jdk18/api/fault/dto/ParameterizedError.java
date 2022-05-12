package com.tcan.spring5.jdk18.api.fault.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ParameterizedError {
    private int errorCode;
    private String errorDescription;
    private List<String> arguments;
}
