package com.SisReservas.repository;

import com.SisReservas.model.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    
    @Query("SELECT p FROM Profissional p WHERE " +
           "LOWER(p.nome) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
           "LOWER(p.email) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
           "LOWER(p.especialidade) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
           "p.telefone LIKE CONCAT('%', :filtro, '%')")
    List<Profissional> buscarPorFiltro(@Param("filtro") String filtro);
    
    @Query("SELECT p FROM Profissional p WHERE " +
           "(:especialidade IS NULL OR LOWER(p.especialidade) LIKE LOWER(CONCAT('%', :especialidade, '%'))) AND " +
           "(:filtro IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
           "LOWER(p.email) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
           "p.telefone LIKE CONCAT('%', :filtro, '%'))")
    List<Profissional> buscarComFiltros(@Param("especialidade") String especialidade, 
                                        @Param("filtro") String filtro);
}
