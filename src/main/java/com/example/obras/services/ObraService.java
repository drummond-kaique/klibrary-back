package com.example.obras.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.obras.model.Author;
import com.example.obras.model.Obra;
import com.example.obras.repository.ObraRepository;

@Service
public class ObraService {
	@Autowired
	private ObraRepository obraRepository;
	
	public List<Obra> getAllObras() {
		return this.obraRepository.findAll();
	}
	
	public Obra getObraById(Integer id) {
		return this.obraRepository.findById(id).get();
	}
	
	public Obra addObra(Obra obra) {
		return this.obraRepository.save(obra);
	}
	
	public boolean delete(Integer id) {
		Obra newObra = this.getObraById(id);
		
		if(newObra != null) {
			this.obraRepository.deleteById(id);
			return true;
		}
		
		return false;
	}
}