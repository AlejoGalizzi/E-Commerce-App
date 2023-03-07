package com.alejogalizzi.ecommerce.mapper;

import com.alejogalizzi.ecommerce.model.dto.ImageDTO;
import com.alejogalizzi.ecommerce.model.dto.ProductDTO;
import com.alejogalizzi.ecommerce.model.entity.Category;
import com.alejogalizzi.ecommerce.model.entity.Image;
import com.alejogalizzi.ecommerce.model.entity.Product;

public final class ProductMapper {

  public static ProductDTO entityToDto(Product product) {
    return new ProductDTO(product.getId(), product.getName(), product.getName(), product.getPrice(),
        product.getStock(), product.getCategory().getId(), product.getImage().getId(),
        product.getCreatedAt(), product.getUpdatedAt());
  }

  public static Product dtoToNewEntity(ProductDTO productDTO, Image image, Category category) {
    Product product = new Product();

    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setPrice(productDTO.getPrice());
    product.setStock(productDTO.getStock());
    product.setImage(image);
    product.setCategory(category);
    return product;
  }

}
