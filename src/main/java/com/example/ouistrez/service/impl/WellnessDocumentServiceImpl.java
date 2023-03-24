package com.example.ouistrez.service.impl;

import com.example.ouistrez.dto.request.WellnessDocumentSaveRequest;
import com.example.ouistrez.dto.response.GeneralPolicyResponse;
import com.example.ouistrez.dto.response.WellnessDocumentResponse;
import com.example.ouistrez.entity.WellnessDocument;
import com.example.ouistrez.enums.Deleted;
import com.example.ouistrez.feign.PolicyClient;
import com.example.ouistrez.feign.entity.GeneralPolicy;
import com.example.ouistrez.repository.WellnessDocumentRepository;
import com.example.ouistrez.service.WellnessDocumentService;
import com.example.ouistrez.utility.SMSService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class WellnessDocumentServiceImpl implements WellnessDocumentService {

    @Autowired
    private PolicyClient policyClient;

    @Autowired
    private WellnessDocumentRepository wellnessDocumentRepository;

    @Autowired
    private SMSService smsService;

    @Override
    public GeneralPolicyResponse getPolicyDetails(String policyNo) {

        GeneralPolicy gp = policyClient.getGeneralPolicyDetails(policyNo);

        if(gp != null) {

            GeneralPolicyResponse generalPolicyResponse = new GeneralPolicyResponse();

            generalPolicyResponse.setPolicyNo(gp.getPolicyNo());
            generalPolicyResponse.setCustomerName(gp.getCustomerName());
            generalPolicyResponse.setCustomerNic(gp.getCustomerNic());
            generalPolicyResponse.setPolicyBranch(gp.getPolicyBranch());
            generalPolicyResponse.setPremiumOutstanding(gp.getPremiumOutstanding());

            return generalPolicyResponse;

        } else {

            return null;

        }

    }

    @Override
    public WellnessDocumentResponse uploadDocument(String request, MultipartFile multipartFile) {

        Gson gson = new Gson();
        WellnessDocumentSaveRequest wellnessDocumentSaveRequest = gson.fromJson(request, WellnessDocumentSaveRequest.class);

//        (/root/Documents)
        File dir = new File("/root/Documents" + wellnessDocumentSaveRequest.getPolicyNo());


//        File dir = new File("C:/Users/HP/Apps/" + wellnessDocumentSaveRequest.getPolicyNo());
        dir.mkdirs();

        File docFile = new File(dir, multipartFile.getOriginalFilename());

        try {
            multipartFile.transferTo(docFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        WellnessDocument wellnessDocument = new WellnessDocument();

        wellnessDocument.setPolicyNo(wellnessDocumentSaveRequest.getPolicyNo());
        wellnessDocument.setFileName(multipartFile.getOriginalFilename());
        wellnessDocument.setFilePath(docFile.getAbsolutePath());
        wellnessDocument.setRelativePath("../" + wellnessDocumentSaveRequest.getPolicyNo() + "/" + multipartFile.getOriginalFilename());
        wellnessDocument.setIsDeleted(Deleted.NO);

        WellnessDocument save = wellnessDocumentRepository.save(wellnessDocument);

        smsService.sendSMS(wellnessDocumentSaveRequest.getPolicyNo(), docFile.getAbsolutePath());

        return convert(save);

    }

    private static WellnessDocumentResponse convert(WellnessDocument wellnessDocument) {

        WellnessDocumentResponse wellnessDocumentResponse = new WellnessDocumentResponse();

        wellnessDocumentResponse.setId(wellnessDocument.getId());
        wellnessDocumentResponse.setFileName(wellnessDocument.getFileName());
        wellnessDocumentResponse.setFilePath(wellnessDocument.getFilePath());
        wellnessDocumentResponse.setRelativePath(wellnessDocument.getRelativePath());
        wellnessDocumentResponse.setCreatedBy(wellnessDocument.getCreatedBy());
        wellnessDocumentResponse.setCreatedDateTime(wellnessDocument.getCreatedDateTime());
        wellnessDocumentResponse.setModifiedBy(wellnessDocument.getModifiedBy());
        wellnessDocumentResponse.setModifiedDateTime(wellnessDocument.getModifiedDateTime());
        wellnessDocumentResponse.setIsDeleted(wellnessDocument.getIsDeleted());

        return wellnessDocumentResponse;

    }

}
