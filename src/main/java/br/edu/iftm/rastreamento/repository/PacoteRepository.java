package br.edu.iftm.rastreamento.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.edu.iftm.rastreamento.model.Pacote;

import java.util.List;

@Repository
public interface PacoteRepository extends CrudRepository<Pacote, Long> {

    List<Pacote> findAllByStatus(String status);

    List<Pacote> findAllByDestinatario(String destinatario);

}
