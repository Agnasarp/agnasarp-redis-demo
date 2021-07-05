package com.agnasarp.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class AgnasarpRedisDemoApplication implements CommandLineRunner {
	private final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(AgnasarpRedisDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Populating embedded database here
		LOG.info("Saving users. Current user count is {}.", userRepository.count());
		User shubham = new User("Shubham", 2000L);
		User pankaj = new User("Pankaj", 29000L);
		User lewis = new User("Lewis", 550L);

		userRepository.save(shubham);
		userRepository.save(pankaj);
		userRepository.save(lewis);
		LOG.info("Done saving users. Data: {}.", userRepository.findAll());
	}
}
