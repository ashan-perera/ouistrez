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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
            generalPolicyResponse.setContactNo(gp.getContactNo());
            generalPolicyResponse.setFromDate(gp.getFromDate());
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

        File dir = new File(System.getProperty("user.home") + "/Documents/" + wellnessDocumentSaveRequest.getPolicyNo());

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
        wellnessDocument.setPhoneNo(wellnessDocumentSaveRequest.getPhoneNo());
        wellnessDocument.setFileName(multipartFile.getOriginalFilename());
        wellnessDocument.setFilePath(docFile.getAbsolutePath());
        wellnessDocument.setRelativePath("../" + wellnessDocumentSaveRequest.getPolicyNo() + "/" + multipartFile.getOriginalFilename());
        wellnessDocument.setIsDeleted(Deleted.NO);

        WellnessDocument save = wellnessDocumentRepository.save(wellnessDocument);

        //send SMS
        String message = "Thank you for joining WELLNESS PLUS. To download your policy schedule please visit: " + ServletUriComponentsBuilder.fromCurrentContextPath().path("/").toUriString() + "wlns/fl/" + wellnessDocument.getId() + "\n" +
                "To download your policy document please visit: " + ServletUriComponentsBuilder.fromCurrentContextPath().path("/").toUriString() + "wlns/fl/0a940002-8736-1ad1-8187-36fc63520000";


        smsService.sendSMS(wellnessDocumentSaveRequest.getPolicyNo(), message, wellnessDocumentSaveRequest.getPhoneNo(), "WellnessDoc");

        return convert(save);

    }

    @Override
    public File getFileById(String id) {

        WellnessDocument wellnessDocument = wellnessDocumentRepository.getReferenceById(id);
        File file = new File(wellnessDocument.getFilePath());
        return file;

    }

    private static WellnessDocumentResponse convert(WellnessDocument wellnessDocument) {

        WellnessDocumentResponse wellnessDocumentResponse = new WellnessDocumentResponse();

        wellnessDocumentResponse.setId(wellnessDocument.getId());
        wellnessDocumentResponse.setFileName(wellnessDocument.getFileName());
        wellnessDocumentResponse.setFilePath(wellnessDocument.getFilePath());
        wellnessDocumentResponse.setRelativePath(wellnessDocument.getRelativePath());
        wellnessDocumentResponse.setPolicyScheduleURL(ServletUriComponentsBuilder.fromCurrentContextPath().path("/").toUriString() + "wellnessDocument/File/" + wellnessDocument.getId());
        wellnessDocumentResponse.setCreatedBy(wellnessDocument.getCreatedBy());
        wellnessDocumentResponse.setCreatedDateTime(wellnessDocument.getCreatedDateTime());
        wellnessDocumentResponse.setModifiedBy(wellnessDocument.getModifiedBy());
        wellnessDocumentResponse.setModifiedDateTime(wellnessDocument.getModifiedDateTime());
        wellnessDocumentResponse.setIsDeleted(wellnessDocument.getIsDeleted());

        return wellnessDocumentResponse;

    }

}
