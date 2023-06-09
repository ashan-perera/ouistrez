package com.example.ouistrez.service;

import com.example.ouistrez.dto.request.ProductClassSaveRequest;
import com.example.ouistrez.dto.request.ProductSaveRequest;
import com.example.ouistrez.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllActiveProducts();

    List<ProductResponse> getActiveProductsByProductClass(String productClassId);

    ProductResponse save(ProductSaveRequest productSaveRequest);

}
