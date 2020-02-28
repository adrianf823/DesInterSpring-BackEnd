package com.example.DesInterSpring.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

@Entity
@Table(name = "usuarios")
public class Usuario {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	public String nombre;
	
	@NotBlank
	@Column(unique = true)
	public String email;
	
	@NotBlank
	public String password;
	
	public String foto;

	@NotNull
    @ManyToMany
	private Set<Rol> roles = new HashSet<>();
	
	public boolean admin;
	
	public Usuario(String nombre, String email, String password, String foto, Set<Rol> roles) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.foto = foto;
        this.roles = roles;
        this.admin=true;
    }
	
	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public Usuario(){}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
	
	
	
	
	
}
