/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entrevista.control.dtos.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 *
 * @author mjlopez
 */
public class FacturaResponse {
    private Integer idFactura;
    private Integer idCliente;
    private Integer idUsuario;
    private Date fecha;
    private BigDecimal total;
    private List<FacturaDetalleResponse> detalles;

    public FacturaResponse(Integer idFactura, Integer idCliente, Integer idUsuario, Date fecha, BigDecimal total, List<FacturaDetalleResponse> detalles) {
        this.idFactura = idFactura;
        this.idCliente = idCliente;
        this.idUsuario = idUsuario;
        this.fecha = fecha;
        this.total = total;
        this.detalles = detalles;
    }

    
    public Integer getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Integer idFactura) {
        this.idFactura = idFactura;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<FacturaDetalleResponse> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<FacturaDetalleResponse> detalles) {
        this.detalles = detalles;
    }
    
    

  
}
