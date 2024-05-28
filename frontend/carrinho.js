const produtos = document.getElementById('produtos')
const salvarPedido = document.getElementById('salvar-pedido')

const empty = document.getElementById('empty')

if (!localStorage.getItem('carrinhoId')) {
  empty.innerHTML = '<p class="text-center">Nenhum item no carrinho</p>'
}

salvarPedido.addEventListener('submit', async (event) => {
  event.preventDefault()

  const formaPagamento = event.target.formaPagamento.value

  if (!formaPagamento) {
    return alert('Preencha o campo')
  }
  
  const carrinho = localStorage.getItem('carrinhoId')

  try {
    await fetch(`http://localhost:8080/carrinhos/${carrinho}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        dtPedido: new Date(),
        precoTotal: 0,
        status: "Pago",
        formaPagamento
      })
    })

    alert('Pedido realizado com sucesso!')

    localStorage.removeItem('carrinhoId')
  } catch (error) {
    console.log(error)
    alert('Ocorreu um erro.')
  }
})

async function inserirProdutos() {
  try {
    const carrinho = localStorage.getItem('carrinhoId')

    const response = await fetch(`http://localhost:8080/carrinhos/${carrinho}/itens`)

    const data = await response.json()

    let html = ''

    data.forEach(item => (

      html += `
      <div class="grid grid-cols-2 gap-8 items-center">
        <img src=${item.imagem} class="w-full h-32" alt="">
        <div class="flex flex-col gap-2">
          <h4 class="text-xl">${item.nome}</h4>
  
          <strong>Preço: ${new Intl.NumberFormat("pt-BR", { style: "currency", currency: "BRL" }).format(item.preco)}</strong>
        </div>
      </div>
      `
    ))

    const total = data.reduce((acc, item) => acc + item.preco, 0)

    document.getElementById('total').textContent = "Total: " + new Intl.NumberFormat("pt-BR", {
      style: "currency",
      currency: "BRL"
    }).format(total)
    produtos.innerHTML = html
  } catch (error) {
    console.log(error)
  }
}

inserirProdutos()