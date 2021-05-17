package com.mazeltov.gateway;

import com.netflix.loadbalancer.*;
import org.springframework.cloud.netflix.ribbon.*;
import org.springframework.context.annotation.*;

@Configuration
@RibbonClient(name = "${cart-service.id}", configuration = CartServiceRibbonConfig.class)
public class CartServiceRibbonConfig {

    @Bean
    public IPing ribbonCartPing() {
        return new PingUrl();
    }

    @Bean
    public IRule ribbonCartRule() {
        return new BestAvailableRule();
    }

}
