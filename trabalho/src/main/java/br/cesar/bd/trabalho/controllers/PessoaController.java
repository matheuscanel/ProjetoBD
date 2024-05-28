package br.cesar.bd.trabalho.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.cesar.bd.trabalho.dao.PessoaDAO;
import br.cesar.bd.trabalho.dtos.CadastroClienteDTO;
import br.cesar.bd.trabalho.dtos.CadastroFuncionarioDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

  @Autowired
  private PessoaDAO pessoaDAO;
  
  @PostMapping("/clientes")
  public ResponseEntity<Object> criarCliente(@RequestBody CadastroClienteDTO cadastroClienteDTO) {
    Integer enderecoPk  = pessoaDAO.criarEndereco(cadastroClienteDTO);
    Integer telefonePk  = pessoaDAO.criarTelefones(cadastroClienteDTO);

    pessoaDAO.criarPessoa(cadastroClienteDTO, telefonePk, enderecoPk);

    Integer idCliente = pessoaDAO.criarCliente(cadastroClienteDTO.getCpf());

    return ResponseEntity.status(201).body(idCliente);
  }
  
  @PostMapping("/funcionarios")
  public ResponseEntity<Object> criarFuncionario(@RequestBody CadastroFuncionarioDTO cadastroFuncionarioDTO) {
    Integer enderecoPk  = pessoaDAO.criarEndereco(cadastroFuncionarioDTO);
    Integer telefonePk  = pessoaDAO.criarTelefones(cadastroFuncionarioDTO);

    pessoaDAO.criarPessoa(cadastroFuncionarioDTO, telefonePk, enderecoPk);

    pessoaDAO.criarFuncionario(cadastroFuncionarioDTO.getCpf(), cadastroFuncionarioDTO.getCargo());

    return ResponseEntity.status(201).build();
  }
}
