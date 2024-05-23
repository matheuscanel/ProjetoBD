const form = document.getElementById('avaliar-produto')

form.addEventListener('submit', async (event) => {
  event.preventDefault()

  const nota_avaliacao = event.target.nota_avaliacao.value

  if (!nota_avaliacao) {
    return alert('Preencha o campo de avaliacão!')
  }

  const produto = getParametroUrl('id_produto')

  try {
    await fetch('http://localhost:8080/avaliacoes', {
      method: "POST",
      body: JSON.stringify({
        id_produto: Number(produto),
        nota_avaliacao: Number(nota_avaliacao)
      }),
      headers: {
        "Content-Type": "application/json"
      }
    })

    alert("Avaliação criada com sucesso!")
  } catch (error) {
    alert("Ocorreu um erro na criação da avaliação!")
    console.log(error)
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
