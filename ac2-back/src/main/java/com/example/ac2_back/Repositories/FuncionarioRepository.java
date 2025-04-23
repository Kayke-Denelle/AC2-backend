package com.example.ac2_back.Repositories;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ac2_back.Models.Funcionario;
import com.example.ac2_back.Models.Projeto;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    
    @Query("SELECT f.projetos FROM Funcionario f WHERE f.id = :id")
    Set<Projeto> findProjetosByFuncionarioId(@Param("id") Integer id);
}
