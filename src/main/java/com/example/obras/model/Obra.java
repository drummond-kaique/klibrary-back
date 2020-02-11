package com.example.obras.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tb_obra")
public class Obra {
	
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
	
	@NotBlank
	private String name;
	
	@NotBlank
	@Size(max=240)
	private String description;
	
	@PastOrPresent
	private Date publication;
	
	@Future
	private Date exposure;
	
	@ManyToMany(cascade = {CascadeType.ALL})
	@Size(min=1)
    private Set<Author> madeBy = new HashSet<>();
	
	public Obra(){}
	
	public Obra(String name, String description, Date publication, Date exposure, Set<Author> madeBy) {
		this.name = name;
		this.description = description;
		this.publication = publication;
		this.exposure = exposure;
		this.madeBy.addAll(madeBy);
	}

	
	public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Date getPublication() {
        return this.publication;
    }

    public void setPublication(Date publication) {
        this.publication = publication;
    }
    
    public Date getExposure() {
        return this.exposure;
    }

    public void setExposure(Date exposure) {
        this.exposure = exposure;
    }
    
    public Set<Author> getMadeBy(){
    	return this.madeBy;
    }
    
    public void setDid(Set<Author> newMadeBy){
    	this.madeBy = newMadeBy;
    }
}
