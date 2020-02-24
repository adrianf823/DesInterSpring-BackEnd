package com.example.DesInterSpring.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.DesInterSpring.entity.Usuario;
import com.example.DesInterSpring.entity.UsuarioPrincipal;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByNombre(nombre).get();
        return UsuarioPrincipal.build(usuario);
    }
}
