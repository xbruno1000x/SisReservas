package com.SisReservas.service;

import com.SisReservas.model.Cliente;
import com.SisReservas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }
    
    public List<Cliente> buscarPorFiltro(String filtro) {
        if (filtro == null || filtro.trim().isEmpty()) {
            return clienteRepository.findAll();
        }
        return clienteRepository.buscarPorFiltro(filtro.trim());
    }

    public Cliente salvar(Cliente cliente) {
        // Validação: verificar se email já existe
        if (cliente.getId() == null && clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new IllegalArgumentException("Já existe um cliente cadastrado com este email: " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNome(clienteAtualizado.getNome());
                    cliente.setTelefone(clienteAtualizado.getTelefone());
                    
                    // Só atualiza email se for diferente e não estiver em uso
                    if (!cliente.getEmail().equals(clienteAtualizado.getEmail())) {
                        if (clienteRepository.existsByEmail(clienteAtualizado.getEmail())) {
                            throw new IllegalArgumentException("Email já está em uso por outro cliente");
                        }
                        cliente.setEmail(clienteAtualizado.getEmail());
                    }
                    
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new IllegalArgumentException("Cliente não encontrado com ID: " + id);
        }
        clienteRepository.deleteById(id);
    }
}
