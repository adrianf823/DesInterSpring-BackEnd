package com.example.DesInterSpring.dto;

import javax.validation.constraints.NotBlank;

public class LoginUsuario {

    @NotBlank
    private String nombre;

    @NotBlank
    private String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreUsuario) {
        this.nombre = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}