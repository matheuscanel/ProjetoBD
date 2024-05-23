const produtoContainer = document.getElementById('produto-container')

async function buscarProduto() {
  try {
    const id_produto = getParametroUrl("id_produto")

    const responseProduto = await fetch(`http://localhost:8080/produtos/${id_produto}`)
    const produto = await responseProduto.json()

    const responseAvaliacoes = await fetch(`http://localhost:8080/avaliacoes/${id_produto}`)
    const avaliacoes = await responseAvaliacoes.json()

    const html = `
      <img class="rounded-md h-96" src=${produto.imagem} alt=${produto.nome}>

      <div class="flex flex-col justify-between">
        <h1 class="text-3xl font-bold text-gray-900">${produto.nome}</h1>

        <p class="text-gray-600">${produto.descricao}</p>

        <div class="flex flex-col gap-1">
          <span class="text-gray-400">Média das avaliações: ${avaliacoes.media.toFixed(2)}</span>
          <span class="text-gray-400">Quantidade de avaliações: ${avaliacoes.avaliacoes.length}</span>
        </div>

        <div class="flex gap-6">
          <button class="bg-blue-500 w-full p-2 text-white border-none rounded-md">Adicionar ao carrinho</button>
          <a href="avaliar-produto.html?id_produto=${id_produto}" class="w-full p-2 border text-center text-blue-500 border-blue-500 rounded-md">Avaliar</a>
        </div>
      </div>
    `

    produtoContainer.innerHTML = html
  } catch (error) {
    console.log(error)
  }
}

buscarProduto()

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


