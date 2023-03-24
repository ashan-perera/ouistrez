package com.example.ouistrez.dto.response;

import com.example.ouistrez.enums.Duplicate;
import lombok.Data;

@Data
public class OnlineGeneralPaymentResponse {

    private String resultCode;
    private String userFriendlyMessage;
    private String orderId;
    private String transactionId;
    private Duplicate duplicate;

}
