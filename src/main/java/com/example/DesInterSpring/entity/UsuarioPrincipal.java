package com.example.DesInterSpring.entity;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {

    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String foto;
    private String fotocomp;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(Long id, String nombre, String email,String foto,String fotocomp, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.foto = foto;
        this.fotocomp = fotocomp;
        this.password = password;
        this.authorities = authorities;
    }

    public static UsuarioPrincipal build(Usuario usuario){
        List<GrantedAuthority> authorities =
                usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getRolNombre().name())).collect(Collectors.toList());
        return new UsuarioPrincipal(usuario.getId(), usuario.getNombre(), usuario.getEmail(),usuario.getFoto(),usuario.getFotocomp(), usuario.getPassword(), authorities);
    }

    public Long getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFotocomp() {
		return fotocomp;
	}

	public void setFotocomp(String fotocomp) {
		this.fotocomp = fotocomp;
	}

	@Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombre;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
