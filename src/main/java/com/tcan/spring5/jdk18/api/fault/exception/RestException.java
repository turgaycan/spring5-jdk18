package com.tcan.spring5.jdk18.api.fault.exception;

import com.tcan.spring5.jdk18.constant.CodeAndDescriptions;
import lombok.Getter;

import static java.util.Objects.requireNonNullElse;

@Getter
public class RestException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final String code;
    private final String description;

    public RestException(String code) {
        super(CodeAndDescriptions.CODE_N_DESC.getOrDefault(code, code));
        final String description = CodeAndDescriptions.CODE_N_DESC.getOrDefault(code, code);
        this.code = code;
        this.description = description;
    }

    public RestException(String message, String code, String description) {
        super(message);
        this.code = code;
        this.description = requireNonNullElse(description, code);
    }
}
