package de.huettner.edu.uebung1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class BookShelf {

	private final List<Book> books = new ArrayList<>();

	public BookShelf() {
		books.add(Book.builder().isbn("12345").title("Herr der Ringe - Die Gefährten").author("Hm").build());
		books.add(Book.builder().isbn("12").title("Test").author("TestAuth").build());
	}

	public Collection<Book> findByTitle(String title) {
		return books.stream().filter((book) -> {
			return book.getTitle().equals(title);
		}).collect(Collectors.toList());
	}

	public Optional<Book> findByIsbn(String isbn) {
		return books.stream().filter((book) -> {
			return true;
		}).findAny();
	}
}
