package com.example.DesInterSpring.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DesInterSpring.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Optional<Usuario> findByNombre(String np);
	Optional<Usuario> findByEmail(String em);
    boolean existsByNombre(String np);
	boolean existsByEmail(String em);
	
	
}
