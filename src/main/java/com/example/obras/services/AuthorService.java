package com.example.obras.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.obras.model.Author;
import com.example.obras.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	private AuthorRepository authorRepository;
	
	public List<Author> getAllAuthors() {
		return this.authorRepository.findAll();
	}
	
	public Author getAuthorById(Integer id) {
		return this.authorRepository.findById(id).get();
	}
	
	public Author addAuthor(Author author) {
		return this.authorRepository.save(author);
	}
	
	public boolean delete(Integer id) {
		Author newAuthor = this.getAuthorById(id);
		
		if(newAuthor.getDid().size() >= 1) return false;
		
		if(newAuthor != null) {
			this.authorRepository.deleteById(id);
			return true;
		}
		
		return false;
	}

	public boolean existByEmail(String email) {
		return this.authorRepository.existsByEmail(email);
	}
	
	public boolean existByCpf(String cpf) {
		return this.authorRepository.existsByCpf(cpf);
	}
}