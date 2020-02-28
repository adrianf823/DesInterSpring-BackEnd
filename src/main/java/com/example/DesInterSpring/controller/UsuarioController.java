package com.example.DesInterSpring.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import com.example.DesInterSpring.jwt.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.example.DesInterSpring.Mensaje;
import com.example.DesInterSpring.Repository.UsuarioRepository;
import com.example.DesInterSpring.entity.Usuario;
import com.example.DesInterSpring.services.UsuarioService;

@RestController
@RequestMapping("api/Usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

	@Autowired
    UsuarioService usuarioService;
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
    @GetMapping("/lista")
    public ResponseEntity<List<Usuario>> getLista(){
        List<Usuario> lista = usuarioService.obtenerTodos();
        return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
    }
	
    @GetMapping("/detalle/{id}")
    public ResponseEntity<Usuario> getOne(@PathVariable Long id){
        if(!usuarioService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe ese usuario"), HttpStatus.NOT_FOUND);
        Usuario usuario = usuarioService.obtenerPorId(id).get();
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }
	
    @PostMapping("nuevo")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> create(@RequestBody Usuario usuario){
        if(StringUtils.isBlank(usuario.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuario.getEmail()))
            return new ResponseEntity(new Mensaje("el email es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuario.getPassword()))
            return new ResponseEntity(new Mensaje("La contraseña es obligatoria"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorEmail(usuario.getEmail()))
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        usuarioService.guardar(usuario);
        return new ResponseEntity(new Mensaje("usuario guardado"), HttpStatus.CREATED);
    }
    
    @PutMapping("/actualizar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable("id") Long id){
        if(!usuarioService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe ese usuario"), HttpStatus.NOT_FOUND);
        if(StringUtils.isBlank(usuario.getNombre()))
            return new ResponseEntity(new Mensaje("el nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(StringUtils.isBlank(usuario.getEmail()))
            return new ResponseEntity(new Mensaje("el email es obligatorio"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existePorEmail(usuario.getEmail()) &&
                usuarioService.obtenerPorEmail(usuario.getEmail()).get().getId() != id)
            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuarioUpdate = usuarioService.obtenerPorId(id).get();
        usuarioUpdate.setNombre(usuario.getNombre());
        if(usuario.getPassword()=="") {
        	
        }else {
        usuarioUpdate.setPassword(passwordEncoder.encode(usuario.getPassword()));
        }
        usuarioUpdate.setEmail(usuario.getEmail());
        usuarioService.guardar(usuarioUpdate);
        return new ResponseEntity(new Mensaje("usuario actualizado"), HttpStatus.CREATED);
    }
    
    @DeleteMapping("/borrar/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long id){
        if(!usuarioService.existePorId(id))
            return new ResponseEntity(new Mensaje("no existe ese usuario"), HttpStatus.NOT_FOUND);
        usuarioService.borrar(id);
        return new ResponseEntity(new Mensaje("usuario eliminado"), HttpStatus.OK);
    }

}
