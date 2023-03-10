package com.alejogalizzi.ecommerce.repository;

import com.alejogalizzi.ecommerce.model.authorization.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  Optional<User> findByEmail(String email);
}
