package com.tcan.spring5.jdk18.api.validator;

import com.tcan.spring5.jdk18.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.tcan.spring5.jdk18.constant.ApplicationConstants.EMAIL_EXPRESSION;

@Component
public class EmailValidator {

    public boolean isValidIfNotBlank(String email) {
        if (StringUtils.isStripBlank(email)) {
            return true;
        }
        return checkEmail(email);
    }

    public boolean isValid(String email) {
        if (StringUtils.isStripBlank(email)) {
            return false;
        }

        return checkEmail(email);
    }

    private boolean checkEmail(String email) {
        // Make the comparison case-insensitive.
        final Pattern pattern = Pattern.compile(EMAIL_EXPRESSION, Pattern.CASE_INSENSITIVE);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}
