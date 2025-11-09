package com.SisReservas.repository;

import com.SisReservas.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    Optional<Cliente> findByEmail(String email);
    
    boolean existsByEmail(String email);
    
    Optional<Cliente> findByTelefone(String telefone);
    
    @Query("SELECT c FROM Cliente c WHERE " +
           "LOWER(c.nome) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :filtro, '%')) OR " +
           "c.telefone LIKE CONCAT('%', :filtro, '%')")
    List<Cliente> buscarPorFiltro(@Param("filtro") String filtro);
}
