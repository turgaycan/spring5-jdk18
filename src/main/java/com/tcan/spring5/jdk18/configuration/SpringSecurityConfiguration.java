package com.tcan.spring5.jdk18.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcan.spring5.jdk18.security.CustomUserDetailsService;
import com.tcan.spring5.jdk18.security.JsonSecurityExceptionHandler;
import com.tcan.spring5.jdk18.security.RestApiAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${spring.application.name}")
    private final String applicationName;
    private final ObjectMapper objectMapper;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        final DaoAuthenticationProvider authenticationProvider = new RestApiAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService);
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final JsonSecurityExceptionHandler handler = new JsonSecurityExceptionHandler(applicationName, objectMapper);

        http
                .httpBasic()
                .authenticationEntryPoint(handler)
                .and()

                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                .authorizeRequests()
                .antMatchers("/api/rest/**")
                .authenticated()
                .and()

                .exceptionHandling()
                .authenticationEntryPoint(handler)
                .accessDeniedHandler(handler)
                .and()

                .csrf()
                .disable();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(11);
    }
}
