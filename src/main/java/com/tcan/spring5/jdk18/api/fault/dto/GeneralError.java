package com.tcan.spring5.jdk18.api.fault.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class GeneralError {
    private int code;
    private String description;
}
