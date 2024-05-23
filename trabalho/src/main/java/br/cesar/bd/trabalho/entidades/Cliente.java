package br.cesar.bd.trabalho.entidades;

import lombok.Data;

@Data
public class Cliente {
  private Integer id_cliente;
  private Integer fk_endereco_endereco_PK;
  private String fk_Pessoa_cpf;
}
