package com.tcan.spring5.jdk18.api.model;

import com.tcan.spring5.jdk18.service.model.EmployeeDTO;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@ToString
public class EmployeeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Long id;
    private final String email;
    private final String fullname;
    private final String title;
    private final Date createDate;

    public EmployeeResponse(EmployeeDTO employeeDTO) {
        this.id = employeeDTO.getId();
        this.email = employeeDTO.getEmail();
        this.fullname = employeeDTO.getFullname();
        this.title = employeeDTO.getTitle();
        this.createDate = employeeDTO.getCreateDate();
    }
}
