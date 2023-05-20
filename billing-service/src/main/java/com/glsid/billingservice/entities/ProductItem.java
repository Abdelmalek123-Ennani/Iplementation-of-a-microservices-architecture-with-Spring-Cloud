package com.glsid.billingservice.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.glsid.billingservice.model.Costumer;
import com.glsid.billingservice.model.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor

public class ProductItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double quantity;
    private double price;
    private Long productID;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    private Bill bill;
    @Transient // means I don't want it to be presented in db (just here in entity)
    private Product product;
    @Transient
    private String productName;
}
