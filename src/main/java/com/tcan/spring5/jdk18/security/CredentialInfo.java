package com.tcan.spring5.jdk18.security;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CredentialInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String user;
    @ToString.Exclude
    private String pass;
    private List<String> auths;
}
