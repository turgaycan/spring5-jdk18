package com.tcan.spring5.jdk18.dao.model;

import com.tcan.spring5.jdk18.service.model.EmployeeDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Entity
@DynamicUpdate
@NoArgsConstructor
@Table(name = "employees")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullname;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "create_date", nullable = false)
    private Date createDate;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;


    public Employee(String email, String fullname, String title) {
        this.email = email;
        this.fullname = fullname;
        this.title = title;
        this.createDate = new Date();
        this.status = Status.ACTIVE;
    }

    public EmployeeDTO toDTO() {
        final EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(getId());
        employeeDTO.setEmail(getEmail());
        employeeDTO.setFullname(getFullname());
        employeeDTO.setTitle(getTitle());
        employeeDTO.setCreateDate(getCreateDate());
        employeeDTO.setStatus(getStatus());
        return employeeDTO;
    }
}


