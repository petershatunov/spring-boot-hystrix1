package com.example.demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "json-service")
public interface FeignJsonService {

    @RequestMapping(value = "/posts?userId=1", method = { RequestMethod.GET})
    String getJson();

}
