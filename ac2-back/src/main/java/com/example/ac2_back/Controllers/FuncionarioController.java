package com.example.ac2_back.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2_back.dtos.DadosProjetoDTO;
import com.example.ac2_back.dtos.FuncionarioDTO;
import com.example.ac2_back.services.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    
    private final FuncionarioService funcionarioService;
    
    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }
    
    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody FuncionarioDTO dto) {
        funcionarioService.adicionarFuncionario(dto);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}/projetos")
    public ResponseEntity<List<DadosProjetoDTO>> buscarProjetos(@PathVariable Integer id) {
        List<DadosProjetoDTO> projetos = funcionarioService.buscarProjetosPorFuncionario(id);
        return ResponseEntity.ok(projetos);
    }
}
