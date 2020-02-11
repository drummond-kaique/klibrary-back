package com.example.obras.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
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

import com.example.obras.model.Obra;
import com.example.obras.services.ObraService;

@RestController
@CrossOrigin
@RequestMapping("/obra")
public class ObraController {
	@Autowired
	private ObraService obraService;
	
	@GetMapping
	//@PostAuthorize("hasRole('USER')")
	public ResponseEntity<List<Obra>> getAll() {
		List<Obra> obras = this.obraService.getAllObras();
		return ResponseEntity.ok(obras);
	}
	
	@PostMapping
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Obra> add(@Valid @RequestBody Obra obraBody){
		Obra obra = this.obraService.addObra(obraBody);
		return ResponseEntity.ok(obra);
	}

	@DeleteMapping("/{id}")
	//@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		if(obraService.delete(id)) {
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.badRequest().build();
	}
}