package br.com.api.funcionarios.model;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @NotNull
    private double valor;

}
