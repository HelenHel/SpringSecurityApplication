package com.example.springsecurityapplication.repositories;

import com.example.springsecurityapplication.models.Category;
import com.example.springsecurityapplication.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

//  поиск всех товаров по части наименования товара в независимости от регистра
    List<Product> findByTitleContainingIgnoreCase(String name);

//  поиск по наименованию и фильтрация по диапозону цены
    @Query(value = "select * from product where ((lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '1?%')) and (price >= ?2 and price <= ?3)", nativeQuery = true)
    List<Product> findByTitleAndPriceGreaterThanEqualAndPriceLessThanEqual(String title, float ot, float Do);

//  поиск по наименованию и фильтрация по диапозону цены, и сортировка по возрастанию цены
    @Query(value = "select * from product where (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '1?%') and (price >= ?2 and price <= ?3) order by price asc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceAsc(String title, float ot, float Do);

//  поиск по наименованию и фильтрация по диапозону цены, и сортировка по убыванию цены
    @Query(value = "select * from product where (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '1?%') and (price >= ?2 and price <= ?3) order by price desc", nativeQuery = true)
    List<Product> findByTitleOrderByPriceDesc(String title, float ot, float Do);

//  поиск по наименованию и фильтрация по диапозону цены, и сортировка по возрастанию цены, и фильтрации по категории
    @Query(value = "select * from product where category_id = ?4 and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '1?%') and (price >= ?2 and price <= ?3) order by price asc", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceAsc(String title, float ot, float Do, int category);

    //  поиск по наименованию и фильтрация по диапозону цены, и сортировка по убыванию цены, и фильтрации по категории
    @Query(value = "select * from product where category_id= ?4 and (lower(title) LIKE %?1%) or (lower(title) LIKE '?1%') OR (lower(title) LIKE '1?%') and (price >= ?2 and price <= ?3) order by price desc", nativeQuery = true)
    List<Product> findByTitleAndCategoryOrderByPriceDesc(String title, float ot, float Do, int category);

}
