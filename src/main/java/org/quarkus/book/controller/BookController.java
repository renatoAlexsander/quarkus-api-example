package org.quarkus.book.controller;

import org.quarkus.book.dto.BookDTO;
import org.quarkus.book.service.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {

    @Inject
    BookService bookService;

    @POST
    public void save(BookDTO bookDTO) {
        bookService.save(bookDTO);
    }

    @GET
    public Response findAll() {
        return Response.ok(bookService.findAll())
            .build();
    }

    @GET
    @Path("{id}")
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(bookService.findById(id))
            .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteById(@PathParam("id") Long id) {
        bookService.deleteById(id);
        return Response.noContent().build();
    }
}
