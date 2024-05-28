package br.cesar.bd.trabalho.dtos;

import java.math.BigDecimal;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class AtualizarCarrinhoDTO {
  private Timestamp dtPedido;
  private BigDecimal precoTotal;
  private String status;
  private String formaPagamento;
}
