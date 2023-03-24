package com.example.ouistrez.feign.entity;

import lombok.Data;

@Data
public class GeneralPolicy {

    private String policyNo;
    private String customerName;
    private String customerNic;
    private String customerAddress;
    private String policyBranch;
    private String premiumOutstanding;
    private String fromDate;
    private String toDate;

}
