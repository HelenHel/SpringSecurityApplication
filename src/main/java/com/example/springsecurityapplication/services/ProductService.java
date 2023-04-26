package com.example.springsecurityapplication.services;

import com.example.springsecurityapplication.models.Category;
import com.example.springsecurityapplication.models.Product;
import com.example.springsecurityapplication.repositories.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    public List<Product> getIgnoreCase(String name){
        productRepository.findByTitleContainingIgnoreCase(name);
        return null;
    }
    public List<Product> getTitleAndPriceGreater(String title, float ot, float Do){
        productRepository.findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(title, ot, Do) ;
        return null;
    }
    public List<Product> getTitlePriceAsc(String title, float ot, float Do){
        productRepository.findByTitleOrderByPriceAsc(title, ot, Do);
        return null;
    }
    public List<Product> getTitlePriceDesc(String title, float ot, float Do){
        productRepository.findByTitleOrderByPriceDesc(title, ot, Do);
        return null;
    }
    public List<Product> getTitleCategoryPriceAsc(String title, float ot, float Do, int category){
        productRepository.findByTitleAndCategoryOrderByPriceAsc(title, ot, Do, category);
        return null;
    }
    public List<Product> getTitleCategoryPriceDesc(String title, float ot, float Do, int category){
        productRepository.findByTitleAndCategoryOrderByPriceDesc(title, ot, Do, category);
        return null;
    }

//  метод позволяет получить список всех товаров
    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }
//  метод позволяет получить товар по id
    public Product getProductId(int id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }
//   метод позволяет сохранить товар
    @Transactional
    public void saveProduct(Product product, Category category){
        product.setCategory(category);
        productRepository.save(product);
    }
//  метод позволяет обновить данные о товаре
    @Transactional
    public void updateProduct(int id, Product product){
        product.setId(id);
        productRepository.save(product);
    }
//  метод позволяет удалить товар по id
    @Transactional
    public void deleteProduct(int id){
        productRepository.deleteById(id);
    }
}
