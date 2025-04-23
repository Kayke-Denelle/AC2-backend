package com.example.ac2_back.Repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ac2_back.Models.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {
    
    @Query("SELECT p FROM Projeto p LEFT JOIN FETCH p.funcionarios WHERE p.id = :id")
    Optional<Projeto> findByIdWithFuncionarios(@Param("id") Integer id);
    
    List<Projeto> findByDataInicioBetween(LocalDate inicio, LocalDate fim);

    @Query("SELECT DISTINCT p FROM Projeto p LEFT JOIN FETCH p.funcionarios")
List<Projeto> findAllWithFuncionarios();
}