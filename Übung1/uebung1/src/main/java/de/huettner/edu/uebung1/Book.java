package de.huettner.edu.uebung1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
	private String title;
	private String author;
	private String isbn;
}
