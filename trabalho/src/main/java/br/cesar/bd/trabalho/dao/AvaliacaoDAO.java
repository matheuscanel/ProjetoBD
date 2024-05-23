package br.cesar.bd.trabalho.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import br.cesar.bd.trabalho.entidades.Avaliacao;

@Repository
public class AvaliacaoDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;
 
  public Integer criar(Integer nota_avaliacao) {
    String sql = "INSERT INTO avaliacoes (nota_avaliacao) VALUES (?)";

    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, nota_avaliacao);
            return statement;
        }
    }, holder);
    
    Integer primaryKey = holder.getKey().intValue();
    
    return primaryKey;
  }

  public void atribuirAvaliacaoAProduto(Integer id_produto, Integer id_avaliacao) {
    String sql = "INSERT INTO produtos_avaliacoes (fk_Produto_id_produto, fk_Avaliacao_id_avaliacao) VALUES (?, ?)";

    jdbcTemplate.update(sql, id_produto, id_avaliacao);
  }

  @SuppressWarnings("deprecation")
  public Double buscarMediaPorProduto(Integer id_produto) {
    String sql = "SELECT AVG(a.nota_avaliacao) as media from produtos_avaliacoes pa JOIN avaliacoes a ON a.id_avaliacao = pa.fk_Avaliacao_id_avaliacao WHERE pa.fk_Produto_id_produto = ?";

    return jdbcTemplate.queryForObject(sql, new Object[]{id_produto}, (rs, rowNum) -> {
      return rs.getDouble("media");
    });
  }

  public List<Avaliacao> buscarPorProduto(Integer id_produto) {
    String sql = "SELECT a.id_avaliacao, a.nota_avaliacao from produtos_avaliacoes pa JOIN avaliacoes a ON a.id_avaliacao = pa.fk_Avaliacao_id_avaliacao WHERE pa.fk_Produto_id_produto = ?";

    List<Avaliacao> avaliacoes = jdbcTemplate.query(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id_produto);
        return preparedStatement;
      }
    }, new Mapper());

    return avaliacoes;
  }

  private static class Mapper implements RowMapper<Avaliacao> {
    @Override
    public Avaliacao mapRow(ResultSet rs, int rowNum) throws SQLException {
        Avaliacao avaliacao = new Avaliacao();
    
        avaliacao.setId_avaliacao(rs.getInt("id_avaliacao"));
        avaliacao.setNota_avaliacao(rs.getInt("nota_avaliacao"));

        return avaliacao;
    }
  }
}
