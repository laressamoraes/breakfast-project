package com.grupowl.breakfastproject.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.grupowl.breakfastproject.entities.Item;
import com.grupowl.breakfastproject.entities.User;
import com.grupowl.breakfastproject.repositories.ItemRepository;
import com.grupowl.breakfastproject.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ItemRepository itemRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Item i1 = new Item(null, "Pão");
		Item i2 = new Item(null, "Café");
		
		User u1 = new User(null, "Primeiro", "001-001-001-01");
		User u2 = new User(null, "Segundo", "002-002-002-02");
		
		itemRepository.saveAll(Arrays.asList(i1, i2));
		userRepository.saveAll(Arrays.asList(u1, u2));	
	}
}