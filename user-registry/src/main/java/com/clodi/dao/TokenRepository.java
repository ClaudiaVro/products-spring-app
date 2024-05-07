package com.clodi.dao;

import com.clodi.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<VerificationToken, Integer> {

    VerificationToken findByToken(String verificationToken);

}
