package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.context.annotation.Bean;

//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableCircuitBreaker
//@EnableEurekaClient
@EnableFeignClients
public class DemoApplication {

    public static void main(String[] args) {
	SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public Sampler defaultSampler() {

	return new AlwaysSampler();
    }

}


