package br.com.api.funcionarios.service;

import br.com.api.funcionarios.model.CargoModel;
import br.com.api.funcionarios.model.CargoResponseModel;
import br.com.api.funcionarios.repository.CargoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CargoService {

    @Autowired
    private CargoRespository cargoRespository;

    @Autowired
    private CargoResponseModel cargoResponseModel;

    // Método para listar todos os cargos
    public Iterable<CargoModel> list(){
        return cargoRespository.findAll();
    }

    // Método para cadastrar ou alterar um cargo
    public ResponseEntity<?> createOrUpdate(CargoModel cargoModel, String action) {

        if(cargoModel.getNome().equals("")){

            cargoResponseModel.setMessage("Nome do cargo é obrigatório!");
            return new ResponseEntity<CargoResponseModel>(cargoResponseModel, HttpStatus.BAD_REQUEST);

        } else {
            if(action.equals("create")){
                return new ResponseEntity<CargoModel>(cargoRespository.save(cargoModel), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<CargoModel>(cargoRespository.save(cargoModel), HttpStatus.OK);
            }
        }
    }

    // Método para deletar um cargo
    public ResponseEntity<CargoResponseModel> remove(Long id){
        cargoRespository.deleteById(id);
        cargoResponseModel.setMessage("O cargo foi removido com sucesso!");

        return new ResponseEntity<CargoResponseModel>(cargoResponseModel, HttpStatus.OK);
    }

}