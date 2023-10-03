package br.com.api.funcionarios.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "cargo")
@Getter
@Setter
public class CargoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private double salarioBase;

    @NotNull
    private double beneficioAnual;

    @NotNull
    private double beneficioPercentual;

}
