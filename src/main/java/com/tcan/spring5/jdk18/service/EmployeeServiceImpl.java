package com.tcan.spring5.jdk18.service;

import com.tcan.spring5.jdk18.api.fault.exception.ServiceRuntimeException;
import com.tcan.spring5.jdk18.common.model.Pager;
import com.tcan.spring5.jdk18.common.model.SearchResult;
import com.tcan.spring5.jdk18.dao.EmployeeRepository;
import com.tcan.spring5.jdk18.dao.model.Employee;
import com.tcan.spring5.jdk18.dao.model.Status;
import com.tcan.spring5.jdk18.service.model.EmployeeDTO;
import com.tcan.spring5.jdk18.service.model.SearchEmployeeQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.USER_EXISTS;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public EmployeeDTO create(String email, String fullname, String title) {
        final Optional<Employee> optionalEmployee = employeeRepository.findByEmailAndStatus(email.strip().toLowerCase(), Status.ACTIVE);
        if (optionalEmployee.isPresent()) {
            throw new ServiceRuntimeException(USER_EXISTS);
        }
        final Employee employee = new Employee(email, fullname, title);
        final Employee persisted = employeeRepository.saveAndFlush(employee);
        return persisted.toDTO();
    }

    @Override
    @Transactional(readOnly = true)
    public SearchResult<EmployeeDTO> searchEmployees(SearchEmployeeQuery query, Pager pager) {
        final Pageable pageable = pager.toPageableWithSort(Sort.by("createDate").descending());
        final Page<Employee> employees = employeeRepository.searchEmployees(query, pageable);
        final List<EmployeeDTO> employeeDTOList = employees.getContent()
                .stream()
                .map(Employee::toDTO)
                .collect(Collectors.toList());
        return new SearchResult<>(employeeDTOList, new SearchResult<>(employees));
    }
}
