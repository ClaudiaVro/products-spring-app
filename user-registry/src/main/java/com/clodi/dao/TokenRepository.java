package com.clodi.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clodi.entity.VerificationToken;

public interface TokenRepository extends JpaRepository<VerificationToken, Integer> {

	VerificationToken findByToken(String verificationToken);

}
