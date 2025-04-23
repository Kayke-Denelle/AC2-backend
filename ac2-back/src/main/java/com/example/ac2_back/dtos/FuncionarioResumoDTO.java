package com.example.ac2_back.dtos;


public class FuncionarioResumoDTO {
    private Integer id;
    private String nome;
    private String cargo;

    // Construtores
    public FuncionarioResumoDTO() {
    }

    public FuncionarioResumoDTO(Integer id, String nome, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cargo = cargo;
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
