package com.glsid.inventoryservice.repositories;

import com.glsid.inventoryservice.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductRepositoty extends JpaRepository<Product , Long> {
}
