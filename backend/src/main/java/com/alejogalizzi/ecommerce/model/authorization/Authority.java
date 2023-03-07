package com.alejogalizzi.ecommerce.model.authorization;

import com.alejogalizzi.ecommerce.util.constants.Roles;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "authorities")
@Entity
public class Authority implements GrantedAuthority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;


  private String authority;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  public static SimpleGrantedAuthority USER_ROLE = new SimpleGrantedAuthority(Roles.ROLE_USER.name());
  public static SimpleGrantedAuthority ADMIN_ROLE = new SimpleGrantedAuthority(Roles.ROLE_ADMIN.name());
}
