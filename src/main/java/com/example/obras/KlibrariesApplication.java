package com.example.obras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KlibrariesApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(KlibrariesApplication.class, args);
	}
	
	@Override
	public void run(String... params) throws Exception {}
}
