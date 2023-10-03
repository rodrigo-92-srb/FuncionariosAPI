package br.com.api.funcionarios.repository;

import br.com.api.funcionarios.model.VendaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VendaRepository extends JpaRepository<VendaModel, Long> {

    List<VendaModel> findByFuncionarioId(Long funcionarioId);

    List<VendaModel> findByFuncionarioIdAndDataVenda(Long funcionarioId, LocalDate dataVenda);

}
