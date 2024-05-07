package com.clodi.repository;

import com.clodi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface ProductRepository extends JpaRepository<Product, Long> {

}
