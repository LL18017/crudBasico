/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entrevista.control.repository;

import com.example.entrevista.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mjlopez
 */
public interface ClientePersist extends JpaRepository<Cliente,Integer>{
    
}
