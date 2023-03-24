package com.example.ouistrez.repository;

import com.example.ouistrez.entity.ProductClass;
import com.example.ouistrez.enums.Deleted;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductClassRepository extends JpaRepository<ProductClass, String> {

    List<ProductClass> findByIsDeleted(Deleted isDeleted);

}
