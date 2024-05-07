package com.clodi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration @EnableFeignClients("com.clodi.proxy") @EnableWebSecurity public class SecurityConfig {

    /*
     * @formatter:off
     */
    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(r -> r
                        .requestMatchers("/products/history/**","/test").authenticated()
                        .anyRequest().permitAll())
                        .formLogin(withDefaults())
                        .httpBasic(withDefaults())
                        .csrf(c -> c.disable());
        return http.build();
    }
    /*
     * @formatter:on
     */

    @Bean PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
