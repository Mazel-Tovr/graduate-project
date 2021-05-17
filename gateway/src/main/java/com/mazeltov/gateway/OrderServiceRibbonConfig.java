package com.mazeltov.gateway;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.*;
import org.springframework.context.annotation.*;

@Configuration
@RibbonClient(name = "${order-service.id}", configuration = OrderServiceRibbonConfig.class)
public class OrderServiceRibbonConfig {

    @Bean
    public IPing ribbonOrderPing() {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonOrderRule() {
        return new BestAvailableRule();
    }

}
