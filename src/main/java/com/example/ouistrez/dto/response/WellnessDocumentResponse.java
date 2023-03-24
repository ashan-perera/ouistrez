package com.example.ouistrez.dto.response;

import com.example.ouistrez.enums.Deleted;
import lombok.Data;

import java.util.Date;

@Data
public class WellnessDocumentResponse {

    private String id;
    private String policyNo;
    private String fileName;
    private String filePath;
    private String relativePath;
    private String createdBy;
    private Date createdDateTime;
    private String modifiedBy;
    private Date modifiedDateTime;
    private Deleted isDeleted;

}
