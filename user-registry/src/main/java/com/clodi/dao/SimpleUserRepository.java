package com.clodi.dao;

import java.util.Optional;

import com.clodi.entity.SimpleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface SimpleUserRepository extends JpaRepository<SimpleUser, Long> {

    Optional<SimpleUser> findByUsername(String name);

    Optional<SimpleUser> findByEmail(String email);

}
