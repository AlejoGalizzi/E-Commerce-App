package com.alejogalizzi.ecommerce.util.seeder;

import com.alejogalizzi.ecommerce.model.authorization.Privilege;
import com.alejogalizzi.ecommerce.model.authorization.Role;
import com.alejogalizzi.ecommerce.model.authorization.User;
import com.alejogalizzi.ecommerce.repository.IPrivilegeRepository;
import com.alejogalizzi.ecommerce.repository.IRoleRepository;
import com.alejogalizzi.ecommerce.repository.IUserRepository;
import com.alejogalizzi.ecommerce.util.constants.Privileges;
import com.alejogalizzi.ecommerce.util.constants.Roles;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private IRoleRepository roleRepository;
  @Autowired
  private IPrivilegeRepository privilegeRepository;


  @Override
  public void run(String... args) throws Exception {
    seedPrivilegeTable();
    seedRoleTable();
    seedUserTable();

  }

  private void seedPrivilegeTable() {
    if (privilegeRepository.count() == 0) {
      createPrivileges();
    }
  }

  private void createPrivileges() {
    Privilege write = new Privilege();
    write.setName(Privileges.WRITE.name());
    Privilege read = new Privilege();
    read.setName(Privileges.READ.name());
    Privilege edit = new Privilege();
    edit.setName(Privileges.EDIT.name());
    Privilege delete = new Privilege();
    delete.setName(Privileges.DELETE.name());
    privilegeRepository.saveAll(List.of(read, write, edit, delete));
  }

  private void seedRoleTable() {
    if (roleRepository.count() == 0) {
      createRoles();
    }
  }

  private void createRoles() {
    List<Privilege> privileges = privilegeRepository.findAll();
    Role admin = new Role();
    admin.setName(Roles.ROLE_ADMIN.name());
    admin.setPrivileges(privileges.stream().collect(Collectors.toSet()));
    Role user = new Role();
    user.setName(Roles.ROLE_USER.name());
    privileges.stream()
        .filter(privilege -> Objects.equals(privilege.getName(), Privileges.READ.name())).findFirst()
        .ifPresent(user::addPrivilege);
    roleRepository.saveAll(List.of(admin,user));
  }

  private void seedUserTable() {
    if (userRepository.count() == 0) {
      createUsers();
    }
  }

  private void createUsers() {
    List<Role> roles = roleRepository.findAll();
    for (int index = 0; index < 2; index++) {
      createUser(USERNAMES.get(index),
          PASSWORDS.get(index), Roles.ROLE_ADMIN.name(), roles);
    }
    for (int index = 2; index < 4; index++) {
      createUser(USERNAMES.get(index),
          PASSWORDS.get(index), Roles.ROLE_USER.name(), roles);
    }
  }

  private void createUser(String username, String password, String role, List<Role> roles) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(passwordEncoder.encode(password));
    if (Objects.equals(role, "ROLE_ADMIN")) {
      user.setRoles(roles.stream().filter(dbRole ->
          Objects.equals(dbRole.getName(), Roles.ROLE_ADMIN.name())
          || Objects.equals(dbRole.getName(), Roles.ROLE_USER.name())).collect(
          Collectors.toSet()));
    } else {
      user.setRoles(
          roles.stream().filter(dbRole -> Objects.equals(dbRole.getName(), Roles.ROLE_USER.name())).collect(
              Collectors.toSet()));
    }
    userRepository.save(user);
  }
}
