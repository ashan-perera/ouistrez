package com.example.ouistrez.service;

import com.example.ouistrez.dto.response.OnlineGeneralPaymentResponse;
import com.example.ouistrez.dto.response.OnlineGeneralTransactionStatusResponse;

public interface OnlineGeneralService {

    OnlineGeneralPaymentResponse pay(String policyNo, Double amount, String orderId);

    OnlineGeneralTransactionStatusResponse getTransactionStatus(String orderId);

}
