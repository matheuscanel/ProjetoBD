package br.cesar.bd.trabalho.dtos;

import lombok.Data;

@Data
public class CriarItemCarrinhoDTO {
  private Integer fkCliente;
  private Integer fkCarrinho;
  private Integer fkProduto;
}
