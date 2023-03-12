package com.alejogalizzi.ecommerce.model.dto;

import com.alejogalizzi.ecommerce.model.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

public class OrderDTO {

  private long id;

  @NotBlank(message = "Item id list must not be empty or null")
  @Size(min = 1, message = "Item id list must have at least one id")
  private List<Long> items_id;

  @NotBlank(message = "User id must not be null or blank")
  private long user_id;

  private double totalPrice;
}
