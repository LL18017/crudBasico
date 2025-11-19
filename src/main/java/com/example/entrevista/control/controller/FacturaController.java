/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entrevista.control.controller;

import com.example.entrevista.control.dtos.request.FacturaRequest;
import com.example.entrevista.control.dtos.response.FacturaResponse;
import com.example.entrevista.control.services.FacturaService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author mjlopez
 */
@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<?> crearFactura(
            @RequestBody FacturaRequest request,
            HttpServletRequest httpRequest) {

        // Obtener id_usuario desde el atributo que pone JwtFilter
        try {
        Object idUsuarioAttr = httpRequest.getAttribute("usuarioId");
        
        
        if (idUsuarioAttr == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No se encontró usuario en token");
        }

        Integer idUsuario;
            // claims podría venir como Integer, Long o String -> normalizar
        if (idUsuarioAttr instanceof Number) {
            idUsuario = ((Integer) idUsuarioAttr);
        } else {
            idUsuario = Integer.valueOf(idUsuarioAttr.toString());
        }

        FacturaResponse resp = facturaService.crearFactura(request, idUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<FacturaResponse>> getFacturas(HttpServletRequest httpRequest) {

        // Opcional: obtener el usuario que hace la petición
        Integer usuarioId = (Integer) httpRequest.getAttribute("usuarioId");

        // Llamar al servicio para obtener todas las facturas
        List<FacturaResponse> facturas = facturaService.findFacturas();

        return ResponseEntity.ok(facturas);
    }
}

