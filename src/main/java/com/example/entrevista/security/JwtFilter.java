/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entrevista.security;

import com.example.entrevista.control.services.JwtService;
import io.jsonwebtoken.Claims;
import static io.jsonwebtoken.Jwts.claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author mjlopez
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {

        String path = req.getRequestURI();

        // 1️⃣ Permitir rutas públicas
        if (path.startsWith("/auth/")) {
            chain.doFilter(req, res);
            return;
        }

        String header = req.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Debe enviar token Bearer");
            return;
        }

        String token = header.substring(7);
            Integer usuarioId;

        try {
                Claims claims = jwtService.getClaims(token);

                // Extraer id del token
                Object id = claims.get("id");
                if (id == null) {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.getWriter().write("Token no contiene id de usuario");
                    return;
                }

                // Convertir a Integer
                if (id instanceof Number) {
                    usuarioId = ((Number) id).intValue();
                } else {
                    usuarioId = Integer.parseInt(id.toString());
                }

                System.out.println("el usuarioId es "+usuarioId);
                // Guardar en request
                req.setAttribute("usuarioId", usuarioId);
                
                UsernamePasswordAuthenticationToken authToken =
                new UsernamePasswordAuthenticationToken(usuarioId, null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authToken);

            } catch (Exception e) {
                res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                res.getWriter().write("Token inválido o expirado");
                return;
            }


        System.out.println("pasa ");
        // Continuar con la cadena
        chain.doFilter(req, res);
    }


}


