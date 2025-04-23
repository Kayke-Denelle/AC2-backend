package com.example.ac2_back.dtos;

public class FuncionarioDTO {
    private String nome;
    private String cargo;
    private Integer setorId;

    public FuncionarioDTO() {
    }

    public FuncionarioDTO(String nome, String cargo, Integer setorId) {
        this.nome = nome;
        this.cargo = cargo;
        this.setorId = setorId;
    }

    // Getters e Setters
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

    public Integer getSetorId() {
        return setorId;
    }

    public void setSetorId(Integer setorId) {
        this.setorId = setorId;
    }
}