package com.example.obras.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.obras.model.Author;
import com.example.obras.services.AuthorService;

@RestController
@CrossOrigin
@RequestMapping("/author")
public class AuthorController {
	@Autowired
	private AuthorService authorService;
	
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Author>> getAll() {
		List<Author> authors = this.authorService.getAllAuthors();
		return ResponseEntity.ok(authors);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<String> add(@Valid @RequestBody Author authorBody){
		if(this.authorService.existByEmail(authorBody.getEmail())) {
			return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
		}
		
		if(this.authorService.existByCpf(authorBody.getCpf()) && authorBody.getCountry().equals("Brazil")) {
			return new ResponseEntity<String>("Fail -> CPF is already in use!",
                    HttpStatus.BAD_REQUEST);
		}
		
		Author author = this.authorService.addAuthor(authorBody);
		return ResponseEntity.ok("Author Created");
	}
}