package com.example.demo;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "second-service")
public interface FeignClientService2 {

    @RequestMapping(value = "/api2/test/{millisec}", method = { RequestMethod.GET})
    String getService2(@PathVariable("millisec") int millisec);

    @RequestMapping(value = "/api2/test/100", method = { RequestMethod.GET})
    List<Post> getServiceFixMills();
    //String getServiceFixMills();

}
