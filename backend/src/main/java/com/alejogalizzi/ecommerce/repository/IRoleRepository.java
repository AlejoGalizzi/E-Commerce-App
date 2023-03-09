package com.alejogalizzi.ecommerce.repository;

import com.alejogalizzi.ecommerce.model.authorization.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

}
