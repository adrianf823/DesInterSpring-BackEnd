package com.example.DesInterSpring.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.example.DesInterSpring.RolNombre;

@Entity
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NotNull
    private RolNombre rolNombre;

    public Rol() {
    }

    public Rol(@NotNull RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RolNombre getRolNombre() {
        return rolNombre;
    }

    public void setRolNombre(RolNombre rolNombre) {
        this.rolNombre = rolNombre;
    }
}