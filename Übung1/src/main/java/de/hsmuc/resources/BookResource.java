package de.hsmuc.resources;

import java.util.Collection;
import java.util.Optional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import de.hsmuc.presistence.BookShelf;
import de.hsmuc.resources.domain.Book;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Path("/api/books")
@Produces(MediaType.APPLICATION_JSON)
@Api
@ApiModel(description = "Test")
public class BookResource {

	private BookShelf bookShelf;

	@Autowired
	public BookResource(BookShelf bookShelf) {
		this.bookShelf = bookShelf;
	}

	@GET
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Alles OK"),
			@ApiResponse(code = 400, message = "Nix OK") })
	public Response books(@QueryParam("title") String title) {
		Collection<Book> books = bookShelf.findByTitle(title);
		return Response.ok(books).build();
	}

	@GET
	@Path("/{isbn}")
	public Response byIsbn(@PathParam("isbn") String isbn) {
		Optional<Book> foundBook = bookShelf.findByIsbn(isbn);

		if (foundBook.isPresent()) {
			return Response.ok(foundBook.get()).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
}
