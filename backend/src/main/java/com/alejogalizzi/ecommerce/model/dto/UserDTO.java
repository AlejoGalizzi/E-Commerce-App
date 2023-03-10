package com.alejogalizzi.ecommerce.model.dto;

import com.alejogalizzi.ecommerce.util.constants.Roles;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private long id;

  @NotBlank
  @Size(min = 4, max = 24, message = "Username must have a lenght between 4 and 24 characters")
  private String username;

  @NotBlank(message = "Password must not be null or empty")
  @Size(min = 8, max = 16, message = "Password must have between 8 and 16 characters")
  private String password;

  @JsonProperty(access = Access.READ_ONLY)
  private Set<Roles> roles;
}
