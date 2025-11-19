/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.entrevista.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

/**
 *
 * @author mjlopez
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigCustom {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

                    http.csrf(csrf -> csrf.disable())
                             .cors(cors -> cors.configurationSource(request -> {
                        var corses = new CorsConfiguration();
                        corses.setAllowedOrigins(List.of("http://127.0.0.1:5500"));
                        corses.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
                        corses.setAllowedHeaders(List.of("*"));
                        corses.setAllowCredentials(true);
                        return corses;
                    }))
                 .authorizeHttpRequests(auth -> auth
                     .requestMatchers("/auth/**").permitAll()  // login/registro público
                     .anyRequest().authenticated()             // todo lo demás protegido
                 )
                 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

}

