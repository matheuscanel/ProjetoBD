package br.cesar.bd.trabalho.entidades;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Produto {
  private Integer id_produto;
  private BigDecimal preco;
  private String nome;
  private String descricao;
  private String imagem;
}
