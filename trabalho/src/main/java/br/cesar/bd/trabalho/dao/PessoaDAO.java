package br.cesar.bd.trabalho.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import br.cesar.bd.trabalho.dtos.CadastroClienteDTO;

@Repository
public class PessoaDAO {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public void criarPessoa(CadastroClienteDTO cadastroClienteDTO, Integer telefonePk, Integer enderecoPk) {
    String sql = "INSERT INTO pessoas (cpf, nome, dt_nascimento, fk_endereco_pk, fk_telefone_pk) VALUES (?, ?, ?, ?, ?)";

    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cadastroClienteDTO.getCpf());
            statement.setString(2, cadastroClienteDTO.getNome());
            statement.setString(3, cadastroClienteDTO.getDtNascimento());
            statement.setInt(4, enderecoPk);
            statement.setInt(5, telefonePk);
            return statement;
        }
    }, holder);
  }

  public Integer criarCliente(String cpf) {
    String sql = "INSERT INTO clientes (fk_pessoa_cpf) VALUES (?)";
    
    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cpf);
            return statement;
        }
    }, holder);

    Integer primaryKey = holder.getKey().intValue();

    return primaryKey;
  }

  public void criarFuncionario(String cpf, String cargo) {
    String sql = "INSERT INTO funcionarios (fk_pessoa_cpf, cargo) VALUES (?, ?)";

    jdbcTemplate.update(sql, cpf, cargo);
  }

  public Integer criarEndereco(CadastroClienteDTO cadastroClienteDTO) {
    String sql = "INSERT INTO enderecos (rua, bairro, estado, cidade) VALUES (?, ?, ?, ?)";

    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cadastroClienteDTO.getRua());
            statement.setString(2, cadastroClienteDTO.getBairro());
            statement.setString(3, cadastroClienteDTO.getEstado());
            statement.setString(4, cadastroClienteDTO.getCidade());
            return statement;
        }
    }, holder);
    
    Integer primaryKey = holder.getKey().intValue();
    
    return primaryKey;
  }

  public Integer criarTelefones(CadastroClienteDTO cadastroClienteDTO) {
    String sql = "INSERT INTO telefones (telefone) VALUES (?)";

    GeneratedKeyHolder holder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
        @Override
        public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, cadastroClienteDTO.getTelefones());
            return statement;
        }
    }, holder);
    
    Integer primaryKey = holder.getKey().intValue();
    
    return primaryKey;
  }
}
