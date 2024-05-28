package br.cesar.bd.trabalho.entidades;

import lombok.Data;

@Data
public class Cliente {
  private Integer id_cliente;
  private Integer fk_endereco_pk;
  private String fk_pessoa_cpf;
  private Integer fk_telefone_pk;
}
