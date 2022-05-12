package com.tcan.spring5.jdk18.api.model;

import com.tcan.spring5.jdk18.util.MdcUtils;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class BaseRestResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    private final RestResponseStatus status;
    private final String txKey = MdcUtils.getOrGenerateLogTrackKey();

    public static BaseRestResponse ok() {
        return new BaseRestResponse(RestResponseStatus.ok());
    }

    public static BaseRestResponse forReturnCode(String code) {
        return new BaseRestResponse(new RestResponseStatus(code));
    }
}
