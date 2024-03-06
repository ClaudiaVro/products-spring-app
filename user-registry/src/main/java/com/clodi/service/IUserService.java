package com.clodi.service;

import java.util.Optional;

import com.clodi.dto.SimpleUserDTO;
import com.clodi.entity.SimpleUser;
import com.clodi.entity.VerificationToken;

public interface IUserService {

	SimpleUser registerNewUserAccount(SimpleUserDTO userDTO);

	public void createVerificationToken(SimpleUser user, String token);

	public VerificationToken getVerificationToken(String verificationToken);

	public void enableRegisteredUser(SimpleUser user);

	Optional<SimpleUser> findUserById(Long id);
}
