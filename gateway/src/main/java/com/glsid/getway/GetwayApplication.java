package com.glsid.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetwayApplication.class, args);
	}

	// static way
//	@Bean
	RouteLocator routeLocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route((r) -> r.path("/costumers/**").uri("lb://COSTUMER-SERVICE"))
				.route((r) -> r.path("/products/**").uri("lb://PRODUCT-SERVICE"))
				.build();

	}

	// dynamic way
	// if you don't know les microservices and routes
	// know the micro-service name via URL
	// ex : http://localhost:8888/COSTUMER-SERVICE/costumers  (we mention the service name in the URL)
	// ex : http://localhost:8888/PRODUCT-SERVICE/products/1
	@Bean
	DiscoveryClientRouteDefinitionLocator definitionLocator(
			ReactiveDiscoveryClient reactiveDiscoveryClient,
			DiscoveryLocatorProperties discoveryLocatorProperties
	) {
		return new DiscoveryClientRouteDefinitionLocator(reactiveDiscoveryClient , discoveryLocatorProperties);
	}
}
