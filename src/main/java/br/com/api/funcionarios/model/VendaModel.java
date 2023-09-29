package br.com.api.funcionarios.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "venda")
@Getter
@Setter
public class VendaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private FuncionarioModel funcionario;

    private LocalDate dataVenda;

    private BigDecimal valor;

}
