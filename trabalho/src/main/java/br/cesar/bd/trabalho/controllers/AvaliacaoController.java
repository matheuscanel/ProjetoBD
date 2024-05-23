package br.cesar.bd.trabalho.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cesar.bd.trabalho.dao.AvaliacaoDAO;
import br.cesar.bd.trabalho.dtos.BuscarProProdutoDTO;
import br.cesar.bd.trabalho.dtos.CriarAvaliacaoDTO;
import br.cesar.bd.trabalho.dtos.CriarProdutoDTO;
import br.cesar.bd.trabalho.entidades.Avaliacao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("avaliacoes")
public class AvaliacaoController {
  
  @Autowired
  private AvaliacaoDAO avaliacaoDao;

  @PostMapping()
  public ResponseEntity<CriarProdutoDTO> criarProduto(@RequestBody CriarAvaliacaoDTO avaliacaoDTO) {
    Integer id = avaliacaoDao.criar(avaliacaoDTO.getNota_avaliacao());

    avaliacaoDao.atribuirAvaliacaoAProduto(avaliacaoDTO.getId_produto(), id);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("/{id_produto}")
  public ResponseEntity<Object> buscarPorProduto(@PathVariable Integer id_produto) {
    BuscarProProdutoDTO buscarProProdutoDTO = new BuscarProProdutoDTO();

    List<Avaliacao> avaliacoes = avaliacaoDao.buscarPorProduto(id_produto);
    Double media = avaliacaoDao.buscarMediaPorProduto(id_produto);

    buscarProProdutoDTO.setAvaliacoes(avaliacoes);
    buscarProProdutoDTO.setMedia(media);

    return ResponseEntity.ok().body(buscarProProdutoDTO);
  }
}
