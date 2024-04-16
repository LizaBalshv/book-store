package com.example.bookstore;

import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class BookStoreApplication {

	@Autowired
	private BookService bookService;

	public static void main(String[] args) {
		SpringApplication.run(BookStoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				Book book1 = new Book();
				book1.setTitle("The Adventures of Tom Sawyer");
				book1.setAuthor("Mark Twain");
				book1.setIsbn("ISO 2108:2005");
				book1.setPrice(BigDecimal.valueOf(100.50));
				book1.setDescription("About a boy, Tom Sawyer, growing up along the Mississippi River");
				book1.setCoverImage("test");

				Book book2 = new Book();
				book2.setTitle("1984");
				book2.setAuthor("George Orwell");
				book2.setIsbn("ISO 2108:2005");
				book2.setPrice(BigDecimal.valueOf(250));
				book2.setDescription("Is a dystopian novel and cautionary tale by English writer George Orwell");
				book2.setCoverImage("Nineteen Eighty-Four has become a classic literary example of political and dystopian fiction");

				bookService.save(book1);
				bookService.save(book2);
				System.out.println(bookService.findAll());
			}
		};
	}
}
