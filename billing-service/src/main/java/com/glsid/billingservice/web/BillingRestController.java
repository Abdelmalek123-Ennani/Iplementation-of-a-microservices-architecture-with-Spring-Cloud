package com.glsid.billingservice.web;

import com.glsid.billingservice.entities.Bill;
import com.glsid.billingservice.feign.CostumerRestClient;
import com.glsid.billingservice.feign.ProductItemRestClient;
import com.glsid.billingservice.model.Costumer;
import com.glsid.billingservice.model.Product;
import com.glsid.billingservice.repositories.BillRepository;
import com.glsid.billingservice.repositories.ProductItemRepository;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BillingRestController {
    private BillRepository billRepository;
    private ProductItemRepository productItemRepository;
    private CostumerRestClient costumerRestClient;
    private ProductItemRestClient productItemRestClient;
    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill bill = billRepository.findById(id).get();
        Costumer costumer = costumerRestClient.getCostumerById(bill.getCostumerID());
        bill.setCostumer(costumer);
        bill.getProductItems().forEach(productItem -> {
            Product product = productItemRestClient.getProductById(productItem.getProductID());
            //productItem.setProduct(product);
            productItem.setProductName(product.getName());
        });
        return bill;
    }

    @GetMapping(path = "/bills/costumer/{costumerId}")
    public List<Bill> getBills(@PathVariable(name = "costumerId") Long costumerId) {
        List<Bill> bills = billRepository.findByCostumerID(costumerId);
        bills.forEach(bill -> {
            Costumer costumer = costumerRestClient.getCostumerById(bill.getCostumerID());
            bill.setCostumer(costumer);
            bill.getProductItems().forEach(productItem -> {
                Product product = productItemRestClient.getProductById(productItem.getProductID());
                //productItem.setProduct(product);
                productItem.setProductName(product.getName());
            });
        });
        return bills;
    }

    public BillingRestController(BillRepository billRepository, ProductItemRepository productItemRepository, CostumerRestClient costumerRestClient, ProductItemRestClient productItemRestClient) {
        this.billRepository = billRepository;
        this.productItemRepository = productItemRepository;
        this.costumerRestClient = costumerRestClient;
        this.productItemRestClient = productItemRestClient;
    }
}
