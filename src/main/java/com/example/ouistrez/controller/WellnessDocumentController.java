package com.example.ouistrez.controller;

import com.example.ouistrez.dto.response.GeneralPolicyResponse;
import com.example.ouistrez.dto.response.WellnessDocumentResponse;
import com.example.ouistrez.service.WellnessDocumentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    @GetMapping("File/{id}")
    public ResponseEntity<Resource> getFile(@PathVariable("id") String id) throws IOException {

        File imgFile = wellnessDocumentService.getFileById(id);

        byte[] fileBytes = FileUtils.readFileToByteArray(imgFile);
        ByteArrayResource resource = new ByteArrayResource(fileBytes);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control","no-cache, no-store, must-revalidate");
        headers.add("Pragma","no-cache");
        headers.add("Expires","0");
        headers.add("Content-Disposition","attachment; filename=" + imgFile.getName());
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(imgFile.length())
                .contentType(MediaType.parseMediaType("application/jpg")).body(resource);

    }


}
