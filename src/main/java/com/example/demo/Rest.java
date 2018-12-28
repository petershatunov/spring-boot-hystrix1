package com.example.demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class Rest {

    @Autowired
    private Tracer tracer;

    @Autowired
    private FeignClientService2 feignClientService2;

    @Autowired
    private FeignClientService3 feignClientService3;

    @Autowired
    private FeignJsonService feignJsonService;

    @GetMapping(path = "/test/{millisec}")
    @HystrixCommand(commandProperties = {
		    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "8000")}, fallbackMethod = "hystrixFall")
    public String testHystrix(@PathVariable int millisec) {

	heavyMethod(millisec);
	heavyMethod(500);

	String service2 = "";
	String service3 = "";
	try {
	    service2 = feignClientService2.getService2(millisec);
	    service3 = feignClientService3.getService2(millisec);
	} catch (Exception e) {
	    log.error("ex = ", e);
	}

	return service3;

    }

    public void heavyMethod(int millesec) {

	Span newSpan = tracer.createSpan("hyTst_Heavy!");

	try {
	    Thread.sleep(millesec);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	} finally {
	    newSpan.tag("srv_heavy", "HY_Heavy!");
	    newSpan.logEvent(org.springframework.cloud.sleuth.Span.CLIENT_RECV);
	    tracer.close(newSpan);
	}

    }

    private String hystrixFall(int millisec) {
	return "Sorry service1 is down at " + millisec + ". Please try again later";
    }

}
