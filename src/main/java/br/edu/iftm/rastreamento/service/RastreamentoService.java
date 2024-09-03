package br.edu.iftm.rastreamento.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iftm.rastreamento.dto.RastreamentoDTO;
import br.edu.iftm.rastreamento.model.Rastreamento;
import br.edu.iftm.rastreamento.repository.RastreamentoRepository;
import br.edu.iftm.rastreamento.service.util.Converters;

@Service
public class RastreamentoService {

    @Autowired
    private RastreamentoRepository rastreamentoRepository;

    @Autowired
    private Converters converters;
    
    public List<RastreamentoDTO> getAllRastreamentos() {
        Iterable<Rastreamento> rastreamentosIterable = rastreamentoRepository.findAll();
        List<Rastreamento> rastreamentosList = new ArrayList<>();
        rastreamentosIterable.forEach(rastreamentosList::add);
        return rastreamentosList.stream().map((rast)-> converters.convertToDTO(rast)).collect(Collectors.toList());
    }

    public RastreamentoDTO getRastreamentoById(Long id) {
        Rastreamento rastreamento = rastreamentoRepository.findById(id).get();
        return converters.convertToDTO(rastreamento);
    }

    public RastreamentoDTO createRastreamento(RastreamentoDTO rastreamentoDTO) {
        Rastreamento rastreamento = converters.convertToEntity(rastreamentoDTO);
        Rastreamento savedRastreamento = rastreamentoRepository.save(rastreamento);
        return converters.convertToDTO(savedRastreamento);
    }

    public RastreamentoDTO updateRastreamento(Long id, RastreamentoDTO rastreamentoDTO) {
        Rastreamento rastreamento = converters.convertToEntity(rastreamentoDTO);
        rastreamento.setId(id);
        Rastreamento updatedRastreamento = rastreamentoRepository.save(rastreamento);
        return converters.convertToDTO(updatedRastreamento);
    }
}