package com.tcan.spring5.jdk18.service;

import com.tcan.spring5.jdk18.common.model.Pager;
import com.tcan.spring5.jdk18.common.model.SearchResult;
import com.tcan.spring5.jdk18.service.model.EmployeeDTO;
import com.tcan.spring5.jdk18.service.model.SearchEmployeeQuery;

public interface EmployeeService {
    EmployeeDTO create(String email, String fullname, String title);

    SearchResult<EmployeeDTO> searchEmployees(SearchEmployeeQuery query, Pager pager);
}
