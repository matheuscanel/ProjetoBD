package br.cesar.bd.trabalho.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.cesar.bd.trabalho.dtos.DadosDTO;

@Repository
public class DadosDAO {
  
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public List<DadosDTO> buscarDados() {
    String sql = """
      SELECT
        sum(preco_total) as lucro,
        count(id) as pedidos
      FROM carrinhos;
    """;

    return jdbcTemplate.query(sql, (rs, rowNum) -> {
      DadosDTO dadosDTO = new DadosDTO();

      dadosDTO.setLucro(rs.getInt("lucro"));
      dadosDTO.setTotalPedidos(rs.getInt("pedidos"));

      return dadosDTO;
    });
  }

}
