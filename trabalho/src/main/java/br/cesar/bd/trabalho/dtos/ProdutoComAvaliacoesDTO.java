package br.cesar.bd.trabalho.dtos;

import br.cesar.bd.trabalho.entidades.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ProdutoComAvaliacoesDTO extends Produto {
  private Integer mediaAvaliacao;
}
