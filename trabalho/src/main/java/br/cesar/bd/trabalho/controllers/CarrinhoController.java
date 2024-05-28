package br.cesar.bd.trabalho.controllers;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cesar.bd.trabalho.dao.CarrinhoDAO;
import br.cesar.bd.trabalho.dtos.AtualizarCarrinhoDTO;
import br.cesar.bd.trabalho.dtos.CriarItemCarrinhoDTO;
import br.cesar.bd.trabalho.dtos.ItensCarrinhoDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("carrinhos")
public class CarrinhoController {
  @Autowired
  private CarrinhoDAO carrinhoDAO;

  @PostMapping("/itens")
  public ResponseEntity<Object> criarItem(@RequestBody CriarItemCarrinhoDTO criarItemCarrinhoDTO) {
    System.out.println(criarItemCarrinhoDTO);
    carrinhoDAO.criarItemCarrinho(criarItemCarrinhoDTO);
    return ResponseEntity.status(200).build();
  }

  @PostMapping()
  public ResponseEntity<Integer> criarCarrinho(CriarItemCarrinhoDTO criarItemCarrinhoDTO) {
    Integer carrinhoPk = carrinhoDAO.criarCarrinho();
    return ResponseEntity.status(200).body(carrinhoPk);
  }

  @GetMapping("/{id}/itens")
  public List<ItensCarrinhoDTO> buscarItensDeCarrinho(@PathVariable Integer id) {
    return carrinhoDAO.buscarItensDeCarrinho(id);
  }  

  @PutMapping("/{id}")
  public ResponseEntity<Object> atualizarPedido(@RequestBody AtualizarCarrinhoDTO atualizarCarrinhoDTO, @PathVariable Integer id) {
    List<ItensCarrinhoDTO> itensCarrinhoDTO = carrinhoDAO.buscarItensDeCarrinho(id);

    BigDecimal total = new BigDecimal("0.0");

    for (ItensCarrinhoDTO itemCarrinhoDTO : itensCarrinhoDTO) {
      total = total.add(itemCarrinhoDTO.getPreco());
    }

    atualizarCarrinhoDTO.setPrecoTotal(total);

    carrinhoDAO.atualizar(atualizarCarrinhoDTO, id);
    return ResponseEntity.status(200).build();
  }

}
