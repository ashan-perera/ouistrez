package com.example.ouistrez.dto.request;

import lombok.Data;

@Data
public class ProductSaveRequest {

    private String name;
    private String code;
    private String productClassId;

}
