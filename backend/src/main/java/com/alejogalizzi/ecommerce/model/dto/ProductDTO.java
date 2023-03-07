package com.alejogalizzi.ecommerce.model.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
  private long id;

  @NotBlank
  @Size(min = 3, max = 24)
  private String name;

  @NotBlank
  private String description;

  @NotBlank
  @Min(1)
  private BigDecimal price;

  @NotBlank
  @Min(3)
  private int stock;

  private long categoryId;

  private long imageId;

  private Date createdAt;

  private Date updatedAt;
}
