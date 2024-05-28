package br.cesar.bd.trabalho.entidades;

import lombok.Data;

@Data
public class Pessoa {
  private String cpf;
  private String nome;
  private String dt_nascimento;
  private Integer fk_endereco_pk;
  private Integer fk_telefone_pk;
}
