package br.com.api.funcionarios.controller;

import br.com.api.funcionarios.model.FuncionarioModel;
import br.com.api.funcionarios.service.FinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("financeiro")
public class FinanceiroController {


    @Autowired
    private FinanceiroService financeiroService;

    // 1- Recebe uma lista de funcionários, mês e ano. E retorna o valor total pago (salário e benefício) a esses funcionários no mês.
    @GetMapping("/total-value-list")
    public double calcBeneficioAnual(@RequestParam(name = "mes") int mes,
                                     @RequestParam(name = "ano") int ano,
                                     @RequestParam(name = "listaFunc") List<Long> funcionarioList){

        return financeiroService.calcValorTotalPagoByList(funcionarioList, mes, ano);
    }

    // 2- Recebe uma lista de funcionários, mês e ano. E retorna o total pago somente em salários no mês.
    @GetMapping("/total-salary-list")
    public double totalSalario(@RequestParam(name = "mes") int mes,
                               @RequestParam(name = "ano") int ano,
                               @RequestParam(name = "listaFunc") List<Long> funcionarioList){

        return financeiroService.calcTotalSalariosByList(funcionarioList, mes, ano);
    }

    // 3- Recebe uma lista somente com os funcionários que recebem benefícios, mês e ano. E retorna o total pago em benefícios no mês.
    @GetMapping("/total-beneficio-list")
    public double totalBeneficio(@RequestParam(name = "mes") int mes,
                                 @RequestParam(name = "ano") int ano,
                                 @RequestParam(name = "listaFunc") List<Long> funcionarioList){

        return financeiroService.calcTotalBeneficiosByList(funcionarioList, mes, ano);
    }

    // 4- Recebe uma lista de funcionários, mês e ano. E retorna o que recebeu o valor mais alto no mês.
    @GetMapping("/maior-salario-list")
    public FuncionarioModel filtraMaiorSalario(@RequestParam(name = "mes") int mes,
                                               @RequestParam(name = "ano") int ano,
                                               @RequestParam(name = "listaFunc") List<Long> funcionarioList){

        return financeiroService.funcionarioMaiorSalario(funcionarioList, mes, ano);
    }

    // 5- Recebe uma lista somente com os funcionários que recebem benefícios, mês e ano.
    // E retorne o nome do funcionário que recebeu o valor mais alto em benefícios no mês.
    @GetMapping("maior-beneficio-list")
    public FuncionarioModel filtraMaiorBeneficio(@RequestParam(name = "mes") int mes,
                                                          @RequestParam(name = "ano") int ano,
                                                          @RequestParam(name = "listaFunc") List<Long> funcionarioList){

        return financeiroService.funcionarioMaiorBeneficio(funcionarioList, mes, ano);
    }

}
