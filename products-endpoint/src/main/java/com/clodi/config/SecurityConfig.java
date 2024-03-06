package com.clodi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Claudia Vidican
 */
@EnableWebSecurity public class SecurityConfig {

    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //        http.mvcMatcher("/receipts/**")
        //                .authorizeRequests()
        //                .mvcMatchers("/receipts/**")
        //                .access("hasAuthority('SCOPE_history.read')")
        //                .and()
        //                .oauth2ResourceServer()
        //                .jwt();
        //        return http.build();

        return http.authorizeHttpRequests(r -> r.requestMatchers("/receipts/**").authenticated()).build();
    }
}
