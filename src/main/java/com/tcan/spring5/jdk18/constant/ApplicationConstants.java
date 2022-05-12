package com.tcan.spring5.jdk18.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConstants {

    /*
     * Email format: A valid email address will have following format:
     *   [\\w\\.-]+: Begins with word characters, (may include periods and hypens).
     *   @: It must have a '@' symbol after initial characters.
     *   ([\\w\\-]+\\.)+: '@' must follow by more alphanumeric characters (may include hypens.).
     * This part must also have a "." to separate domain and subdomain names.
     *   [A-Z]{2,4}$ : Must end with two to four alphabets.
     *     (This will allow domain names with 2, 3 and 4 characters e.g pa, com, net, wxyz)
     *
     * Examples: Following email addresses will pass validation
     * - abc@xyz.net
     * - ab.c@tx.gov
     */
    public static final String EMAIL_EXPRESSION = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

    public static final String MDC_LOG_TRACK_ID = "logTrackId";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String TIME_ZONE_ISTANBUL = "Europe/Istanbul";

    public static final String APPLICATION_JSON = "application/json";

    /* auths */
    public static final String AUTH_READ = "ROLE_READ";
    public static final String AUTH_WRITE = "ROLE_WRITE";
}
