package br.com.api.funcionarios.service;

import br.com.api.funcionarios.model.FuncionarioModel;
import br.com.api.funcionarios.model.FuncionarioResponseModel;
import br.com.api.funcionarios.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioResponseModel funcionarioResponseModel;

    // Método para listar todos os funcionários
    public Iterable<FuncionarioModel> list(){
        return funcionarioRepository.findAll();
    }

    // Método para obter um funcionário pelo ID
    public FuncionarioModel getById(Long id) {

        Optional<FuncionarioModel> funcionarioOptional = funcionarioRepository.findById(id);

        if (funcionarioOptional.isPresent()) {
            return funcionarioOptional.get();
        } else {
            return null;
        }
    }

    // Método para cadastro ou alteração de funcionário
    public ResponseEntity<?> createOrUpdate(FuncionarioModel funcionarioModel, String action){

        if(funcionarioModel.getNome().equals("")){

            funcionarioResponseModel.setMessage("Nome do funcionário é obrigatório!");
            return new ResponseEntity<FuncionarioResponseModel>(funcionarioResponseModel, HttpStatus.BAD_REQUEST);

        } else if (funcionarioModel.getCargo() == null) {

            funcionarioResponseModel.setMessage("Cargo do funcionário é obrigatório!");
            return new ResponseEntity<FuncionarioResponseModel>(funcionarioResponseModel, HttpStatus.BAD_REQUEST);

        } else if (funcionarioModel.getDataContratacao() == null) {

            funcionarioResponseModel.setMessage("Data de contratação do funcionário é obrigatória!");
            return new ResponseEntity<FuncionarioResponseModel>(funcionarioResponseModel, HttpStatus.BAD_REQUEST);

        } else {
            if(action.equals("create")){
                return new ResponseEntity<FuncionarioModel>(funcionarioRepository.save(funcionarioModel), HttpStatus.CREATED);
            }else {
                return new ResponseEntity<FuncionarioModel>(funcionarioRepository.save(funcionarioModel), HttpStatus.OK);
            }
        }
    }

    // Método para deletar um funcionário
    public ResponseEntity<FuncionarioResponseModel> remove(Long id){
        funcionarioRepository.deleteById(id);
        funcionarioResponseModel.setMessage("O funcionário foi removido com sucesso!");

        return new ResponseEntity<FuncionarioResponseModel>(funcionarioResponseModel, HttpStatus.OK);
    }

}
