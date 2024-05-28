package br.cesar.bd.trabalho.dtos;

import lombok.Data;

@Data
public class CadastroClienteDTO {
  private String cpf;
  private String nome;
  private String dtNascimento;

  private String telefones;
  
  private String rua;
  private String bairro;
  private String cidade;
  private String estado;
}
