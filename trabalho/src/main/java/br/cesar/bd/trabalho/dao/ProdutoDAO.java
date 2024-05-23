package br.cesar.bd.trabalho.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import br.cesar.bd.trabalho.dtos.CriarProdutoDTO;
import br.cesar.bd.trabalho.entidades.Produto;

@Repository
public class ProdutoDAO {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<Produto> buscarProdutos() {
    String sql = "SELECT * from produtos;";

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      Produto produto = new Produto();

      produto.setDescricao(rs.getString("descricao"));
      produto.setNome(rs.getString("nome"));
      produto.setPreco(rs.getBigDecimal("preco"));
      produto.setId_produto(rs.getInt("id_produto"));
      produto.setImagem(rs.getString("imagem"));

      return produto;
    });
  }

  public void criar(CriarProdutoDTO criarProdutoDto) {
    String sql = "INSERT INTO produtos (nome, descricao, preco, imagem) VALUES (?, ?, ?, ?)";

    jdbcTemplate.update(sql, criarProdutoDto.getNome(), criarProdutoDto.getDescricao(), criarProdutoDto.getPreco(), criarProdutoDto.getImagem());
  }

  public void deletar(Integer id) {
    String sql = "DELETE FROM produtos WHERE id_produto = ?";

    jdbcTemplate.update(sql, id);
  }

  public Optional<Produto> buscarProduto(Integer id) {
    String sql = "SELECT * FROM produtos WHERE id_produto = ?";

    List<Produto> entidades = jdbcTemplate.query(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        return preparedStatement;
      }
    }, new Mapper());

    return entidades.isEmpty() ? Optional.empty() : Optional.of(entidades.get(0));
  }
  
  private static class Mapper implements RowMapper<Produto> {
    @Override
    public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
        Produto produto = new Produto();
        produto.setDescricao(rs.getString("descricao"));
        produto.setNome(rs.getString("nome"));
        produto.setPreco(rs.getBigDecimal("preco"));
        produto.setId_produto(rs.getInt("id_produto"));
        produto.setImagem(rs.getString("imagem"));
  
        return produto;
    }
  }
}
