package com.example.ouistrez.dto.response;

import lombok.Data;

@Data
public class GeneralPolicyResponse {

    private String policyNo;
    private String customerName;
    private String customerNic;
    private String policyBranch;
    private String premiumOutstanding;

}
