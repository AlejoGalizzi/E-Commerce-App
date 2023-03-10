package com.alejogalizzi.ecommerce.controller;

import com.alejogalizzi.ecommerce.model.dto.ProductDTO;
import com.alejogalizzi.ecommerce.service.abstraction.IProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  @Autowired
  private IProductService productService;

  @GetMapping
  private ResponseEntity<List<ProductDTO>> getProducts() {
    return ResponseEntity.ok(productService.getProducts());
  }
//   or hasRole(Roles.ROLE_ADMIN.name())

  @GetMapping("/{id}")
  private ResponseEntity<ProductDTO> getProductById(@PathVariable long id) {
    return ResponseEntity.ok(productService.getProductById(id));
  }

  @PostMapping
  private ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
  }
}
