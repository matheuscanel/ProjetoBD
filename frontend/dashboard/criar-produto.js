const form = document.getElementById('criar-editar-produto')

async function buscarDadosSeEditar() {
  const produto = getParametroUrl('id_produto')

  if (!produto) {
    document.getElementById('titulo').textContent = 'Criar Produto'
    return null
  }

  try {
    const response = await fetch(`http://localhost:8080/produtos/${produto}`)
    const data = await response.json()

    document.getElementById('nome').setAttribute('value', data.nome)
    document.getElementById('descricao').setAttribute('value', data.descricao)
    document.getElementById('preco').setAttribute('value', Number(data.preco))
    document.getElementById('imagem').setAttribute('value', data.imagem)

    document.getElementById('titulo').textContent = 'Editar Produto ' + data.nome

  } catch (error) {
    console.log(error)
    alert('Ocorre um erro.')    
  }
}

buscarDadosSeEditar()

form.addEventListener('submit', async (event) => {
  event.preventDefault()

  const produto = getParametroUrl('id_produto')
  const nome = event.target.nome.value
  const descricao = event.target.descricao.value
  const preco = event.target.preco.value
  const imagem = event.target.imagem.value

  if (!nome || !descricao || !preco || !imagem) {
    return alert('Preencha todos os campos')
  }

  if (produto) {
    await fetch(`http://localhost:8080/produtos/${produto}`, {
      method: "PUT",
      body: JSON.stringify({
        nome,
        descricao,
        preco,
        imagem
      }),
      headers: {
        "Content-Type": "application/json"
      }
    })

    alert('Produto atualizado com sucesso!')
  } else {
    await fetch(`http://localhost:8080/produtos`, {
      method: "POST",
      body: JSON.stringify({
        nome,
        descricao,
        preco,
        imagem
      }),
      headers: {
        "Content-Type": "application/json"
      }
    })

    alert('Produto criado com sucesso!')
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
