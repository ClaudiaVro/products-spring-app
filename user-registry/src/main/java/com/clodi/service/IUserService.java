package com.clodi.service;

import java.util.Optional;

import com.clodi.dto.SimpleUserDTO;
import com.clodi.entity.SimpleUser;
import com.clodi.entity.VerificationToken;

public interface IUserService {

    SimpleUser registerNewUserAccount(SimpleUserDTO userDTO);

    void createVerificationToken(SimpleUser user, String token);

    VerificationToken getVerificationToken(String verificationToken);

    void enableRegisteredUser(SimpleUser user);

    Optional<SimpleUser> findUserById(Long id);
}
