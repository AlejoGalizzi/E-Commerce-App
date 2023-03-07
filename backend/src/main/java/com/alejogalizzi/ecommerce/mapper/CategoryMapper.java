package com.alejogalizzi.ecommerce.mapper;

import com.alejogalizzi.ecommerce.model.dto.CategoryDTO;
import com.alejogalizzi.ecommerce.model.entity.Category;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CategoryMapper {

  public static CategoryDTO entityToDto(Category category) {
    return new CategoryDTO(category.getId(), category.getName(),
        category.getProducts().stream().map(ProductMapper::entityToDto).collect(
            Collectors.toList()), category.getCreatedAt());
  }
}
