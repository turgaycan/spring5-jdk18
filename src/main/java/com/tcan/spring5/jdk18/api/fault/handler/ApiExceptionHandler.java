package com.tcan.spring5.jdk18.api.fault.handler;

import com.tcan.spring5.jdk18.api.fault.dto.ValidationErrorRestResponse;
import com.tcan.spring5.jdk18.api.fault.exception.RestException;
import com.tcan.spring5.jdk18.api.fault.exception.ServiceRuntimeException;
import com.tcan.spring5.jdk18.api.model.BaseRestResponse;
import com.tcan.spring5.jdk18.api.model.RestApiResponse;
import com.tcan.spring5.jdk18.api.model.RestResponseStatus;
import com.tcan.spring5.jdk18.constant.CodeAndDescriptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.*;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(basePackages = "com.tcan.spring5.jdk18.api")
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public RestApiResponse<ValidationErrorRestResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        LOG.info("handleMethodArgumentNotValidException::{}", ex.getMessage());
        final Errors errors = ex.getBindingResult();
        final ValidationErrorRestResponse response = new ValidationErrorRestResponse();

        if (errors.hasFieldErrors()) {
            errors.getFieldErrors()
                    .forEach(fieldError -> {
                        final String code = fieldError.getDefaultMessage();
                        response.addFieldError(fieldError.getField(), code,
                                CodeAndDescriptions.CODE_N_DESC.getOrDefault(code, code));
                    });
        }

        if (errors.hasGlobalErrors()) {
            errors.getGlobalErrors().forEach(globalError -> {
                final String code = globalError.getDefaultMessage();
                response.addGlobalError(code, CodeAndDescriptions.CODE_N_DESC.getOrDefault(code, code));
            });
        }

        return new RestApiResponse<>(new RestResponseStatus(ERR_INPUT_VALIDATION), response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseRestResponse handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        LOG.info("handleHttpMessageNotReadableException::{}", ex.getMessage(), ex);
        return BaseRestResponse.forReturnCode(ERR_INPUT_FORMAT);
    }

    @ExceptionHandler(RestException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public BaseRestResponse handleRestException(RestException ex) {
        LOG.info("handleRestException::exceptionMessage::{}::description::{}", ex.getMessage(), ex.getDescription());
        return new BaseRestResponse(new RestResponseStatus(ex.getCode(), ex.getDescription()));
    }

    @ExceptionHandler(ServiceRuntimeException.class)
    public BaseRestResponse handleServiceRuntimeException(HttpServletResponse response, ServiceRuntimeException ex) {
        final var code = ex.getCode();
        final var description = ex.getDescription();

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        LOG.info("handleServiceRuntimeException::errorCode::{}::description::{}", code, description);
        return new BaseRestResponse(new RestResponseStatus(code, description));
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseRestResponse handleAllException(Exception ex) {
        LOG.error("Global error occurred::{}", ex.getMessage(), ex);
        return new BaseRestResponse(new RestResponseStatus(UNKNOWN_ERROR));
    }

}
