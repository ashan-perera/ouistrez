package com.example.ouistrez.repository;

import com.example.ouistrez.entity.Product;
import com.example.ouistrez.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByIsDeleted(Deleted isDeleted);

}
