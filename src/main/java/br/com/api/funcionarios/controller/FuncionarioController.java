package br.com.api.funcionarios.controller;

import br.com.api.funcionarios.model.FuncionarioModel;
import br.com.api.funcionarios.model.FuncionarioResponseModel;
import br.com.api.funcionarios.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("")
    public String rote(){
        return "Endpoint de funcionários OK...";
    }

    @GetMapping("/list")
    public Iterable<FuncionarioModel> list(){
        return funcionarioService.list();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody FuncionarioModel funcionarioModel, String action){
        return funcionarioService.createOrUpdate(funcionarioModel, "create");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody FuncionarioModel funcionarioModel, String action){
        return funcionarioService.createOrUpdate(funcionarioModel, "update");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<FuncionarioResponseModel> remove(@PathVariable Long id){
        return funcionarioService.remove(id);
    }
}
