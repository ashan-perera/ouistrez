package com.example.ouistrez.utility.impl;

import com.example.ouistrez.utility.SMSService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Service
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendSMS(String policyNo, String message, String phoneNo) {

//        LifePolicy lifePolicy = policyClient.getLifePolicyDetails(policyNo);




//            String phone = rehabContactNo(lifePolicy.getContactNo());
//            String claim_no = "TR_" + provider;

        String sms = message;
        String claim_no = "WL-TEST";

            try {

                // String text="test sms from jetty server";
                URL oracle = new URL("http://172.20.10.16:8070/MO?sms="+ URLEncoder.encode(sms, "utf-8") + "&phone=" + phoneNo + "&claim_no=" + claim_no);


                URLConnection yc = oracle.openConnection();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(yc.getInputStream()));
                String inputLine;

                String getRes = "";
                while ((inputLine = in.readLine()) != null) {
                    getRes = getRes + inputLine + "\n";
                }
                in.close();

                System.out.println(getRes.trim());

                if (getRes.trim().startsWith("Sent")) {
                    System.out.println(getRes + " WARNING");
                } else {
                    System.out.println(getRes + " ERROR");
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
                System.out.println("Error " + e.getMessage());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }



    }

}
