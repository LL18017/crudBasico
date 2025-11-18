/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entrevista.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

/**
 *
 * @author mjlopez
 */
@Entity
@Table(name = "factura_detalle")
@NamedQueries({
    @NamedQuery(name = "FacturaDetalle.findAll", query = "SELECT f FROM FacturaDetalle f"),
    @NamedQuery(name = "FacturaDetalle.findByIdFacturaDetalle", query = "SELECT f FROM FacturaDetalle f WHERE f.idFacturaDetalle = :idFacturaDetalle"),
    @NamedQuery(name = "FacturaDetalle.findByCantidad", query = "SELECT f FROM FacturaDetalle f WHERE f.cantidad = :cantidad"),
    @NamedQuery(name = "FacturaDetalle.findByPrecioUnitario", query = "SELECT f FROM FacturaDetalle f WHERE f.precioUnitario = :precioUnitario"),
    @NamedQuery(name = "FacturaDetalle.findBySubtotal", query = "SELECT f FROM FacturaDetalle f WHERE f.subtotal = :subtotal")})
public class FacturaDetalle implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_factura_detalle")
    private Integer idFacturaDetalle;
    @Basic(optional = false)
    @Column(name = "cantidad")
    private int cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
    @Column(name = "subtotal")
    private BigDecimal subtotal;
    @JoinColumn(name = "id_factura", referencedColumnName = "id_factura")
    @ManyToOne(optional = false)
    private Factura idFactura;
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne(optional = false)
    private Producto idProducto;

    public FacturaDetalle() {
    }

    public FacturaDetalle(Integer idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public FacturaDetalle(Integer idFacturaDetalle, int cantidad, BigDecimal precioUnitario) {
        this.idFacturaDetalle = idFacturaDetalle;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public Integer getIdFacturaDetalle() {
        return idFacturaDetalle;
    }

    public void setIdFacturaDetalle(Integer idFacturaDetalle) {
        this.idFacturaDetalle = idFacturaDetalle;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Factura getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Factura idFactura) {
        this.idFactura = idFactura;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFacturaDetalle != null ? idFacturaDetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FacturaDetalle)) {
            return false;
        }
        FacturaDetalle other = (FacturaDetalle) object;
        if ((this.idFacturaDetalle == null && other.idFacturaDetalle != null) || (this.idFacturaDetalle != null && !this.idFacturaDetalle.equals(other.idFacturaDetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.entrevista.entities.FacturaDetalle[ idFacturaDetalle=" + idFacturaDetalle + " ]";
    }
    
}
