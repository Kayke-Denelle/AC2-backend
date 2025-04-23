package com.example.ac2_back.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.ac2_back.Models.Funcionario;
import com.example.ac2_back.Models.Projeto;
import com.example.ac2_back.Repositories.FuncionarioRepository;
import com.example.ac2_back.Repositories.ProjetoRepository;
import com.example.ac2_back.dtos.DadosProjetoDTO;
import com.example.ac2_back.dtos.FuncionarioResumoDTO;
import com.example.ac2_back.dtos.ProjetoDTO;
import com.example.ac2_back.exceptions.ProjetoNotFoundException;
import com.example.ac2_back.exceptions.FuncionarioNotFoundException;

@Service
public class ProjetoService {
    
    private final ProjetoRepository projetoRepository;
    private final FuncionarioRepository funcionarioRepository;
    
    public ProjetoService(ProjetoRepository projetoRepository, 
                         FuncionarioRepository funcionarioRepository) {
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
    
    public List<DadosProjetoDTO> listarTodosProjetos() {
        return projetoRepository.findAllWithFuncionarios().stream()
            .map(this::convertToDadosProjetoDTO)
            .collect(Collectors.toList());
    }
    
    public DadosProjetoDTO buscarProjetoPorId(Integer id) {
        Projeto projeto = projetoRepository.findByIdWithFuncionarios(id)
            .orElseThrow(() -> new ProjetoNotFoundException(id));
        
        return convertToDadosProjetoDTO(projeto);
    }
    
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = projetoRepository.findById(idProjeto)
            .orElseThrow(() -> new ProjetoNotFoundException(idProjeto));
        
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
            .orElseThrow(() -> new FuncionarioNotFoundException(idFuncionario));
        
        projeto.getFuncionarios().add(funcionario);
        projetoRepository.save(projeto);
    }
    
    public List<DadosProjetoDTO> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return projetoRepository.findByDataInicioBetween(inicio, fim).stream()
            .map(this::convertToDadosProjetoDTO)
            .collect(Collectors.toList());
    }
    
    private DadosProjetoDTO convertToDadosProjetoDTO(Projeto projeto) {
        List<FuncionarioResumoDTO> funcionariosDTO = projeto.getFuncionarios().stream()
            .map(funcionario -> new FuncionarioResumoDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo()))
            .collect(Collectors.toList());
        
        return new DadosProjetoDTO(
            projeto.getId(),
            projeto.getNome(),
            projeto.getDescricao(),
            projeto.getDataInicio(),
            projeto.getDataFim(),
            funcionariosDTO
        );
    }
}