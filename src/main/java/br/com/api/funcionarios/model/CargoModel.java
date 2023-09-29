package br.com.api.funcionarios.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "cargo")
@Getter
@Setter
public class CargoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private BigDecimal salarioBase;

    private BigDecimal beneficioAnual;

    private BigDecimal beneficioPercentual;

}
