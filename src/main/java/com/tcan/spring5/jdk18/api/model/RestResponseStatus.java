package com.tcan.spring5.jdk18.api.model;

import lombok.*;

import java.io.Serializable;

import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.CODE_N_DESC;
import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.NO_ERROR;

@EqualsAndHashCode
@Getter
@Setter
@RequiredArgsConstructor
public class RestResponseStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    private final String code;
    private final String description;

    public RestResponseStatus(String code) {
        this.code = code;
        this.description = CODE_N_DESC.get(code);
    }

    public static RestResponseStatus ok() {
        return new RestResponseStatus(NO_ERROR, CODE_N_DESC.get(NO_ERROR));
    }

    public static RestResponseStatus error(String code) {
        return new RestResponseStatus(code, CODE_N_DESC.get(code));
    }
}
