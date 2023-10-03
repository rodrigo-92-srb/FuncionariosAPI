package br.com.api.funcionarios.service;

import br.com.api.funcionarios.model.CargoModel;
import br.com.api.funcionarios.model.FuncionarioModel;
import br.com.api.funcionarios.model.VendaModel;
import br.com.api.funcionarios.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public BigDecimal calcTotalVendasByFuncionarioAndData(Long funcionarioId, LocalDate dataAnalisada){

        Iterable<VendaModel> vendaModelList = vendaService.listByFuncionarioAndDataVenda(funcionarioId, dataAnalisada);

        BigDecimal totalVendas = BigDecimal.valueOf(0);

        for (VendaModel vendaModel: vendaModelList) {
            totalVendas = totalVendas.add(vendaModel.getValor());
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

    public BigDecimal calcValorTotalPagobyFuncionario(Long funcionarioId, int mes, int ano){

        FuncionarioModel funcionario = funcionarioService.getById(funcionarioId);

        CargoModel cargo = funcionario.getCargo();

        LocalDate dataAnalisada = createData(mes, ano);

        BigDecimal salario = calcSalario(funcionarioId, mes, ano);

        BigDecimal valorVendido = calcTotalVendasByFuncionarioAndData(funcionarioId, dataAnalisada);

        BigDecimal beneficio;

        if(funcionario.getCargo().getId() == 1){
            beneficio = cargo.getBeneficioPercentual().multiply(salario).divide(BigDecimal.valueOf(100));
        } else if (funcionario.getCargo().getId() == 2) {
            beneficio = cargo.getBeneficioPercentual().multiply(valorVendido).divide(BigDecimal.valueOf(100));
        } else {
            beneficio = BigDecimal.valueOf(0);
        }
        BigDecimal ValorTotal = salario.add(beneficio);
        return ValorTotal;
    }

    public BigDecimal calcValorTotalPagoByList(List<Long> funcionarioIds, int mes, int ano){

        BigDecimal valorTotalLista = BigDecimal.valueOf(0);
        for (Long funcionarioId: funcionarioIds) {
            valorTotalLista = valorTotalLista.add(calcValorTotalPagobyFuncionario(funcionarioId, mes, ano));
        }
        return valorTotalLista;
    }

    public BigDecimal calcSalario(Long funcionarioId, int mes, int ano){

        FuncionarioModel funcionario = funcionarioService.getById(funcionarioId);

        CargoModel cargo = funcionario.getCargo();

        LocalDate dataAnalisada = createData(mes, ano);

        int anosContratado = calcDiferencaAnual(funcionario.getDataContratacao(), dataAnalisada);

        BigDecimal beneficioAnual = cargo.getBeneficioAnual().multiply(BigDecimal.valueOf(anosContratado));

        BigDecimal salario = cargo.getSalarioBase().add(beneficioAnual);

        return salario;

    }

    public BigDecimal calcTotalSalariosByList(List<Long> funcionarioIds, int mes, int ano){

        BigDecimal salarioTotalLista = BigDecimal.valueOf(0);

        for (Long funcionarioId: funcionarioIds) {
            salarioTotalLista = salarioTotalLista.add(calcSalario(funcionarioId, mes, ano));
        }

        return salarioTotalLista;
    }

    public BigDecimal calcBeneficio(Long funcionarioId, int mes, int ano){

        FuncionarioModel funcionario = funcionarioService.getById(funcionarioId);

        CargoModel cargo = funcionario.getCargo();

        LocalDate dataAnalisada = createData(mes, ano);

        BigDecimal salario = calcSalario(funcionarioId, mes, ano);

        BigDecimal valorVendido = calcTotalVendasByFuncionarioAndData(funcionarioId, dataAnalisada);

        BigDecimal beneficio = BigDecimal.valueOf(0);

        if(funcionario.getCargo().getId() == 1){
            beneficio = cargo.getBeneficioPercentual().multiply(salario).divide(BigDecimal.valueOf(100));
        } else if (funcionario.getCargo().getId() == 2) {
            beneficio = cargo.getBeneficioPercentual().multiply(valorVendido).divide(BigDecimal.valueOf(100));
        } else {
            beneficio = BigDecimal.valueOf(0);
        }
        return beneficio;
    }

    public BigDecimal calcTotalBeneficiosByList(List<Long> funcionarioIds, int mes, int ano){

        BigDecimal totalBeneficios = BigDecimal.valueOf(0);
        for (Long funcionarioId: funcionarioIds) {
            totalBeneficios = totalBeneficios.add(calcBeneficio(funcionarioId, mes, ano));
        }

        return totalBeneficios;
    }
}
