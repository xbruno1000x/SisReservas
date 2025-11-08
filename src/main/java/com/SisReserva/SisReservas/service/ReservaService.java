package com.SisReservas.service;

import com.SisReservas.model.Reserva;
import com.SisReservas.model.Reserva.StatusReserva;
import com.SisReservas.model.Cliente;
import com.SisReservas.repository.ReservaRepository;
import com.SisReservas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> buscarPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> buscarPorCliente(Long clienteId) {
        return reservaRepository.findByClienteId(clienteId);
    }

    public List<Reserva> buscarPorData(LocalDate data) {
        return reservaRepository.findByData(data);
    }

    public List<Reserva> buscarPorStatus(StatusReserva status) {
        return reservaRepository.findByStatus(status);
    }

    public List<Reserva> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return reservaRepository.findByDataBetween(dataInicio, dataFim);
    }

    public Reserva criar(Reserva reserva) {
        // Validar se o cliente existe
        if (reserva.getCliente() == null || reserva.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente é obrigatório para criar uma reserva");
        }

        Cliente cliente = clienteRepository.findById(reserva.getCliente().getId())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + reserva.getCliente().getId()));

        reserva.setCliente(cliente);
        
        // Validação básica de data (não pode ser no passado)
        if (reserva.getData().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Não é possível criar reserva para data passada");
        }

        return reservaRepository.save(reserva);
    }

    public Reserva atualizar(Long id, Reserva reservaAtualizada) {
        return reservaRepository.findById(id)
                .map(reserva -> {
                    reserva.setData(reservaAtualizada.getData());
                    reserva.setHora(reservaAtualizada.getHora());
                    reserva.setObservacoes(reservaAtualizada.getObservacoes());
                    
                    if (reservaAtualizada.getStatus() != null) {
                        reserva.setStatus(reservaAtualizada.getStatus());
                    }
                    
                    return reservaRepository.save(reserva);
                })
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada com ID: " + id));
    }

    public Reserva atualizarStatus(Long id, StatusReserva novoStatus) {
        return reservaRepository.findById(id)
                .map(reserva -> {
                    reserva.setStatus(novoStatus);
                    return reservaRepository.save(reserva);
                })
                .orElseThrow(() -> new IllegalArgumentException("Reserva não encontrada com ID: " + id));
    }

    public void deletar(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new IllegalArgumentException("Reserva não encontrada com ID: " + id);
        }
        reservaRepository.deleteById(id);
    }
}
