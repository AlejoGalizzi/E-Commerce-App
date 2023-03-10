package com.alejogalizzi.ecommerce.service.abstraction;

import com.alejogalizzi.ecommerce.model.dto.ProductDTO;
import java.util.List;

public interface IProductService {

  List<ProductDTO> getProducts();

  ProductDTO getProductById(long id);

  ProductDTO createProduct(ProductDTO productDTO);

  ProductDTO editProductById(ProductDTO productDTO, long id);

  void deleteById(long id);
}
