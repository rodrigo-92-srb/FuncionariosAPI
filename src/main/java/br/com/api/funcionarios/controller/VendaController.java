package br.com.api.funcionarios.controller;

import br.com.api.funcionarios.model.VendaModel;
import br.com.api.funcionarios.model.VendaResponseModel;
import br.com.api.funcionarios.service.FuncionarioService;
import br.com.api.funcionarios.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("")
    public String rote(){
        return "Endpoint de vendas OK...";
    }

    @GetMapping("/list")
    public Iterable<VendaModel> list(){
        return vendaService.list();
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody VendaModel vendaModel, String action){
        return vendaService.createOrUpdate(vendaModel, "create");
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody VendaModel vendaModel, String action){
        return vendaService.createOrUpdate(vendaModel, "update");
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<VendaResponseModel> remove(@PathVariable Long id){
        return vendaService.remove(id);
    }



}
