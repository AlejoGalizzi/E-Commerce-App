package com.alejogalizzi.ecommerce.repository;

import com.alejogalizzi.ecommerce.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {

}
