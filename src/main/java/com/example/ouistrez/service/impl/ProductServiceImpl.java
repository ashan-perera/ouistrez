package com.example.ouistrez.service.impl;

import com.example.ouistrez.dto.request.ProductSaveRequest;
import com.example.ouistrez.dto.response.ProductClassResponse;
import com.example.ouistrez.dto.response.ProductResponse;
import com.example.ouistrez.entity.Product;
import com.example.ouistrez.entity.ProductClass;
import com.example.ouistrez.enums.Deleted;
import com.example.ouistrez.repository.ProductRepository;
import com.example.ouistrez.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductResponse> getAllActiveProducts() {

        return productRepository.findByIsDeleted(Deleted.NO).stream().map(ProductServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public List<ProductResponse> getActiveProductsByProductClass(String productClassId) {

        return productRepository.findByIsDeletedAndProductClassId(Deleted.NO, productClassId).stream().map(ProductServiceImpl::convert).collect(Collectors.toList());

    }

    @Override
    public ProductResponse save(ProductSaveRequest productSaveRequest) {

        Product product = new Product();

        product.setName(productSaveRequest.getName());
        product.setCode(productSaveRequest.getCode());

        ProductClass productClass = new ProductClass();
        productClass.setId(productSaveRequest.getProductClassId());
        product.setProductClass(productClass);

        product.setIsDeleted(Deleted.NO);

        Product save = productRepository.save(product);

        return convert(save);

    }

    private static ProductResponse convert(Product product) {

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setCode(product.getCode());
        productResponse.setCreatedBy(product.getCreatedBy());
        productResponse.setCreatedDateTime(product.getCreatedDateTime());
        productResponse.setModifiedBy(product.getModifiedBy());
        productResponse.setModifiedDateTime(product.getModifiedDateTime());
        productResponse.setIsDeleted(product.getIsDeleted());

        return productResponse;

    }

}
