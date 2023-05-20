package com.glsid.billingservice;

import com.glsid.billingservice.entities.Bill;
import com.glsid.billingservice.entities.ProductItem;
import com.glsid.billingservice.feign.CostumerRestClient;
import com.glsid.billingservice.feign.ProductItemRestClient;
import com.glsid.billingservice.model.Costumer;
import com.glsid.billingservice.model.Product;
import com.glsid.billingservice.repositories.BillRepository;
import com.glsid.billingservice.repositories.ProductItemRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients   // obligatoire de declare ici pour activer OpenFeign
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository,
                            ProductItemRepository productItemRepository,
                            CostumerRestClient costumerRestClient,
                            ProductItemRestClient productItemRestClient) {
        return args -> {
            Costumer costumer = costumerRestClient.getCostumerById(1L);
            Bill bill1 = billRepository.save(new Bill(null, new Date() , null , costumer.getId() , null));
            PagedModel<Product> productPagedModel = productItemRestClient.pageProducts();
            productPagedModel.forEach(product -> {
                ProductItem productItem = new ProductItem();
                productItem.setPrice(product.getPrice());
                productItem.setQuantity(1+new Random().nextInt(100));
                productItem.setBill(bill1);
                productItem.setProductID(product.getId());
                productItemRepository.save(productItem);
            });
        };
    }
}
