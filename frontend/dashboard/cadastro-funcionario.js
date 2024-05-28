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
  const cargo = event.target.cargo.value

  if (!nome || !cpf || !dt_nascimento || !rua || !bairro || !estado || !cidade || !telefones || !cargo) {
    return alert('Preencha todos os campos!')
  }

  try {
    await fetch('http://localhost:8080/pessoas/funcionarios', {
      method: "POST",
      body: JSON.stringify({
        nome,
        cpf,
        dtNascimento: dt_nascimento,
        rua,
        bairro,
        estado,
        cidade,
        telefones,
        cargo
      }),
      headers: {
        "Content-Type": "application/json"
      }
    })

    alert("Conta criada com sucesso!")
  } catch (error) {
    alert("Ocorreu um erro na criação da avaliação!")
    console.log(error)
  }
})
