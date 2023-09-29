package br.com.api.funcionarios.repository;

import br.com.api.funcionarios.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<VendaModel, Long> {



}
