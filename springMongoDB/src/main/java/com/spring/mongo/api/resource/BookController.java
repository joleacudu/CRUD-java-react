package com.spring.mongo.api.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.mongo.api.model.Book;
import com.spring.mongo.api.repository.BookRepository;

@RestController
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@PostMapping("/addBook")
	public String saveBook(@RequestBody Book book) {
		repository.save(book);
		return "Libro creado correctamente con el Id: " + book.getId();
	}
	
	@GetMapping("/findAllBook")
	public List<Book> getBooKs(){
		return repository.findAll();
	}
	
	 @GetMapping("/findAllBooks")
	    public List<Book> getBooks() {
	        return repository.findAll();
	    }
	    
    @GetMapping("/findAllBooks/{id}")
    public Optional<Book> getBooks(@PathVariable int id) {
        return repository.findById(id);
    }
	
	@DeleteMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        repository.deleteById(id);
        return "Libro Eliminado con el Id: " + id;
    }
}
