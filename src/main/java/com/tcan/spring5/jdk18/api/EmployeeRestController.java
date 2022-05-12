package com.tcan.spring5.jdk18.api;

import com.tcan.spring5.jdk18.api.fault.exception.RestException;
import com.tcan.spring5.jdk18.api.model.*;
import com.tcan.spring5.jdk18.api.validator.EmailValidator;
import com.tcan.spring5.jdk18.common.model.Pager;
import com.tcan.spring5.jdk18.common.model.SearchResult;
import com.tcan.spring5.jdk18.service.EmployeeService;
import com.tcan.spring5.jdk18.service.model.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.tcan.spring5.jdk18.constant.ApplicationConstants.AUTH_READ;
import static com.tcan.spring5.jdk18.constant.ApplicationConstants.AUTH_WRITE;
import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.EMAIL_VALIDATION;

@Slf4j
@RestController
@RequestMapping(value = "api/rest")
@RequiredArgsConstructor
public class EmployeeRestController {
    private final EmailValidator emailValidator;
    private final EmployeeService employeeService;

    @Secured(AUTH_READ)
    @GetMapping("v10/employees")
    public PageableRestApiResponse<EmployeeResponse> list(@Validated @RequestBody SearchEmployeesRequest employeesRequest,
                                                          Pager pager) {
        LOG.debug("list employee request :: {}", employeesRequest);
        final boolean isValid = emailValidator.isValidIfNotBlank(employeesRequest.getEmail());
        if (!isValid) {
            throw new RestException(EMAIL_VALIDATION);
        }
        final SearchResult<EmployeeDTO> employees = employeeService.searchEmployees(employeesRequest.toQuery(), pager);
        final List<EmployeeResponse> employeeList = employees.getContent()
                .stream()
                .map(EmployeeResponse::new)
                .collect(Collectors.toList());

        return new PageableRestApiResponse<>(employeeList, employees);
    }

    @Secured(AUTH_WRITE)
    @PostMapping("v10/employees")
    public RestApiResponse<CreateEmployeeResponse> create(@Validated @RequestBody CreateEmployeeRequest createEmployeeRequest) {
        LOG.debug("create employee request :: {}", createEmployeeRequest);
        final boolean isValid = emailValidator.isValid(createEmployeeRequest.getEmail());
        if (!isValid) {
            throw new RestException(EMAIL_VALIDATION);
        }
        final EmployeeDTO employeeDTO = employeeService.create(createEmployeeRequest.getEmail(), createEmployeeRequest.getFullname(), createEmployeeRequest.getTitle());
        return new RestApiResponse<>(new CreateEmployeeResponse(employeeDTO));
    }

}
