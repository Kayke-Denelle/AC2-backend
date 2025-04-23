package com.example.ac2_back.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ac2_back.Models.Funcionario;
import com.example.ac2_back.Models.Projeto;
import com.example.ac2_back.Repositories.FuncionarioRepository;
import com.example.ac2_back.Repositories.ProjetoRepository;
import com.example.ac2_back.dtos.DadosProjetoDTO;
import com.example.ac2_back.dtos.FuncionarioResumoDTO;
import com.example.ac2_back.dtos.ProjetoDTO;


@Service
public class ProjetoService {
    
    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;
    
    // Injeção de dependência via construtor
    public ProjetoService(ProjetoRepository projetoRepository, FuncionarioRepository funcionarioRepository) {
        this.projetoRepository = projetoRepository;
        this.funcionarioRepository = funcionarioRepository;
    }
    
    public void adicionarProjeto(ProjetoDTO dto) {
        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFim(dto.getDataFim());
        
        projetoRepository.save(projeto);
    }
    
    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository.findByIdWithFuncionarios(id)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        
        List<FuncionarioResumoDTO> funcionariosDTO = new ArrayList<>();
        for (Funcionario funcionario : projeto.getFuncionarios()) {
            funcionariosDTO.add(new FuncionarioResumoDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo()
            ));
        }
        
        return new DadosProjetoDTO(
            projeto.getId(),
            projeto.getNome(),
            projeto.getDescricao(),
            projeto.getDataInicio(),
            projeto.getDataFim(),
            funcionariosDTO
        );
    }
    
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
        
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        
        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }
    
    public List<Projeto> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return projetoRepository.findByDataInicioBetween(inicio, fim);
    }
}
