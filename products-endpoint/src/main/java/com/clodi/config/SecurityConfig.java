package com.clodi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * @author Claudia Vidican
 */
@Configuration public class SecurityConfig {

    /*
     * @formatter:off
     */
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(r -> r.anyRequest().permitAll())
                        .csrf(c -> c.disable());
        return http.build();
    }
    /*
     * @formatter:on
     */

}
