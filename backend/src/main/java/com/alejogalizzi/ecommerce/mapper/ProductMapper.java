package com.alejogalizzi.ecommerce.mapper;

import com.alejogalizzi.ecommerce.model.dto.ProductDTO;
import com.alejogalizzi.ecommerce.model.entity.Category;
import com.alejogalizzi.ecommerce.model.entity.Image;
import com.alejogalizzi.ecommerce.model.entity.Product;

public final class ProductMapper {

  public static ProductDTO entityToDto(Product product) {
    ProductDTO productDTO = new ProductDTO();
    productDTO.setName(product.getName());
    productDTO.setDescription(product.getDescription());
    productDTO.setPrice(product.getPrice());
    productDTO.setStock(product.getStock());
    if(product.getCategory() != null) {
      productDTO.setCategoryId(product.getCategory().getId());
    }
    if(product.getImage() != null) {
      productDTO.setImageId(product.getImage().getId());
    }
    return productDTO;
  }

  public static Product dtoToNewEntity(ProductDTO productDTO, Image image, Category category) {
    Product product = new Product();
    product.setName(productDTO.getName());
    product.setDescription(productDTO.getDescription());
    product.setPrice(productDTO.getPrice());
    product.setStock(productDTO.getStock());
    if(image != null) {
      product.setImage(image);
    }
    if(category != null) {
      product.setCategory(category);
    }
    return product;
  }

}
