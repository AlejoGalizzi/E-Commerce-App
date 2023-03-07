package com.alejogalizzi.ecommerce.model.dto;

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

  private String name;

  private String description;

  private BigDecimal price;

  private int stock;

  private long categoryId;

  private long imageId;


  private Date createdAt;

  private Date updatedAt;
}
