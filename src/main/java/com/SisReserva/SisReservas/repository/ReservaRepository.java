package com.SisReservas.repository;

import com.SisReservas.model.Reserva;
import com.SisReservas.model.Reserva.StatusReserva;
import com.SisReservas.model.Cliente;
import com.SisReservas.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    List<Reserva> findByClienteId(Long clienteId);
    
    List<Reserva> findByProfissionalId(Long profissionalId);
    
    List<Reserva> findByData(LocalDate data);
    
    List<Reserva> findByStatus(StatusReserva status);
    
    List<Reserva> findByDataBetween(LocalDate dataInicio, LocalDate dataFim);
    
    List<Reserva> findByClienteAndStatus(Cliente cliente, StatusReserva status);
    
    List<Reserva> findByProfissionalAndStatus(Profissional profissional, StatusReserva status);
    
    List<Reserva> findByProfissionalAndData(Profissional profissional, LocalDate data);
}
