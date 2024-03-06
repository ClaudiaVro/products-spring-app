package com.clodi.service;

import com.clodi.dao.SimpleUserRepository;
import com.clodi.dao.TokenRepository;
import com.clodi.dto.SimpleUserDTO;
import com.clodi.entity.SimpleRole;
import com.clodi.entity.SimpleUser;
import com.clodi.entity.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private SimpleUserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

//    @Autowired
//    private PasswordEncoder encoder;

    @Override
    public SimpleUser registerNewUserAccount(SimpleUserDTO userDTO) {
        SimpleUser user = new SimpleUser();
        user.setEnabled(false);
//        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());

        SimpleRole authorities = new SimpleRole(user, SimpleRole.USER);
        user.setRoles(List.of(authorities));
        return userRepository.save(user);

    }

    @Override
    public void createVerificationToken(SimpleUser user, String tokenStr) {
        VerificationToken token = new VerificationToken(tokenStr, user);
        tokenRepository.save(token);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    @Override
    public void enableRegisteredUser(SimpleUser user) {
        userRepository.save(user);
    }

    public Optional<SimpleUser> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Optional<SimpleUser> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<SimpleUser> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
