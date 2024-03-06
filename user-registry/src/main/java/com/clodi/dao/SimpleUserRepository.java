package com.clodi.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clodi.entity.SimpleUser;

@Repository
public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {

	Optional<SimpleUser> findByUsername(String name);

	Optional<SimpleUser> findByEmail(String email);

}
