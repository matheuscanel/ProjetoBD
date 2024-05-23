const produtosContainer = document.getElementById('produtos-container')

async function inserirProdutos() {
  const response = await fetch('http://localhost:8080/produtos')
  const data = await response.json()

  let html = ''

  data.forEach(item => {
    html += `    
      <div class="flex flex-col border border-gray-300 rounded-md overflow-hidden">
        <img src=${item.imagem} class="w-full h-48" alt="">
        <div class="p-4 flex flex-col gap-6">
          <h4 class="text-xl font-semibold">${item.nome}</h4>

          <strong>Preço: ${new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL" }).format(item.preco)}</strong>
          <div class="flex items-center justify-between">
            <a href="produto.html?id_produto=${item.id_produto}" class="p-2 border-none rounded-md text-white text-sm bg-blue-500">Ver mais</a>
            <a href="avaliar-produto.html?id_produto=${item.id_produto}" class="p-2 border border-blue-500 rounded-md text-blue-500 text-sm bg-transparent">Avaliar</a>
          </div>
        </div>
      </div>
    `
  })

  produtosContainer.innerHTML = html
}

inserirProdutos()