package com.tcan.spring5.jdk18.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.DESC_BAD_CREDENTIALS;


@Slf4j
public class RestApiAuthenticationProvider extends DaoAuthenticationProvider {
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (authentication.getCredentials() == null) {
            LOG.warn("Authentication failed: no credentials provided");
            throw new BadCredentialsException(DESC_BAD_CREDENTIALS);
        }

        final String presentedPassword = authentication.getCredentials().toString();

        if (userDetails instanceof RestApiUser) {
            ((RestApiUser) userDetails).checkPassword(getPasswordEncoder(), authentication.getCredentials().toString());
            return;
        }

        if (!getPasswordEncoder().matches(presentedPassword, userDetails.getPassword())) {
            LOG.warn("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(DESC_BAD_CREDENTIALS);
        }
    }
}
