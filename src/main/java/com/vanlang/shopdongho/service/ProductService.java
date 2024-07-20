package com.vanlang.shopdongho.service;


import com.vanlang.shopdongho.model.Product;
import com.vanlang.shopdongho.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepository.findById(id);
    }

    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(@NonNull Product product){
        Product existingProduct = productRepository.findById(product.getId()).orElseThrow(()
                -> new IllegalStateException("Product with id " + product.getId() + "does not exist!"));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setCategory(product.getCategory());
        return productRepository.save(existingProduct);
    }

    public void deleteProductById(Long id){
        if(!productRepository.existsById(id)){
            throw new IllegalStateException("Product with id " + id + "does not exist!");
        }
        productRepository.deleteById(id);
    }




}
