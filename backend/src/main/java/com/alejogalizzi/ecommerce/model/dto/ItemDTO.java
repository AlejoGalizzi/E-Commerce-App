package com.alejogalizzi.ecommerce.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

  private long id;

  @NotBlank(message = "Product id must not be null or empty")
  private long product_id;

  @JsonProperty(access = Access.READ_ONLY)
  private long order_id;

  @NotBlank(message = "Quantity must not be null or empty")
  @Min(1)
  @Max(100)
  private int quantity;

}
