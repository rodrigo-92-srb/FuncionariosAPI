package br.com.api.funcionarios.controller;

import br.com.api.funcionarios.model.VendaModel;
import br.com.api.funcionarios.model.VendaResponseModel;
import br.com.api.funcionarios.service.FuncionarioService;
import br.com.api.funcionarios.service.VendaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("vendas")
@Tag(name = "Venda Controller", description = "Operações relacionadas a vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/list")
    @Operation(summary = "Listar todas as vendas", description = "Retorna uma lista de todas as vendas.")
    public Iterable<VendaModel> list(){
        return vendaService.list();
    }

    @GetMapping("/listByFuncionarioId/{id}")
    @Operation(summary = "Listar todas os vendas de um funcionário", description = "Retorna uma lista de todas as vendas do funcionário com o ID informado.")
    public Iterable<VendaModel> listByFuncionarioId(@PathVariable Long id){
        return vendaService.listByFuncionario(id);
    }

    @GetMapping("/listByFuncionarioIdAndDataVenda/{id}")
    @Operation(summary = "Listar todas as vendas de determinado funcionário em uma data", description = "Retorna uma lista de todas as vendas de determinado funcionário em uma data.")
    public Iterable<VendaModel> listByFuncionarioId(@PathVariable Long id,
                                                    @RequestParam(name = "dataVenda") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataVenda){
        return vendaService.listByFuncionarioAndDataVenda(id, dataVenda);
    }

    @PostMapping("/create")
    @Operation(summary = "Criar uma venda", description = "Cria uma nova venda com os dados fornecidos no corpo da requisição e na action informada (create).")
    public ResponseEntity<?> create(@RequestBody VendaModel vendaModel, String action){
        return vendaService.createOrUpdate(vendaModel, "create");
    }

    @PutMapping("/update")
    @Operation(summary = "Criar uma venda", description = "Atualiza uma venda existente com os dados fornecidos no corpo da requisição e na action informada (update).")
    public ResponseEntity<?> update(@RequestBody VendaModel vendaModel, String action){
        return vendaService.createOrUpdate(vendaModel, "update");
    }

    @DeleteMapping("/remove/{id}")
    @Operation(summary = "Excluir uma venda", description = "Exclui uma venda com base no ID fornecido.")
    public ResponseEntity<VendaResponseModel> remove(@PathVariable Long id){
        return vendaService.remove(id);
    }



}