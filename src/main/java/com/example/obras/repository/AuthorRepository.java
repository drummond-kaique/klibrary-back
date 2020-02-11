package com.example.obras.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.obras.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
    Boolean existsByEmail(String email);
    Boolean existsByCpf(String cpf);
}
