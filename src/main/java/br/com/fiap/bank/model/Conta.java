package br.com.fiap.bank.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import br.com.fiap.bank.validation.TipoConta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Entity
@Data
public class Conta {
    
    @Id
    private Long numero;

    private String agencia;

    @Column(name = "nome_titular")
    @NotBlank(message = "O nome deve ser obrigatório")
    private String nomeTitular;

    @Column(unique = true)
    @CPF(message = "Digite um CPF válido.")
    private String cpf;

    @PastOrPresent(message = "Insira uma data válida.")
    private LocalDate dataDeAbertura;

    @PositiveOrZero(message = "Insira um saldo válido.")
    private BigDecimal saldo;

    private boolean ativa = true;

    @TipoConta
    private String tipo;
}
