package br.cesar.bd.trabalho.dtos;

import java.util.List;

import br.cesar.bd.trabalho.entidades.Avaliacao;
import lombok.Data;

@Data
public class BuscarProProdutoDTO {
  private List<Avaliacao> avaliacoes;
  private Double media;
}
