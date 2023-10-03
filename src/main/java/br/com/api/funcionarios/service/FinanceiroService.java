package br.com.api.funcionarios.service;

import br.com.api.funcionarios.model.CargoModel;
import br.com.api.funcionarios.model.FuncionarioModel;
import br.com.api.funcionarios.model.VendaModel;
import br.com.api.funcionarios.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class FinanceiroService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private  FuncionarioService funcionarioService;

    @Autowired
    private VendaService vendaService;

    public double calcTotalVendasByFuncionarioAndData(Long funcionarioId, LocalDate dataAnalisada){

        Iterable<VendaModel> vendaModelList = vendaService.listByFuncionarioAndDataVenda(funcionarioId, dataAnalisada);

        double totalVendas = 0;

        for (VendaModel vendaModel: vendaModelList) {
            totalVendas = totalVendas + vendaModel.getValor();
        }

        return totalVendas;
    }

    public int calcDiferencaAnual(LocalDate dataContratacao, LocalDate dataFinal){

        int diferencaAnual = Period.between(dataContratacao, dataFinal).getYears();

        return diferencaAnual;
    }

    public LocalDate createData(int mes, int ano){
        return LocalDate.of(ano,mes,1);
    }

    public double calcValorTotalPagobyFuncionario(Long funcionarioId, int mes, int ano){

        FuncionarioModel funcionario = funcionarioService.getById(funcionarioId);

        CargoModel cargo = funcionario.getCargo();

        LocalDate dataAnalisada = createData(mes, ano);

        double salario = calcSalario(funcionarioId, mes, ano);

        double valorVendido = calcTotalVendasByFuncionarioAndData(funcionarioId, dataAnalisada);

        double beneficio;

        if(funcionario.getCargo().getId() == 1){
            beneficio = (cargo.getBeneficioPercentual() * salario)/100;
        } else if (funcionario.getCargo().getId() == 2) {
            beneficio = (cargo.getBeneficioPercentual()*valorVendido)/100;
        } else {
            beneficio = 0;
        }
        double ValorTotal = salario + beneficio;

        return ValorTotal;
    }

    public double calcValorTotalPagoByList(List<Long> funcionarioIds, int mes, int ano){

        double valorTotalLista = 0;
        for (Long funcionarioId: funcionarioIds) {
            valorTotalLista = valorTotalLista + calcValorTotalPagobyFuncionario(funcionarioId, mes, ano);
        }
        return valorTotalLista;
    }

    public double calcSalario(Long funcionarioId, int mes, int ano){

        FuncionarioModel funcionario = funcionarioService.getById(funcionarioId);

        CargoModel cargo = funcionario.getCargo();

        LocalDate dataAnalisada = createData(mes, ano);

        int anosContratado = calcDiferencaAnual(funcionario.getDataContratacao(), dataAnalisada);

        double beneficioAnual = cargo.getBeneficioAnual() * anosContratado;

        double salario = cargo.getSalarioBase() + beneficioAnual;

        return salario;

    }

    public double calcTotalSalariosByList(List<Long> funcionarioIds, int mes, int ano){

        double salarioTotalLista = 0;

        for (Long funcionarioId: funcionarioIds) {
            salarioTotalLista = salarioTotalLista + calcSalario(funcionarioId, mes, ano);
        }

        return salarioTotalLista;
    }

    public double calcBeneficio(Long funcionarioId, int mes, int ano){

        FuncionarioModel funcionario = funcionarioService.getById(funcionarioId);

        CargoModel cargo = funcionario.getCargo();

        LocalDate dataAnalisada = createData(mes, ano);

        double salario = calcSalario(funcionarioId, mes, ano);

        double valorVendido = calcTotalVendasByFuncionarioAndData(funcionarioId, dataAnalisada);

        double beneficio = 0;

        if(funcionario.getCargo().getId() == 1){
            beneficio = (cargo.getBeneficioPercentual() * salario)/100;
        } else if (funcionario.getCargo().getId() == 2) {
            beneficio = (cargo.getBeneficioPercentual() * valorVendido)/100;
        } else {
            beneficio = 0;
        }
        return beneficio;
    }

    public double calcTotalBeneficiosByList(List<Long> funcionarioIds, int mes, int ano){

        double totalBeneficios = 0;
        for (Long funcionarioId: funcionarioIds) {
            totalBeneficios = totalBeneficios + calcBeneficio(funcionarioId, mes, ano);
        }

        return totalBeneficios;
    }
}
