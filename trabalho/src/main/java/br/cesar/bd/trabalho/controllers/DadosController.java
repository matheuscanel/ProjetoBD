package br.cesar.bd.trabalho.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cesar.bd.trabalho.dao.DadosDAO;
import br.cesar.bd.trabalho.dtos.DadosDTO;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("dados")
public class DadosController {
  
  @Autowired
  private DadosDAO dadosDAO;

  @GetMapping()
  public ResponseEntity<Object> buscarDados() {
    List<DadosDTO> dados = dadosDAO.buscarDados();

    return ResponseEntity.ok().body(dados);
  }
}
