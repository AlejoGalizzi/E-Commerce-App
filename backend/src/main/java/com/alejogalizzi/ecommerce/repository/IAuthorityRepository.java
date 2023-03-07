package com.alejogalizzi.ecommerce.repository;

import com.alejogalizzi.ecommerce.model.authorization.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAuthorityRepository extends JpaRepository<Authority, Long> {

}
