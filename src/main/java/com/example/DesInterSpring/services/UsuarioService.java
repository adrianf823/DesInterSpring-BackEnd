package com.example.DesInterSpring.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DesInterSpring.Repository.UsuarioRepository;
import com.example.DesInterSpring.entity.Usuario;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	
	public List<Usuario> obtenerTodos(){
        List<Usuario> lista = usuarioRepository.findAll();
        return lista;
    }

    public Optional<Usuario> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> obtenerPorNombre(String np){
        return usuarioRepository.findByNombre(np);
    }
    
    public Optional<Usuario> obtenerPorEmail(String np){
        return usuarioRepository.findByEmail(np);
    }

    public void guardar(Usuario usuario){
    	usuarioRepository.save(usuario);
    }

    public void borrar(Long id){
    	usuarioRepository.deleteById(id);
    }

    public boolean existePorNombre(String np){
        return usuarioRepository.existsByNombre(np);
    }

    public boolean existePorEmail(String em){
        return usuarioRepository.existsByEmail(em);
    }
    
    public boolean existePorId(Long id){
        return usuarioRepository.existsById(id);
    }
	
    public Optional<Usuario> getByNombre(String nu){
        return usuarioRepository.findByNombre(nu);
    }
	
}
