/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entrevista.control.dtos.request;

import java.util.List;

/**
 *
 * @author mjlopez
 */
public class FacturaRequest {
     private Integer idCliente;
    private String comentarios; // opcional
    private List<FacturaDetalleRequest> productos;

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<FacturaDetalleRequest> getProductos() {
        return productos;
    }

    public void setProductos(List<FacturaDetalleRequest> productos) {
        this.productos = productos;
    }
    
    
}
