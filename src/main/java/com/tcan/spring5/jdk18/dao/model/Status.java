package com.tcan.spring5.jdk18.dao.model;

import com.tcan.spring5.jdk18.api.fault.exception.ServiceRuntimeException;

import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.STATUS_VALIDATION;

public enum Status {
    ACTIVE,
    INACTIVE;

    public static Status get(String status) {
        for (Status employeeStatus : values()) {
            if (employeeStatus.name().equalsIgnoreCase(status)) {
                return employeeStatus;
            }
        }
        throw new ServiceRuntimeException(STATUS_VALIDATION);
    }

    public static Status getOrDefault(String status, Status defaultStatus) {
        for (Status employeeStatus : values()) {
            if (employeeStatus.name().equalsIgnoreCase(status)) {
                return employeeStatus;
            }
        }
        return defaultStatus;
    }
}
