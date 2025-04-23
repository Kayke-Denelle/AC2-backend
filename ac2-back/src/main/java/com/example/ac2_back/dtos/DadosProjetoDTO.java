package com.example.ac2_back.dtos;

import java.time.LocalDate;
import java.util.List;

public class DadosProjetoDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private List<FuncionarioResumoDTO> funcionarios;

    // Construtores
    public DadosProjetoDTO() {
    }

    public DadosProjetoDTO(Integer id, String nome, String descricao, LocalDate dataInicio, 
                          LocalDate dataFim, List<FuncionarioResumoDTO> funcionarios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.funcionarios = funcionarios;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public List<FuncionarioResumoDTO> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<FuncionarioResumoDTO> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
