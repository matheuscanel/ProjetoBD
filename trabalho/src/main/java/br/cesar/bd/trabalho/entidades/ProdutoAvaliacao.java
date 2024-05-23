package br.cesar.bd.trabalho.entidades;

import lombok.Data;

@Data
public class ProdutoAvaliacao {
  private Integer fk_Produto_id_produto;
  private Integer fk_Avaliacao_id_avaliacao;
}
