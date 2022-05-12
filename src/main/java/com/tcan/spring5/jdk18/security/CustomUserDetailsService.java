package com.tcan.spring5.jdk18.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.tcan.spring5.jdk18.constant.ApplicationConstants.AUTH_READ;
import static com.tcan.spring5.jdk18.constant.ApplicationConstants.AUTH_WRITE;
import static com.tcan.spring5.jdk18.constant.CodeAndDescriptions.DESC_ACCESS_DENIED;


@Slf4j
@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private Map<String, RestApiUser> userMap = new ConcurrentHashMap<>();
    private final List<String> readAuths = Arrays.asList(AUTH_READ);
    private final List<String> allAuths = Arrays.asList(AUTH_READ, AUTH_WRITE);

    @PostConstruct
    public void init() {
        //tc1234
        userMap.put("turgay", new RestApiUser(new CredentialInfo("turgay", "$2a$11$GtAi6UixqM0XjuDhNcsdaOgcszvFM5u6D4pzc2z4SWnGyZ630ffjS", allAuths)));
        //sk1234
        userMap.put("serdar", new RestApiUser(new CredentialInfo("serdar", "$2a$11$uues4hufY0vhc2SnojarjeA2Jefm8DX6jFK9Py1dYK5o.gIPvf3Ka", allAuths)));
        //fb1234
        userMap.put("fatih", new RestApiUser(new CredentialInfo("fatih", "$2a$11$z1C0H6f6MlpoaKw7bTVNgOotSP3zTZGbJqsdslUXsaVfdG49pZIbK", readAuths)));
        LOG.info("Loaded {} RestApiUsers for REST authentication", userMap.size());
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return Optional.ofNullable(this.userMap.get(username))
                .orElseThrow(() -> new UsernameNotFoundException(DESC_ACCESS_DENIED));
    }

}
