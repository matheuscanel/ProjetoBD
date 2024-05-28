package br.cesar.bd.trabalho.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cesar.bd.trabalho.dao.ProdutoDAO;
import br.cesar.bd.trabalho.dtos.CriarProdutoDTO;
import br.cesar.bd.trabalho.dtos.ProdutoComAvaliacoesDTO;
import br.cesar.bd.trabalho.entidades.Produto;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("produtos")
public class ProdutoController {
  @Autowired
  private ProdutoDAO produtoDao;

  @GetMapping()
  public List<Produto> buscarTodosProdutos() {
    List<Produto> produtos = produtoDao.buscarProdutos();

    return produtos;
  }

  @GetMapping("/avaliados")
  public List<ProdutoComAvaliacoesDTO> buscarProdutosMaisBemAvaliados() {
    List<ProdutoComAvaliacoesDTO> produtos = produtoDao.buscarProdutosMaisAvaliados();

    return produtos;
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> buscarProduto(@PathVariable Integer id) {
    Optional<Produto> produto = produtoDao.buscarProduto(id);

    if (produto.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    return ResponseEntity.status(200).body(produto);
  }

  @PostMapping()
  public ResponseEntity<CriarProdutoDTO> criarProduto(@RequestBody CriarProdutoDTO produtoDto) {
    produtoDao.criar(produtoDto);

    return ResponseEntity.status(HttpStatus.CREATED).body(produtoDto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<CriarProdutoDTO> atualizarProduto(@RequestBody CriarProdutoDTO produtoDto, @PathVariable Integer id) {
    produtoDao.atualizar(produtoDto, id);

    return ResponseEntity.status(HttpStatus.CREATED).body(produtoDto);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deletarProduto(@PathVariable Integer id) {
    produtoDao.deletar(id);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
