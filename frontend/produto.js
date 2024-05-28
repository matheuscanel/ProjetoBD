const produtoContainer = document.getElementById('produto-container')
const adicionarProduto = document.getElementById('adicionar-carrinho')

async function buscarProduto() {
  try {
    const id_produto = getParametroUrl("id_produto")

    const responseProduto = await fetch(`http://localhost:8080/produtos/${id_produto}`)
    const produto = await responseProduto.json()

    const responseAvaliacoes = await fetch(`http://localhost:8080/avaliacoes/${id_produto}`)
    const avaliacoes = await responseAvaliacoes.json()

    document.getElementById('imagem').setAttribute('src', produto.imagem)
    document.getElementById('nome').textContent = produto.nome
    document.getElementById('descricao').textContent = produto.descricao
    document.getElementById('media_avaliacoes').textContent = "Média das avaliações: " + avaliacoes.media.toFixed(2)
    document.getElementById('qtd_avaliacoes').textContent = "Quantidade de avaliações: " + avaliacoes.avaliacoes.length

  } catch (error) {
    console.log(error)
  }
}

document.addEventListener('DOMContentLoaded', () => {
  console.log({adicionarProduto})
})

buscarProduto().then(() => {
  if (adicionarProduto) {
    adicionarProduto.addEventListener('click', async () => {
      const produto = Number(getParametroUrl("id_produto"))

      const carrinhoId = localStorage.getItem('carrinhoId')
      const idCliente = Number(localStorage.getItem('clienteId'))

      try {
        if (carrinhoId) {
          await fetch('http://localhost:8080/carrinhos/itens', {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              fkCliente: idCliente,
              fkCarrinho: Number(carrinhoId),
              fkProduto: produto
            })
          })
        } else {
          const response = await fetch('http://localhost:8080/carrinhos', {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
          })
    
          const data = await response.json()
          
          localStorage.setItem('carrinhoId', data)

          await fetch('http://localhost:8080/carrinhos/itens', {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify({
              fkCliente: idCliente,
              fkCarrinho: data,
              fkProduto: produto
            })
          })
        }
      } catch (error) {
        console.log(error)
      }
    })
  }
})

function getParametroUrl(parameterName) {
  var result = null,
      tmp = [];
  location.search
      .substr(1)
      .split("&")
      .forEach(function (item) {
        tmp = item.split("=");
        if (tmp[0] === parameterName) result = decodeURIComponent(tmp[1]);
      });
  return result;
}


