package br.com.api.funcionarios.repository;

import br.com.api.funcionarios.model.CargoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRespository extends JpaRepository<CargoModel, Long> {

}
