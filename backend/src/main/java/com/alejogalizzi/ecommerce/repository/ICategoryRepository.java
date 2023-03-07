package com.alejogalizzi.ecommerce.repository;

import com.alejogalizzi.ecommerce.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
