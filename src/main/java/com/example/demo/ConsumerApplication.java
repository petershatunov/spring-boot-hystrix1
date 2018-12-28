package com.example.demo;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

//@RibbonClient(name = "second-service1", configuration = CloudProviderConfiguration.class)
public class ConsumerApplication {
    /* ... */
}

//class CloudProviderConfiguration {
//    @Bean
//    public IRule ribbonRule(IClientConfig config) {
//	return new RandomRule();
//    }
//}
