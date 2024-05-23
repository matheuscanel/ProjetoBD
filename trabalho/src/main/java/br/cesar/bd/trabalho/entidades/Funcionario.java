package br.cesar.bd.trabalho.entidades;

import lombok.Data;

@Data
public class Funcionario {
  private Integer id_funcionario;
  private String cargo;

  private String fk_Pessoa_cpf;
  private Integer fk_funcionario_id_funcionario;
  private Integer fk_funcionario_fk_pessoa_;
}
