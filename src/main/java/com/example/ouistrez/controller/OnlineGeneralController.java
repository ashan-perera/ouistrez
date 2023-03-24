package com.example.ouistrez.controller;

import com.example.ouistrez.dto.response.OnlineGeneralPaymentResponse;
import com.example.ouistrez.dto.response.OnlineGeneralTransactionStatusResponse;
import com.example.ouistrez.exception.RecordNotFoundException;
import com.example.ouistrez.service.OnlineGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/online-general")
@CrossOrigin(origins = "*")
public class OnlineGeneralController {

    @Autowired
    private OnlineGeneralService onlineGeneralService;

    @GetMapping("/pay")
    public OnlineGeneralPaymentResponse pay(
            @RequestParam(value = "policyNo") String policyNo,
            @RequestParam(value = "amount") Double amount,
            @RequestParam(value = "orderId") String orderId) {

        return onlineGeneralService.pay(policyNo, amount, orderId);

    }

    @GetMapping("/transaction-status/{orderId}")
    public OnlineGeneralTransactionStatusResponse getTransactionStatus(@PathVariable("orderId") String orderId) throws RecordNotFoundException {

        return onlineGeneralService.getTransactionStatus(orderId);

    }

}
