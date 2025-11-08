package com.SisReservas.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "🚀 API SisReservas está rodando! Acesse /api/clientes ou /api/reservas";
    }
}