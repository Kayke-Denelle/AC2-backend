package com.example.ac2_back.exceptions;

public class ProjetoNotFoundException extends RuntimeException {
    public ProjetoNotFoundException(Integer id) {
        super("Projeto não encontrado com ID: " + id);
    }
}
