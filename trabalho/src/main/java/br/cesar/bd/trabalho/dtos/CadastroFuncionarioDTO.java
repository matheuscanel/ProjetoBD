package br.cesar.bd.trabalho.dtos;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CadastroFuncionarioDTO extends CadastroClienteDTO {
  private String cargo;
}
