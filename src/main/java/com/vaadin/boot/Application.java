package com.vaadin.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}

	@Bean
	public CommandLineRunner loadData(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Manoj", "Krishnamurthy"));
			repository.save(new Customer("Manoj_2", "Krishnamurthy"));
			repository.save(new Customer("Eleni", "Lialiamou"));
			repository.save(new Customer("Hong", "Zhou"));
			repository.save(new Customer("Simor", "Lowry"));
			repository.save(new Customer("Nash", "Pal"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("Customer found with findOne(1L):");
			log.info("--------------------------------");
			log.info(customer.toString());
			log.info("");

			// fetch customers by last name
			log.info("Customer found with findByLastNameStartsWithIgnoreCase('Krishnamurthy'):");
			log.info("--------------------------------------------");
			for (Customer Krishnamurthy : repository
					.findByLastNameStartsWithIgnoreCase("Krishnamurthy")) {
				log.info(Krishnamurthy.toString());
			}
			log.info("");
		};
	}

}
