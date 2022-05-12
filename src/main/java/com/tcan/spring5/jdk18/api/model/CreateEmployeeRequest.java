package com.tcan.spring5.jdk18.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.*;

@Getter
@Setter
@ToString
public class CreateEmployeeRequest {
    @NotEmpty(message = DESC_EMAIL_VALIDATION)
    private String email;
    @NotEmpty(message = DESC_FULLNAME_VALIDATION)
    private String fullname;
    @NotEmpty(message = DESC_TITLE_VALIDATION)
    private String title;

}
