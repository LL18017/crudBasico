/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.entrevista.control.repository;

import com.example.entrevista.entities.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mjlopez
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    Optional<Usuario> findByCorreo(String correo);

    
}
