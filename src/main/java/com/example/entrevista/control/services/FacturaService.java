/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entrevista.control.services;

import com.example.entrevista.control.dtos.request.FacturaDetalleRequest;
import com.example.entrevista.control.dtos.request.FacturaRequest;
import com.example.entrevista.control.dtos.response.FacturaDetalleResponse;
import com.example.entrevista.control.dtos.response.FacturaResponse;
import com.example.entrevista.control.repository.ClienteRepository;
import com.example.entrevista.control.repository.FacturaRepository;
import com.example.entrevista.control.repository.ProductoRepository;
import com.example.entrevista.control.repository.UsuarioRepository;
import com.example.entrevista.entities.Cliente;
import com.example.entrevista.entities.Factura;
import com.example.entrevista.entities.FacturaDetalle;
import com.example.entrevista.entities.Producto;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author mjlopez
 */
@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public FacturaResponse crearFactura(FacturaRequest request, Integer idUsuario) {
        // 1) Validar cliente
        Cliente cliente = clienteRepository.findById(request.getIdCliente())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cliente no encontrado"));

        // 2) Crear factura maestro
        Factura factura = new Factura();
        factura.setIdCliente(cliente);
        factura.setIdUsuario(usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuario no válido")));
        factura.setFecha(new Date());
        factura.setFacturaDetalleList(new ArrayList<>());

        BigDecimal total = BigDecimal.ZERO;

        // 3) Procesar detalles
        for (FacturaDetalleRequest lineaReq : request.getProductos()) {

            if (lineaReq.getCantidad() == null || lineaReq.getCantidad() <= 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cantidad inválida para producto " + lineaReq.getIdProducto());
            }

            Producto producto = productoRepository.findById(lineaReq.getIdProducto())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Producto no encontrado: " + lineaReq.getIdProducto()));

            BigDecimal precioUnitario = producto.getPrecio();

            FacturaDetalle detalle = new FacturaDetalle();
            detalle.setIdFactura(factura); // relacion bidireccional
            detalle.setIdProducto(producto);
            detalle.setCantidad(lineaReq.getCantidad());
            detalle.setPrecioUnitario(precioUnitario);
            // Si tu entidad tiene campo subtotal (GENERATED) puedes calcularlo en java para sumar:
            BigDecimal subtotal = precioUnitario.multiply(BigDecimal.valueOf(lineaReq.getCantidad()));
            // si tienes setter para subtotal (si no es generado por BD), úsalo:
             detalle.setSubtotal(subtotal);

            factura.getFacturaDetalleList().add(detalle);
            total = total.add(subtotal);

        }

        // 4) Setear total y persistir
        factura.setTotal(total);
        Factura guardada = facturaRepository.save(factura); // cascade = ALL persiste detalles

        // 5) Construir response
        List<FacturaDetalleResponse> detallesResp = guardada.getFacturaDetalleList().stream()
            .map(d -> new FacturaDetalleResponse(
                    d.getIdFacturaDetalle(),
                    d.getIdProducto().getIdProducto(),
                    d.getIdProducto().getNombre(),
                    d.getCantidad(),
                    d.getPrecioUnitario(),
                   d.getPrecioUnitario().multiply(BigDecimal.valueOf( d.getCantidad() ))
            )).collect(Collectors.toList());

        FacturaResponse resp = new FacturaResponse(
            guardada.getIdFactura(),
            guardada.getIdCliente().getIdCliente(),
            guardada.getIdUsuario().getIdUsuario(),
            guardada.getFecha(),
            guardada.getTotal(),
            detallesResp
        );

        return resp;
    }
    
    
   public List<FacturaResponse> findFacturas() {
    return facturaRepository.findAll()
        .stream()
        .map(f -> new FacturaResponse(
                f.getIdFactura(),
                f.getIdCliente().getIdCliente(),
                f.getIdUsuario().getIdUsuario(),
                f.getFecha(),
                f.getTotal(),
                List.of()
        ))
        .collect(Collectors.toList());
}

}

