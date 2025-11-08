package com.SisReservas.repository;

import com.SisReservas.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    
    Optional<Profissional> findByEmail(String email);
    
    List<Profissional> findByEspecialidade(String especialidade);
    
    List<Profissional> findByAtivo(Boolean ativo);
    
    List<Profissional> findByAtivoOrderByNomeAsc(Boolean ativo);
    
    boolean existsByEmail(String email);
}
