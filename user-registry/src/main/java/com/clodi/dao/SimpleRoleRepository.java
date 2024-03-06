package com.clodi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.clodi.entity.SimpleRole;

@Repository
public interface SimpleRoleRepository extends JpaRepository<SimpleRole, Long> {

}
