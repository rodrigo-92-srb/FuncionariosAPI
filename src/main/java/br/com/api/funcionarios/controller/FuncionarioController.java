package br.com.api.funcionarios.controller;

import br.com.api.funcionarios.model.FuncionarioModel;
import br.com.api.funcionarios.model.FuncionarioResponseModel;
import br.com.api.funcionarios.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("funcionarios")
@Tag(name = "Funcionario Controller", description = "Operações relacionadas a funcionários")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/list")
    @Operation(summary = "Listar todos os funcionários", description = "Retorna uma lista de todos os funcionários.")
    public Iterable<FuncionarioModel> list(){
        return funcionarioService.list();
    }

    @PostMapping("/create")
    @Operation(summary = "Criar um novo funcionário", description = "Cria um novo funcionário com os dados fornecidos no corpo da requisição e na action informada (create).")
    public ResponseEntity<?> create(@RequestBody FuncionarioModel funcionarioModel, String action){
        return funcionarioService.createOrUpdate(funcionarioModel, "create");
    }

    @PutMapping("/update")
    @Operation(summary = "Atualizar um funcionário", description = "Atualiza um funcionário existente com base nos dados fornecidos no corpo da requisição e na action informada (update).")
    public ResponseEntity<?> update(@RequestBody FuncionarioModel funcionarioModel, String action){
        return funcionarioService.createOrUpdate(funcionarioModel, "update");
    }

    @DeleteMapping("/remove/{id}")
    @Operation(summary = "Excluir um funcionário", description = "Exclui um funcionário com base no ID fornecido.")
    public ResponseEntity<FuncionarioResponseModel> remove(@PathVariable Long id){
        return funcionarioService.remove(id);
    }
}