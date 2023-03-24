package com.example.ouistrez.controller;

import com.example.ouistrez.dto.response.GeneralPolicyResponse;
import com.example.ouistrez.dto.response.OnlineGeneralTransactionStatusResponse;
import com.example.ouistrez.dto.response.WellnessDocumentResponse;
import com.example.ouistrez.exception.RecordNotFoundException;
import com.example.ouistrez.service.WellnessDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/wellnessDocument")
@CrossOrigin(origins = "*")
public class WellnessDocumentController {

    @Autowired
    private WellnessDocumentService wellnessDocumentService;

    @GetMapping("/getPolicyDetails/{policyNo}")
    public GeneralPolicyResponse getPolicyDetails(@PathVariable("policyNo") String policyNo) {

        return wellnessDocumentService.getPolicyDetails(policyNo);

    }

    @PostMapping("/save")
    public WellnessDocumentResponse save(@RequestParam("wellnessUpload") String request,
                                         @RequestPart("multipartFile") MultipartFile multipartFile) {

        return wellnessDocumentService.uploadDocument(request, multipartFile);

    }


}
