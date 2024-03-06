package com.clodi.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//import com.clodi.dao.SimpleUserRepository;
//import com.clodi.entity.SimpleRole;
//import com.clodi.entity.SimpleUser;
//import com.clodi.security.SimpleUserDetails;
//import org.springframework.stereotype.Service;

//@Service
//public class SimpleUserDetailsService implements UserDetailsService {
//
//	private final SimpleUserRepository userRepository;
//
//	public SimpleUserDetailsService(SimpleUserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//		Optional<SimpleUser> userOpt = userRepository.findByUsername(username);
//
//		SimpleUserDetails details = userOpt.map(user -> {
//			Collection<GrantedAuthority> authorities = getAuthorities(user.getRoles());
//			String repoUsername = user.getUsername();
//			String password = user.getPassword();
//			boolean enabled = user.isEnabled();
//			return new SimpleUserDetails(repoUsername, password, enabled, true, true, true, authorities, user.getId());
//		}).orElseThrow();
//
//		return details;
//	}
//
//	private Collection<GrantedAuthority> getAuthorities(List<SimpleRole> roles) {
//		return roles.stream()
//				.map(a -> new SimpleGrantedAuthority(a.getRole())).collect(Collectors.toList());
//	}
//
//}
