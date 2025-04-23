package com.example.ac2_back.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.example.ac2_back.Models.Funcionario;
import com.example.ac2_back.Models.Projeto;
import com.example.ac2_back.Models.Setor;
import com.example.ac2_back.Repositories.FuncionarioRepository;
import com.example.ac2_back.Repositories.ProjetoRepository;
import com.example.ac2_back.Repositories.SetorRepository;
import com.example.ac2_back.dtos.DadosProjetoDTO;
import com.example.ac2_back.dtos.FuncionarioDTO;
import com.example.ac2_back.dtos.FuncionarioResumoDTO;

@Service
public class FuncionarioService {
    
    private final FuncionarioRepository funcionarioRepository;
    private final SetorRepository setorRepository;
    private final ProjetoRepository projetoRepository;
    
    public FuncionarioService(FuncionarioRepository funcionarioRepository, 
                            SetorRepository setorRepository,
                            ProjetoRepository projetoRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.setorRepository = setorRepository;
        this.projetoRepository = projetoRepository;
    }
    
    public void adicionarFuncionario(FuncionarioDTO dto) {
        Setor setor = setorRepository.findById(dto.getSetorId())
            .orElseThrow(() -> new RuntimeException("Setor não encontrado"));
        
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(dto.getNome());
        funcionario.setCargo(dto.getCargo());
        funcionario.setSetor(setor);
        
        funcionarioRepository.save(funcionario);
    }
    
    public List<DadosProjetoDTO> buscarProjetosPorFuncionario(Integer idFuncionario) {
        Set<Projeto> projetos = funcionarioRepository.findProjetosByFuncionarioId(idFuncionario);
        List<DadosProjetoDTO> projetosDTO = new ArrayList<>();
        
        for (Projeto projeto : projetos) {
            List<FuncionarioResumoDTO> funcionariosDTO = new ArrayList<>();
            for (Funcionario funcionario : projeto.getFuncionarios()) {
                funcionariosDTO.add(new FuncionarioResumoDTO(
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getCargo()
                ));
            }
            
            projetosDTO.add(new DadosProjetoDTO(
                projeto.getId(),
                projeto.getNome(),
                projeto.getDescricao(),
                projeto.getDataInicio(),
                projeto.getDataFim(),
                funcionariosDTO
            ));
        }
        
        return projetosDTO;
    }
}
