package com.books.libreria;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.books.libreria.principal.Principal;

@SpringBootApplication
public class LibreriaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LibreriaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal principal = new Principal();
		principal.muestraLibreria();

	}


}
