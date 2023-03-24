package com.example.ouistrez.entity;

import com.example.ouistrez.enums.Flagged;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Data
@Entity
@Table(name = "GENERAL_PAYMENT", uniqueConstraints =
@UniqueConstraint(columnNames = {"ORDER_ID", "PROVIDER"}))
public class GeneralPayment extends BaseEntity {

    @Column(name = "POLICY_NO")
    private String policyNo;

    @Column(name = "AMOUNT")
    private Double amount;

    @Column(name = "ORDER_ID")
    private String orderId;

    @Column(name = "TRANSACTION_ID")
    private String transactionId;

    @Column(name = "RESULT_CODE")
    private String resultCode;

    @Column(name = "PROVIDER")
    private String provider;

    @Column(name = "IS_FLAGGED")
    private Flagged isFlagged;

}
