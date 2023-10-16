package br.com.api.funcionarios.controller;

import br.com.api.funcionarios.model.CargoModel;
import br.com.api.funcionarios.model.CargoResponseModel;
import br.com.api.funcionarios.service.CargoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("cargos")
@Tag(name = "Cargo Controller", description = "Operações relacionadas a cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("/list")
    @Operation(summary = "Listar todos os cargos", description = "Retorna uma lista de todos os cargos.")
    public Iterable<CargoModel> list(){
        return cargoService.list();
    }

    @PostMapping("/create")
    @Operation(summary = "Criar um novo cargo", description = "Cria um novo cargo com os dados fornecidos no corpo da requisição e na action informada (create).")
    public ResponseEntity<?> create(@RequestBody CargoModel cargoModel, String action){
        return cargoService.createOrUpdate(cargoModel, "create");
    }

    @PutMapping("/update")
    @Operation(summary = "Atualizar um cargo", description = "Atualiza um cargo existente com base nos dados fornecidos no corpo da requisição e na action informada (update).")
    public ResponseEntity<?> update(@RequestBody CargoModel cargoModel, String action){
        return cargoService.createOrUpdate(cargoModel, "update");
    }

    @DeleteMapping("/remove/{id}")
    @Operation(summary = "Excluir um cargo", description = "Exclui um cargo com base no ID fornecido.")
    public ResponseEntity<CargoResponseModel> remove(@PathVariable Long id){

        return cargoService.remove(id);
    }

}