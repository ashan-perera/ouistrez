package com.example.ouistrez.feign;

import com.example.ouistrez.feign.entity.GeneralPolicy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-common", url = "http://116.12.80.89:8001/PolicyStatus")
public interface PolicyClient {

    @RequestMapping(method = RequestMethod.GET, value = "/GeneralPolicy")
    GeneralPolicy getGeneralPolicyDetails(@RequestParam("policyNo") String policyNo);

}
