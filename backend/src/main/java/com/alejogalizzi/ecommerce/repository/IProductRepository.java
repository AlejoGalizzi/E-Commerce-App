package com.alejogalizzi.ecommerce.repository;

import com.alejogalizzi.ecommerce.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {

  boolean existsByName(String name);
}
