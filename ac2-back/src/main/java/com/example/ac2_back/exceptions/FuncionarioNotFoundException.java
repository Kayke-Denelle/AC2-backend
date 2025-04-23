package com.example.ac2_back.exceptions;

public class FuncionarioNotFoundException extends RuntimeException {
    public FuncionarioNotFoundException(Integer id) {
        super("Funcionário não encontrado com ID: " + id);
    }
}