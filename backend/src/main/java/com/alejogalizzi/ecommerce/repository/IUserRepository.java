package com.alejogalizzi.ecommerce.repository;

import com.alejogalizzi.ecommerce.model.authorization.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

  User findByUsername(String username);

}
