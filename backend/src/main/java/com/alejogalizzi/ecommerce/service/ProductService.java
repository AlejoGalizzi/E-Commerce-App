package com.alejogalizzi.ecommerce.service;

import com.alejogalizzi.ecommerce.exception.AlreadyRegister;
import com.alejogalizzi.ecommerce.exception.NotFoundException;
import com.alejogalizzi.ecommerce.mapper.ProductMapper;
import com.alejogalizzi.ecommerce.model.dto.ProductDTO;
import com.alejogalizzi.ecommerce.model.entity.Product;
import com.alejogalizzi.ecommerce.repository.ICategoryRepository;
import com.alejogalizzi.ecommerce.repository.IImageRepository;
import com.alejogalizzi.ecommerce.repository.IProductRepository;
import com.alejogalizzi.ecommerce.service.abstraction.IProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

  @Autowired
  private IProductRepository productRepository;

  @Autowired
  private ICategoryRepository categoryRepository;

  @Autowired
  private IImageRepository imageRepository;

  @Override
  public List<ProductDTO> getProducts() {
    return productRepository.findAll().stream().map(ProductMapper::entityToDto).toList();
  }

  @Override
  public ProductDTO getProductById(long id) {
    return ProductMapper.entityToDto(productRepository.findById(id).orElseThrow(
        () -> new NotFoundException(String.format("User with id %s is not found", id))));
  }


  @Override
  public ProductDTO createProduct(ProductDTO productDTO) {
    if (!productRepository.existsByName(productDTO.getName())) {
      Product product = productRepository.save(ProductMapper.dtoToNewEntity(productDTO,
          imageRepository.findById(productDTO.getImageId()).orElse(null),
          categoryRepository.findById(productDTO.getCategoryId()).orElse(null)));
      return ProductMapper.entityToDto(product);
    } else {
      throw new AlreadyRegister("Product already registered");
    }

  }

  @Override
  public ProductDTO editProductById(ProductDTO productDTO, long id) {
    Product productdb = productRepository.findById(id).orElse(null);
    if(productdb == null) {
      throw new NotFoundException(String.format("User with id of %s was not found", id));
    }
    productdb.setName(productDTO.getName());
    productdb.setDescription(productDTO.getDescription());
    productdb.setPrice(productDTO.getPrice());
    productdb.setStock(productDTO.getStock());
    return ProductMapper.entityToDto(productRepository.save(productdb));
  }

  @Override
  public void deleteById(long id) {
    if(!productRepository.existsById(id)) {
      throw new NotFoundException(String.format("User with id of %s was not found", id));
    }else productRepository.deleteById(id);
  }
}
