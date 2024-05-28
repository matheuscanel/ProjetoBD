const produtosContainer = document.getElementById('produtos-container')
const produtosMaisAvaliadosContainer = document.getElementById('produtos-mais-avaliados-container')

async function inserirProdutos() {
  const response = await fetch('http://localhost:8080/produtos')
  const data = await response.json()

  let html = ''

  data.forEach(item => {
    html += `    
      <tr class="border-b transition-colors hover:bg-muted/50 data-[state=selected]:bg-muted">
        <td class="p-2 align-middle [&:has([role=checkbox])]:pr-0 [&>[role=checkbox]]:translate-y-[2px]">${item.id_produto}</td>
        <td class="p-2 align-middle [&:has([role=checkbox])]:pr-0 [&>[role=checkbox]]:translate-y-[2px]">${item.nome}</td>
        <td class="p-2 align-middle [&:has([role=checkbox])]:pr-0 [&>[role=checkbox]]:translate-y-[2px]">${item.preco}</td>
        <td class="p-2 align-middle [&:has([role=checkbox])]:pr-0 [&>[role=checkbox]]:translate-y-[2px]">${item.descricao.slice(0, 30)}...</td>
        <td class="p-2 align-middle [&:has([role=checkbox])]:pr-0 [&>[role=checkbox]]:translate-y-[2px] text-right">
          <div class="flex gap-3 items-center justify-end">
            <a href="criar-produto.html?id_produto=${item.id_produto}">Editar</a>
          </div>
        </td>
      </tr>
    `
  })

  produtosContainer.innerHTML = html
}

async function inserirProdutosMaisAvaliados() {
  const response = await fetch('http://localhost:8080/produtos/avaliados')
  const data = await response.json()

  let html = ''

  data.forEach(item => {
    html += `    
    <div class="flex flex-col border border-gray-300 rounded-md overflow-hidden">
      <img src=${item.imagem} class="w-full h-48" alt="">
      <div class="p-4 flex flex-col gap-6">
        <div class="flex items-end justify-between">
          <h4 class="text-xl font-semibold">${item.nome}</h4>

          <span class="text-xs">Media Avaliação: ${item.mediaAvaliacao}</span>
        </div>

        <strong>Preço: ${new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL" }).format(item.preco)}</strong>
        <div class="flex items-center justify-between">
          <a href="produto.html?id_produto=${item.id_produto}" class="p-2 border-none rounded-md text-white text-sm bg-blue-500">Ver mais</a>
          <a href="avaliar-produto.html?id_produto=${item.id_produto}" class="p-2 border border-blue-500 rounded-md text-blue-500 text-sm bg-transparent">Avaliar</a>
        </div>
      </div>
    </div>
    `
  })

  produtosMaisAvaliadosContainer.innerHTML = html
}

async function inserirDados() {
  try {
    const lucro = document.getElementById('lucro')
    const totalPedidos = document.getElementById('total-pedidos')

    const response = await fetch('http://localhost:8080/dados')

    const data = await response.json()

    lucro.textContent = data[0].lucro
    totalPedidos.textContent = data[0].totalPedidos

    console.log({ data })
  } catch (error) {
    console.log(error)
  }
}

inserirProdutos()
inserirDados()
inserirProdutosMaisAvaliados()