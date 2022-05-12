package com.tcan.spring5.jdk18.api.model;

import com.tcan.spring5.jdk18.service.model.EmployeeDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class CreateEmployeeResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String fullname;
    private String title;
    private Date createDate;

    public CreateEmployeeResponse(EmployeeDTO employeeDTO) {
        this.id = employeeDTO.getId();
        this.fullname = employeeDTO.getFullname();
        this.title = employeeDTO.getTitle();
        this.createDate = employeeDTO.getCreateDate();
    }
}
