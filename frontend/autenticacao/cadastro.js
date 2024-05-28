const form = document.getElementById('cadastro')

form.addEventListener('submit', async (event) => {
  event.preventDefault()

  const nome = event.target.nome.value
  const cpf = event.target.cpf.value
  const dt_nascimento = event.target.dt_nascimento.value
  const rua = event.target.rua.value
  const bairro = event.target.bairro.value
  const estado = event.target.estado.value
  const cidade = event.target.cidade.value
  const telefones = event.target.telefones.value

  if (!nome || !cpf || !dt_nascimento || !rua || !bairro || !estado || !cidade || !telefones) {
    return alert('Preencha todos os campos!')
  }

  try {
    const response = await fetch('http://localhost:8080/pessoas/clientes', {
      method: "POST",
      body: JSON.stringify({
        nome,
        cpf,
        dtNascimento: dt_nascimento,
        rua,
        bairro,
        estado,
        cidade,
        telefones
      }),
      headers: {
        "Content-Type": "application/json"
      }
    })

    if (!response.ok) {
      return alert('Usuário já cadastrado')
    }

    const data = await response.json()

    localStorage.setItem('clienteId', data)

    window.location.href = "http://127.0.0.1:5500/frontend/index.html"

    alert("Conta criada com sucesso!")
  } catch (error) {
    alert("Ocorreu um erro na criação da avaliação!")
    console.log(error)
  }
})
