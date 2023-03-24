package com.example.ouistrez.service;

import com.example.ouistrez.dto.response.ProductClassResponse;
import com.example.ouistrez.dto.request.ProductClassSaveRequest;

import java.util.List;
import java.util.Optional;

public interface ProductClassService {

    List<ProductClassResponse> getAllActiveProductClasses();

    Optional<ProductClassResponse> getById(String id);

    ProductClassResponse save(ProductClassSaveRequest productClassSaveRequest);

}
