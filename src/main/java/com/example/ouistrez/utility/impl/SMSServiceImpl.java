package com.example.ouistrez.utility.impl;

import com.example.ouistrez.utility.SMSService;
import org.apache.http.protocol.HTTP;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

@Service
public class SMSServiceImpl implements SMSService {

    @Override
    public void sendSMS(String policyNo, String message, String phoneNo, String category) {

            try {

                URL oracle = new URL("http://116.12.80.89:8082/dialog-sms/save?phoneNo="+ phoneNo + "&message=" + URLEncoder.encode(message, "utf-8") + "&policyNo=" + policyNo + "&category=" + category);

                HttpURLConnection yc = (HttpURLConnection) oracle.openConnection();
                yc.setRequestMethod("POST");
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
