package com.example.ouistrez.service.impl;

import com.example.ouistrez.dto.response.OnlineGeneralPaymentResponse;
import com.example.ouistrez.dto.response.OnlineGeneralTransactionStatusResponse;
import com.example.ouistrez.entity.GeneralPayment;
import com.example.ouistrez.enums.Deleted;
import com.example.ouistrez.enums.Duplicate;
import com.example.ouistrez.enums.Flagged;
import com.example.ouistrez.repository.GeneralPaymentRepository;
import com.example.ouistrez.service.OnlineGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnlineGeneralServiceImpl implements OnlineGeneralService {

    @Autowired
    private GeneralPaymentRepository generalPaymentRepository;

    @Override
    public OnlineGeneralPaymentResponse pay(String policyNo, Double amount, String orderId) {

        GeneralPayment gp = generalPaymentRepository.findByOrderIdAndIsDeletedAndProvider(orderId, Deleted.NO, "Online");

        if(gp != null) {
            return convertToPaymentDuplicate(gp);
        } else {

            GeneralPayment generalPayment = new GeneralPayment();

            generalPayment.setPolicyNo(policyNo);
            generalPayment.setAmount(amount);
            generalPayment.setOrderId(orderId);
            generalPayment.setTransactionId(generateSequenceNo());
            generalPayment.setResultCode("SUCCESS");
            generalPayment.setProvider("Online");
            generalPayment.setIsFlagged(Flagged.NO);
            generalPayment.setIsDeleted(Deleted.NO);

            GeneralPayment save = generalPaymentRepository.save(generalPayment);

            return convertPayment(save);

        }

    }

    @Override
    public OnlineGeneralTransactionStatusResponse getTransactionStatus(String orderId) {

        GeneralPayment generalPayment = generalPaymentRepository.findByOrderIdAndIsDeletedAndProvider(orderId, Deleted.NO, "Online");

        if(generalPayment != null) {
            return convertToSuccessful(generalPayment);
        } else {
            return convertToUnsuccessful();
        }

    }

    private static OnlineGeneralPaymentResponse convertPayment(GeneralPayment generalPayment) {

        OnlineGeneralPaymentResponse onlineGeneralPaymentResponse = new OnlineGeneralPaymentResponse();

        onlineGeneralPaymentResponse.setResultCode(generalPayment.getResultCode());
        onlineGeneralPaymentResponse.setUserFriendlyMessage("Saved Successfully");
        onlineGeneralPaymentResponse.setOrderId(generalPayment.getOrderId());
        onlineGeneralPaymentResponse.setTransactionId(generalPayment.getTransactionId());
        onlineGeneralPaymentResponse.setDuplicate(Duplicate.NO);

        return onlineGeneralPaymentResponse;

    }

    private static OnlineGeneralPaymentResponse convertToPaymentDuplicate(GeneralPayment generalPayment) {

        OnlineGeneralPaymentResponse onlineGeneralPaymentResponse = new OnlineGeneralPaymentResponse();

        onlineGeneralPaymentResponse.setResultCode(generalPayment.getResultCode());
        onlineGeneralPaymentResponse.setUserFriendlyMessage("This transaction is already saved");
        onlineGeneralPaymentResponse.setOrderId(generalPayment.getOrderId());
        onlineGeneralPaymentResponse.setTransactionId(generalPayment.getTransactionId());
        onlineGeneralPaymentResponse.setDuplicate(Duplicate.YES);

        return onlineGeneralPaymentResponse;

    }

    private static OnlineGeneralTransactionStatusResponse convertToSuccessful(GeneralPayment generalPayment) {

        OnlineGeneralTransactionStatusResponse onlineGeneralTransactionStatusResponse = new OnlineGeneralTransactionStatusResponse();

        onlineGeneralTransactionStatusResponse.setResultCode(generalPayment.getResultCode());
        onlineGeneralTransactionStatusResponse.setTransactionStatus("OK");

        return onlineGeneralTransactionStatusResponse;

    }

    private static OnlineGeneralTransactionStatusResponse convertToUnsuccessful() {

        OnlineGeneralTransactionStatusResponse onlineGeneralTransactionStatusResponse = new OnlineGeneralTransactionStatusResponse();

        onlineGeneralTransactionStatusResponse.setResultCode("UNSUCCESSFUL");
        onlineGeneralTransactionStatusResponse.setTransactionStatus("NOT_EXISTING");

        return onlineGeneralTransactionStatusResponse;

    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    private static String generateSequenceNo() {

        return Long.toString(System.currentTimeMillis());

    }

}
