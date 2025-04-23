package com.example.ac2_back.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ac2_back.Models.Funcionario;
import com.example.ac2_back.Models.Setor;
import com.example.ac2_back.Repositories.SetorRepository;
import com.example.ac2_back.dtos.DadosSetorDTO;
import com.example.ac2_back.dtos.FuncionarioResumoDTO;
import com.example.ac2_back.dtos.SetorDTO;

@Service
public class SetorService {
    
    private final SetorRepository setorRepository;
    
    public SetorService(SetorRepository setorRepository) {
        this.setorRepository = setorRepository;
    }
    
    public void adicionarSetor(SetorDTO dto) {
        Setor setor = new Setor();
        setor.setNome(dto.getNome());
        setor.setDescricao(dto.getDescricao());
        setorRepository.save(setor);
    }
    
    public DadosSetorDTO buscarSetorPorId(Integer id) {
        Setor setor = setorRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Setor não encontrado"));
        
        List<FuncionarioResumoDTO> funcionariosDTO = new ArrayList<>();
        for (Funcionario funcionario : setor.getFuncionarios()) {
            funcionariosDTO.add(new FuncionarioResumoDTO(
                funcionario.getId(),
                funcionario.getNome(),
                funcionario.getCargo()
            ));
        }
        
        return new DadosSetorDTO(
            setor.getId(),
            setor.getNome(),
            setor.getDescricao(),
            funcionariosDTO
        );
    }
}
