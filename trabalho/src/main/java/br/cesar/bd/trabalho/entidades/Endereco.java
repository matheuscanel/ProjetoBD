package br.cesar.bd.trabalho.entidades;

import lombok.Data;

@Data
public class Endereco {
  private Integer endereco_pk;
  private String rua;
  private String bairro;
  private String cidade;
  private String estado;
}
