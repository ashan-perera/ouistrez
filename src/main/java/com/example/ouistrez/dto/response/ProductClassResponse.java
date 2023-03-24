package com.example.ouistrez.dto.response;

import com.example.ouistrez.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class ProductClassResponse {

    private String id;
    private String name;
    private String description;
    private String createdBy;
    private Date createdDateTime;
    private String modifiedBy;
    private Date modifiedDateTime;
    private Deleted isDeleted;

}
