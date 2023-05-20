package com.glsid.billingservice.feign;

import com.glsid.billingservice.model.Costumer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "COSTUMER-SERVICE")
public interface CostumerRestClient {
    @GetMapping(path = "/costumers/{id}")
    Costumer getCostumerById(@PathVariable(name = "id") Long id);
}
