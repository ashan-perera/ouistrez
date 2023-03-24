package com.example.ouistrez.service;

import com.example.ouistrez.dto.response.GeneralPolicyResponse;
import com.example.ouistrez.dto.response.WellnessDocumentResponse;
import org.springframework.web.multipart.MultipartFile;

public interface WellnessDocumentService {

    GeneralPolicyResponse getPolicyDetails(String policyNo);

    WellnessDocumentResponse uploadDocument(String request, MultipartFile multipartFile);

}
