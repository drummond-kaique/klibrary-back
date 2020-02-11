package com.example.obras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.obras.model.Obra;

public interface ObraRepository extends JpaRepository<Obra, Integer>{

}
