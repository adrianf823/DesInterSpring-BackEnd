package com.example.DesInterSpring.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DesInterSpring.RolNombre;
import com.example.DesInterSpring.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long>{
	Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
