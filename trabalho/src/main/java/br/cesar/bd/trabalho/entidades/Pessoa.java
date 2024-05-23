package br.cesar.bd.trabalho.entidades;

import lombok.Data;

@Data
public class Pessoa {
  private String cpf;
  private String nome;
  private String dt_nascimento;
}
