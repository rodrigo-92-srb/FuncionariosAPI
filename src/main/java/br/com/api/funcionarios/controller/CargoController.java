package br.com.api.funcionarios.controller;

import br.com.api.funcionarios.model.CargoModel;
import br.com.api.funcionarios.model.CargoResponseModel;
import br.com.api.funcionarios.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("cargos")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping("")
    public String rote(){
        return "Endpoint de cargos OK...";
    }

    @GetMapping("/list")
    public Iterable<CargoModel> list(){
        return cargoService.list();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CargoModel cargoModel, String action){
        return cargoService.createOrUpdate(cargoModel, "create");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CargoModel cargoModel, String action){
        return cargoService.createOrUpdate(cargoModel, "update");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<CargoResponseModel> remove(@PathVariable Long id){

        return cargoService.remove(id);
    }

}
