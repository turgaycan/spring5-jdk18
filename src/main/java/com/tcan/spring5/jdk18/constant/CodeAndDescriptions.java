package com.tcan.spring5.jdk18.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

import static java.util.Map.entry;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CodeAndDescriptions {
    //general
    public static final String NO_ERROR = "0";
    public static final String DESC_NO_ERROR = "No Error";

    public static final String UNKNOWN_ERROR = "9999";
    public static final String DESC_UNKNOWN_ERROR = "Unknown Error";

    public static final String ERR_INPUT_VALIDATION = "10000";
    public static final String DESC_ERR_INPUT_VALIDATION = "Validation error occurred";
    public static final String ERR_INPUT_FORMAT = "10001";
    public static final String DESC_ERR_INPUT_FORMAT = "Input parameter is incorrect";
    public static final String EMAIL_VALIDATION = "10100";
    public static final String DESC_EMAIL_VALIDATION = "Email is invalid";
    public static final String STATUS_VALIDATION = "10200";
    public static final String DESC_STATUS_VALIDATION = "Status is invalid";

    public static final String USER_EXISTS = "10300";
    public static final String DESC_USER_EXISTS_VALIDATION = "User is already exists";
    public static final String BAD_CREDENTIALS = "10301";
    public static final String DESC_BAD_CREDENTIALS = "Credentials are not valid";
    public static final String ACCESS_DENIED = "10302";
    public static final String DESC_ACCESS_DENIED = "Access denied";

    //validation descriptions
    public static final String DESC_FULLNAME_VALIDATION = "Fullname cannot tbe empty";
    public static final String DESC_TITLE_VALIDATION = "Title cannot tbe empty";

    public static final Map<String, String> CODE_N_DESC = Map.ofEntries(
            entry(NO_ERROR, DESC_NO_ERROR),
            entry(ERR_INPUT_VALIDATION, DESC_ERR_INPUT_VALIDATION),
            entry(ERR_INPUT_FORMAT, DESC_ERR_INPUT_FORMAT),
            entry(EMAIL_VALIDATION, DESC_EMAIL_VALIDATION),
            entry(USER_EXISTS, DESC_USER_EXISTS_VALIDATION),
            entry(STATUS_VALIDATION, DESC_STATUS_VALIDATION),
            entry(BAD_CREDENTIALS, DESC_BAD_CREDENTIALS),
            entry(ACCESS_DENIED, DESC_ACCESS_DENIED),
            entry(UNKNOWN_ERROR, DESC_UNKNOWN_ERROR)
    );

}
