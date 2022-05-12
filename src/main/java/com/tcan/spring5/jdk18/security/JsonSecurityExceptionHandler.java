package com.tcan.spring5.jdk18.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcan.spring5.jdk18.constant.ApplicationConstants;
import com.tcan.spring5.jdk18.constant.CodeAndDescriptions;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.ACCESS_DENIED;
import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.BAD_CREDENTIALS;

@Slf4j
@RequiredArgsConstructor
public class JsonSecurityExceptionHandler implements AccessDeniedHandler, AuthenticationEntryPoint {

    private final String applicationName;
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        LOG.warn("AuthenticationException: {}, {}", authException.getMessage(), authException.getClass());
        response.addHeader("WWW-Authenticate", "Basic realm=\"" + applicationName + "\"");
        final String acceptHeader = StringUtils.defaultIfBlank(request.getHeader(HttpHeaders.ACCEPT), ApplicationConstants.APPLICATION_JSON);
        response.setHeader("Content-Type", acceptHeader);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        final Map<String, Object> jsonResponseMap = new HashMap<>();
        jsonResponseMap.put("code", BAD_CREDENTIALS);
        jsonResponseMap.put("description", CodeAndDescriptions.CODE_N_DESC.getOrDefault(BAD_CREDENTIALS, BAD_CREDENTIALS));
        objectMapper.writeValue(response.getOutputStream(), jsonResponseMap);
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        LOG.warn("AccessDeniedException: {}, {}", accessDeniedException.getMessage(), accessDeniedException.getClass());

        response.setHeader("Content-Type", ApplicationConstants.APPLICATION_JSON);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        final Map<String, Object> jsonResponseMap = new HashMap<>();
        jsonResponseMap.put("errorCode", ACCESS_DENIED);
        jsonResponseMap.put("errorDescription", CodeAndDescriptions.CODE_N_DESC.getOrDefault(ACCESS_DENIED, ACCESS_DENIED));
        objectMapper.writeValue(response.getOutputStream(), jsonResponseMap);
    }
}
