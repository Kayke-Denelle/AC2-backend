package com.example.ac2_back.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ac2_back.dtos.DadosProjetoDTO;
import com.example.ac2_back.dtos.ProjetoDTO;
import com.example.ac2_back.services.ProjetoService;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    
    private final ProjetoService projetoService;
    
    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }
    
    // Novo endpoint para listar todos os projetos
    @GetMapping
    public ResponseEntity<List<DadosProjetoDTO>> listarTodos() {
        List<DadosProjetoDTO> projetos = projetoService.listarTodosProjetos();
        return ResponseEntity.ok(projetos);
    }
    
    @PostMapping
    public ResponseEntity<Void> adicionar(@RequestBody ProjetoDTO dto) {
        projetoService.adicionarProjeto(dto);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DadosProjetoDTO> buscarProjetoPorId(@PathVariable Integer id) {
        DadosProjetoDTO projeto = projetoService.buscarProjetoPorId(id);
        return ResponseEntity.ok(projeto);
    }
    
    @PostMapping("/{idProjeto}/funcionarios/{idFuncionario}")
    public ResponseEntity<Void> vincularFuncionario(
            @PathVariable Integer idProjeto,
            @PathVariable Integer idFuncionario) {
        projetoService.vincularFuncionario(idProjeto, idFuncionario);
        return ResponseEntity.ok().build();
    }
}