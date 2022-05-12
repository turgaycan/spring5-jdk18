package com.tcan.spring5.jdk18.service.model;

import com.tcan.spring5.jdk18.dao.model.Status;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class SearchEmployeeQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String email;
    private String fullname;
    private String title;
    private Date createDate;
    private Status status;
}
