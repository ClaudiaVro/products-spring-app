package com.clodi.config;

import java.util.List;
import java.util.stream.Collectors;

import com.clodi.model.SimpleRole;
import com.clodi.model.SimpleUser;
import com.clodi.proxy.UserProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Claudia Vidican
 */
@Component public class UserRegistryAuthenticationProvider implements AuthenticationProvider {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserProxy userProxy;

    @Override public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        SimpleUser user = userProxy.getUser(username);
        if (user == null) {
            throw new BadCredentialsException("authentication failed");
        }
        String password = user.getPassword();
        if (passwordEncoder.matches(pwd, password)) {
            List<SimpleRole> roles = user.getRoles();
            List<SimpleGrantedAuthority> authorities = roles.stream().map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
        }
        return null;
    }

    @Override public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
