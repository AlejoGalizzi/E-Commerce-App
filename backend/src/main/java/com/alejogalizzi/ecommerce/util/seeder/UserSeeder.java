package com.alejogalizzi.ecommerce.util.seeder;

import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.model.authorization.Authority;
import com.alejogalizzi.ecommerce.repository.IUserRepository;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserSeeder implements CommandLineRunner {

  private static final List<String> USERNAMES = List.of("Luitgard", "Christel", "Stefanie",
      "Oswald");

  private static final List<String> PASSWORDS = List.of("myPassword", "myPassword123",
      "myPassword653",
      "myPassword6123");

  private static final List<Authority> ROLES = List.of(new Authority(), new Authority());
  @Autowired
  private PasswordEncoder passwordEncoder;


  @Autowired
  private IUserRepository userRepository;

  @Override
  public void run(String... args) throws Exception {
    seedUserTable();
  }

  private void seedUserTable() {
    if (userRepository.count() == 0) {
      createUsers();
    }
  }

  private void createUsers() {
    for (int index = 0; index < 2; index++) {
      createUser(USERNAMES.get(index),
          PASSWORDS.get(index), "ROLE_ADMIN");
    }
    for (int index = 2; index < 4; index++) {
      createUser(USERNAMES.get(index),
          PASSWORDS.get(index), "ROLE_USER");
    }
  }

  private void createUser(String username, String password, String role) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    if(Objects.equals(role, "ROLE_ADMIN")) {
      Authority authority_user = new Authority();
      authority_user.setAuthority("ROLE_USER");
      authority_user.setUser(user);
      user.getAuthorities().add(authority_user);
    }
    Authority authority = new Authority();
    authority.setAuthority(role);
    authority.setUser(user);
    user.getAuthorities().add(authority);
    userRepository.save(user);
  }
}
