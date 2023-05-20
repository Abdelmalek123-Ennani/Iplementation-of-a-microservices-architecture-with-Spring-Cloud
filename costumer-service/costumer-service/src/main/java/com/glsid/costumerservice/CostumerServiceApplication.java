package com.glsid.costumerservice;

import com.glsid.costumerservice.entities.Costumer;
import com.glsid.costumerservice.repositories.CostumerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CostumerServiceApplication {


	public static void main(String[] args) {
		SpringApplication.run(CostumerServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(
			CostumerRepository costumerRepository ,
			RepositoryRestConfiguration repositoryRestConfiguration) {
		repositoryRestConfiguration.exposeIdsFor(Costumer.class); // to show the ID on the API
		return args -> {
           costumerRepository.save(new Costumer(null, "Mohamed" , "med@gmail.com"));
		   costumerRepository.save(new Costumer(null, "Hassan" , "hassan@gmail.com"));
		   costumerRepository.save(new Costumer(null, "Salima" , "salima@gmail.com"));

		   costumerRepository.findAll().forEach(costumer -> {
			   System.out.println(costumer.toString());
		   });
		};


	}

}
