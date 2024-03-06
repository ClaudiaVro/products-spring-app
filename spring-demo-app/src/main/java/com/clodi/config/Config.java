package com.clodi.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration @EnableFeignClients("com.clodi.proxy") @EnableWebSecurity public class Config {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public Config(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }

    @Bean SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                                        authorizeRequests -> authorizeRequests.requestMatchers("/products/history/**").authenticated().anyRequest().permitAll())
                        .oauth2Login(withDefaults()).oauth2Client(withDefaults())
                        .logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler()));

        return http.build();
    }

    //    @Bean
    //    public ErrorDecoder errorDecoder() {
    //        return new InvalidProductFeignErrorDecoder();
    //    }

    /**
     * Unused: might need rework.
     */
    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                        new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);

        // Sets the location that the End-User's User Agent will be redirected to
        // after the logout has been performed at the Provider
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("http://localhost:9000/oauth2/revoke");

        return oidcLogoutSuccessHandler;
    }
}
