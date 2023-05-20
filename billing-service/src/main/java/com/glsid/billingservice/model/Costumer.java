package com.glsid.billingservice.model;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class Costumer {
     private Long id;
     private String name;
     private String email;


}
