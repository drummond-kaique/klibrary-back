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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "tb_author")
public class Author {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
	
	@NotBlank
	private String name;
	
	private String gender;
	
    @Email
    private String email;
    
    private String country;
    
    @Past
    private Date dateOfBirth;
    
    @Pattern(regexp="\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}")
    private String cpf;
    
    @ManyToMany(cascade = {CascadeType.ALL})
    private Set<Obra> did = new HashSet<>();
    
    public Author() {}

    public Author(String name, String gender, String email, String country, Date date, String cpf, Set<Obra> did) {
    	this.name = name;
    	this.gender = gender;
        this.email = email;
        this.country = country;
        this.dateOfBirth = date;
        this.cpf = cpf;
        this.did.addAll(did);
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
 
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    public Set<Obra> getDid(){
    	return this.did;
    }
    
    public void setDid(Set<Obra> newDid){
    	this.did = newDid;
    }
}
