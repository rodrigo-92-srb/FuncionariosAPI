package br.com.api.funcionarios.service;

import br.com.api.funcionarios.model.VendaModel;
import br.com.api.funcionarios.model.VendaResponseModel;
import br.com.api.funcionarios.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    @Autowired
    private VendaResponseModel vendaResponseModel;

    // Método para listar todas as vendas
    public Iterable<VendaModel> list(){
        return vendaRepository.findAll();
    }

    // Método para cadastro ou alteração de venda
    public ResponseEntity<?> createOrUpdate(VendaModel vendaModel, String action){

        if(vendaModel.getFuncionario() == null){

            vendaResponseModel.setMessage("O funcionário que realizou a venda é obrigatório!");
            return new ResponseEntity<VendaResponseModel>(vendaResponseModel, HttpStatus.BAD_REQUEST);

        } else if (vendaModel.getDataVenda() == null) {

            vendaResponseModel.setMessage("A data da venda é obrigatória!");
            return new ResponseEntity<VendaResponseModel>(vendaResponseModel, HttpStatus.BAD_REQUEST);

        } else if (vendaModel.getValor() == null) {

            vendaResponseModel.setMessage("O valor da venda é obrigatório!");
            return new ResponseEntity<VendaResponseModel>(vendaResponseModel, HttpStatus.BAD_REQUEST);

        } else {
            if(action.equals("create")){

                return new ResponseEntity<VendaModel>(vendaRepository.save(vendaModel), HttpStatus.CREATED);
            }else {

                return new ResponseEntity<VendaModel>(vendaRepository.save(vendaModel), HttpStatus.OK);
            }
        }
    }

    // Método para deletar uma venda
    public ResponseEntity<VendaResponseModel> remove(Long id){
        vendaRepository.deleteById(id);
        vendaResponseModel.setMessage("A venda foi removida com sucesso!");

        return new ResponseEntity<VendaResponseModel>(vendaResponseModel, HttpStatus.OK);
    }



}
