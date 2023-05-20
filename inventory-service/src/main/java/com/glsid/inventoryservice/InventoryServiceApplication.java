
package com.glsid.inventoryservice;

import com.glsid.inventoryservice.entities.Product;
import com.glsid.inventoryservice.repositories.ProductRepositoty;
import org.apache.hc.core5.reactor.Command;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			ProductRepositoty productRepositoty,
			RepositoryRestConfiguration repositoryRestConfiguration) {
		repositoryRestConfiguration.exposeIdsFor(Product.class);
		return args -> {
		  productRepositoty.save(new Product(null, "Ordinateur" , 1299, 18));
		  productRepositoty.save(new Product(null, "Imprement" , 988, 12));
		  productRepositoty.save(new Product(null, "SmartPhone" , 1231, 6));

		  productRepositoty.findAll().forEach(product ->  {
			  System.out.println(product.toString());
		  });

		};
	}

}
