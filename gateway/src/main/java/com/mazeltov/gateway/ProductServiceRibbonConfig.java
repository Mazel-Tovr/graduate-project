package com.mazeltov.gateway;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.*;
import org.springframework.context.annotation.*;

@Configuration
@RibbonClient(name = "${product-service.id}", configuration = ProductServiceRibbonConfig.class)
public class ProductServiceRibbonConfig {

    @Bean
    public IPing ping() {
        return new PingUrl();
    }

    @Bean
    public IRule rule()
    {
        return new BestAvailableRule();
    }

}
