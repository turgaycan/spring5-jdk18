package com.tcan.spring5.jdk18.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import java.util.UUID;

import static com.tcan.spring5.jdk18.constant.ApplicationConstants.MDC_LOG_TRACK_ID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MdcUtils {

    public static String getOrGenerateLogTrackKey() {
        final var logTrackKey = getLogTrackKey();
        if (StringUtils.isStripBlank(logTrackKey)) {
            return generateAndSetLogTrackKey();
        }
        return logTrackKey;
    }

    public static String getLogTrackKey() {
        return MDC.get(MDC_LOG_TRACK_ID);
    }

    public static void setLogTrackKey(String logTrackKey) {
        MDC.put(MDC_LOG_TRACK_ID, logTrackKey);
    }

    public static String generateAndSetLogTrackKey() {
        final var generatedLogTrackKey = generateNewLogTrackKey();
        setLogTrackKey(generatedLogTrackKey);
        return generatedLogTrackKey;
    }

    public static String generateNewLogTrackKey() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void removeLogTrackKey() {
        MDC.remove(MDC_LOG_TRACK_ID);
    }
}
