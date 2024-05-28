package br.cesar.bd.trabalho.dtos;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ItensCarrinhoDTO {
  private Integer id_produto;
  private String nome;
  private String descricao;
  private BigDecimal preco;
  private String imagem;
}
