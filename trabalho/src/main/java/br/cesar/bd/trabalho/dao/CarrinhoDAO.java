package br.cesar.bd.trabalho.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import br.cesar.bd.trabalho.dtos.AtualizarCarrinhoDTO;
import br.cesar.bd.trabalho.dtos.CriarItemCarrinhoDTO;
import br.cesar.bd.trabalho.dtos.CriarProdutoDTO;
import br.cesar.bd.trabalho.dtos.ItensCarrinhoDTO;
import br.cesar.bd.trabalho.entidades.Produto;

@Repository
public class CarrinhoDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void criarItemCarrinho(CriarItemCarrinhoDTO criarItemCarrinhoDTO) {
    String sql = "INSERT INTO itens_carrinhos (fk_cliente, fk_carrinho, fk_produto) VALUES (?, ?, ?)";

    jdbcTemplate.update(sql, criarItemCarrinhoDTO.getFkCliente(), criarItemCarrinhoDTO.getFkCarrinho(), criarItemCarrinhoDTO.getFkProduto());
  }

  public Integer criarCarrinho() {
    String sql = "INSERT INTO carrinhos () VALUES ()";

    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            return statement;
        }
    }, holder);
    
    Integer primaryKey = holder.getKey().intValue();
    
    return primaryKey;
  }

  public List<ItensCarrinhoDTO> buscarItensDeCarrinho(Integer id) {
    String sql = "SELECT * FROM itens_carrinhos ic JOIN produtos p ON p.id_produto = ic.fk_produto WHERE fk_carrinho = ?;";

    List<ItensCarrinhoDTO> entidades = jdbcTemplate.query(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
      }
    }, new Mapper());

    return entidades;
  }
  
  private static class Mapper implements RowMapper<ItensCarrinhoDTO> {
    @Override
    public ItensCarrinhoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ItensCarrinhoDTO itensCarrinho = new ItensCarrinhoDTO();
        itensCarrinho.setDescricao(rs.getString("descricao"));
        itensCarrinho.setNome(rs.getString("nome"));
        itensCarrinho.setPreco(rs.getBigDecimal("preco"));
        itensCarrinho.setImagem(rs.getString("imagem"));
        itensCarrinho.setId_produto(rs.getInt("id_produto"));
  
        return itensCarrinho;
    }
  }

  public void atualizar(AtualizarCarrinhoDTO atualizarCarrinhoDTO, Integer id) {
    String sql = "UPDATE carrinhos SET id_pedido = ?, id_carrinho = ?, id_entrega = ?, dt_pedido = ?, dt_entrega = ?, preco_total = ?, status = ?, forma_pagamento = ? WHERE id = ?";

    LocalDateTime dtPedido = atualizarCarrinhoDTO.getDtPedido().toLocalDateTime();
    LocalDateTime dtEntrega = dtPedido.plus(7, ChronoUnit.DAYS);
    Timestamp dtEntregaTimestamp = Timestamp.valueOf(dtEntrega);

    jdbcTemplate.update(sql, 1, 1, 1, atualizarCarrinhoDTO.getDtPedido(), dtEntregaTimestamp, atualizarCarrinhoDTO.getPrecoTotal(), atualizarCarrinhoDTO.getStatus(), atualizarCarrinhoDTO.getFormaPagamento(), id);
  }
}
