package com.SisReservas.service;

import com.SisReservas.model.Profissional;
import com.SisReservas.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfissionalService {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public List<Profissional> listarTodos() {
        return profissionalRepository.findAll();
    }

    public List<Profissional> listarAtivos() {
        return profissionalRepository.findByAtivoOrderByNomeAsc(true);
    }

    public Optional<Profissional> buscarPorId(Long id) {
        return profissionalRepository.findById(id);
    }

    public Optional<Profissional> buscarPorEmail(String email) {
        return profissionalRepository.findByEmail(email);
    }

    public List<Profissional> buscarPorEspecialidade(String especialidade) {
        return profissionalRepository.findByEspecialidade(especialidade);
    }

    public Profissional salvar(Profissional profissional) {
        // Validação: verificar se email já existe (se informado)
        if (profissional.getEmail() != null && !profissional.getEmail().isEmpty()) {
            if (profissional.getId() == null && profissionalRepository.existsByEmail(profissional.getEmail())) {
                throw new IllegalArgumentException("Já existe um profissional cadastrado com este email: " + profissional.getEmail());
            }
        }
        return profissionalRepository.save(profissional);
    }

    public Profissional atualizar(Long id, Profissional profissionalAtualizado) {
        return profissionalRepository.findById(id)
                .map(profissional -> {
                    profissional.setNome(profissionalAtualizado.getNome());
                    profissional.setEspecialidade(profissionalAtualizado.getEspecialidade());
                    profissional.setTelefone(profissionalAtualizado.getTelefone());
                    
                    // Só atualiza email se for diferente e não estiver em uso
                    if (profissionalAtualizado.getEmail() != null && !profissionalAtualizado.getEmail().isEmpty()) {
                        if (!profissionalAtualizado.getEmail().equals(profissional.getEmail())) {
                            if (profissionalRepository.existsByEmail(profissionalAtualizado.getEmail())) {
                                throw new IllegalArgumentException("Email já está em uso por outro profissional");
                            }
                            profissional.setEmail(profissionalAtualizado.getEmail());
                        }
                    }
                    
                    if (profissionalAtualizado.getAtivo() != null) {
                        profissional.setAtivo(profissionalAtualizado.getAtivo());
                    }
                    
                    return profissionalRepository.save(profissional);
                })
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado com ID: " + id));
    }

    public Profissional ativarDesativar(Long id, Boolean ativo) {
        return profissionalRepository.findById(id)
                .map(profissional -> {
                    profissional.setAtivo(ativo);
                    return profissionalRepository.save(profissional);
                })
                .orElseThrow(() -> new IllegalArgumentException("Profissional não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        if (!profissionalRepository.existsById(id)) {
            throw new IllegalArgumentException("Profissional não encontrado com ID: " + id);
        }
        profissionalRepository.deleteById(id);
    }
}
