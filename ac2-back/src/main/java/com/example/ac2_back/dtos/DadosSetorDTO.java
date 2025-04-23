package com.example.ac2_back.dtos;

import java.util.List;

public class DadosSetorDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private List<FuncionarioResumoDTO> funcionarios;

    public DadosSetorDTO() {
    }

    public DadosSetorDTO(Integer id, String nome, String descricao, List<FuncionarioResumoDTO> funcionarios) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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

    public List<FuncionarioResumoDTO> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<FuncionarioResumoDTO> funcionarios) {
        this.funcionarios = funcionarios;
    }
}
