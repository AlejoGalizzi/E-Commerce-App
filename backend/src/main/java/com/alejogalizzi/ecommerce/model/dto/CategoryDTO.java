package com.alejogalizzi.ecommerce.model.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {

  private long id;

  private String name;

  private List<ProductDTO> products;

  private Date createdAt;



}
