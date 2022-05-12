package com.tcan.spring5.jdk18.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

    public static boolean isNotStripBlank(String value) {
        return !isStripBlank(value);
    }

    public static boolean isStripBlank(String value) {
        return value == null || value.strip() == null || value.strip().length() == 0;
    }
}
