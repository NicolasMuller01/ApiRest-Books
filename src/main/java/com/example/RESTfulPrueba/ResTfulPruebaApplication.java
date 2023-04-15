package com.example.RESTfulPrueba;

import com.example.RESTfulPrueba.entities.Book;
import com.example.RESTfulPrueba.repository.BookRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@SpringBootApplication
public class ResTfulPruebaApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(ResTfulPruebaApplication.class, args);
		BookRepository repository = context.getBean(BookRepository.class);

		Book book1 = new Book(null, "Call of Cthulhu","Lovecraft",260,14.99,true);
		Book book2 = new Book(null, "la sirenita","Lovecraft",260,14.99,true);

		repository.save(book1);
		repository.save(book2);


	}

}
