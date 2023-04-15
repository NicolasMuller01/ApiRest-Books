package com.example.RESTfulPrueba.controller;

import com.example.RESTfulPrueba.entities.Book;
import com.example.RESTfulPrueba.repository.BookRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BookController {
    private BookRepository bookRepository;

    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //Crud sobre la entidad Book

    //Buscar todos los libros
    @Operation(summary = "Retrieve all books", description = "This method retrieves all the books")
    @GetMapping("/api/Books")
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    //buscar un solo libro en base de datos segun ID
    @GetMapping("/api/Books/{id}")
    public Book findById(@PathVariable Long id){
       Optional<Book> bookOpt = bookRepository.findById(id);
       if(bookOpt.isPresent()){
           return bookOpt.get();
       }
       else{
           return null;
       }
    }

    //crear un nuevo libro en base de datos
    @PostMapping("/api/books")
    public ResponseEntity<Book> create(@RequestBody Book book){ //ResponseEntity<Book> es para devolver un error y un ok para avisar el estado de la operacion
        if(book.getId() != null){
            logger.warn("Trying to create a book with id");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }
    //Actualizar un libro existente en base de datos
    @PutMapping("/api/books")
    public ResponseEntity<Book> update(@RequestBody Book book){
        if(book.getId()==null){
            logger.warn("Trying to update a non existent book");
            return ResponseEntity.badRequest().build();
        }
        Book result = bookRepository.save(book);
        return ResponseEntity.ok(result);
    }
    //borrar un libro en base de datos
    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id){
        if(!bookRepository.existsById(id)){
            logger.warn("Book not found");
            return ResponseEntity.notFound().build();
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
