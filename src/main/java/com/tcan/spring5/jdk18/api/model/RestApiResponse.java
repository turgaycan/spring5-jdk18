package com.tcan.spring5.jdk18.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class RestApiResponse<T> extends BaseRestResponse {
    private static final long serialVersionUID = 1L;

    private final T data;

    public RestApiResponse(T data) {
        this(RestResponseStatus.ok(), data);
    }

    public RestApiResponse(RestResponseStatus status) {
        this(status, null);
    }

    public RestApiResponse(RestResponseStatus status, T data) {
        super(status);
        this.data = data;
    }
}
