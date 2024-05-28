SET FOREIGN_KEY_CHECKS = 0; 
DROP TABLE IF EXISTS produtos;
DROP TABLE IF EXISTS avaliacoes;
DROP TABLE IF EXISTS produtos_avaliacoes;
DROP TABLE IF EXISTS pessoas;
DROP TABLE IF EXISTS funcionarios;
DROP TABLE IF EXISTS clientes;
DROP TABLE IF EXISTS telefones;
DROP TABLE IF EXISTS enderecos;
DROP TABLE IF EXISTS carrinhos;
DROP TABLE IF EXISTS itens_carrinhos;
DROP TRIGGER IF EXISTS trigger_carrinho; 
SET FOREIGN_KEY_CHECKS = 1; 

CREATE TABLE telefones (
  telefone_pk INT AUTO_INCREMENT PRIMARY KEY,
  telefone VARCHAR(250) NOT NULL
);

CREATE TABLE enderecos (
  endereco_pk INT AUTO_INCREMENT PRIMARY KEY,
  rua VARCHAR(250) NOT NULL,
  bairro VARCHAR(250) NOT NULL,
  estado VARCHAR(250) NOT NULL,
  cidade VARCHAR(250) NOT NULL
);

CREATE TABLE pessoas (
  cpf VARCHAR(250) UNIQUE PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  dt_nascimento VARCHAR(250) NOT NULL,
  fk_endereco_pk INT NOT NULL,
  fk_telefone_pk INT NOT NULL,
  FOREIGN KEY (fk_endereco_pk) REFERENCES enderecos(endereco_pk),
  FOREIGN KEY (fk_telefone_pk) REFERENCES telefones(telefone_pk)
);

CREATE TABLE clientes (
  id_cliente INT AUTO_INCREMENT PRIMARY KEY,
  fk_pessoa_cpf VARCHAR (250),
  FOREIGN KEY (fk_pessoa_cpf) REFERENCES pessoas(cpf)
);

CREATE TABLE funcionarios (
  id_funcionario INT AUTO_INCREMENT PRIMARY KEY,
  fk_pessoa_cpf VARCHAR (250),
  cargo VARCHAR(250),
  FOREIGN KEY (fk_pessoa_cpf) REFERENCES pessoas(cpf)
);

CREATE TABLE produtos (
  id_produto INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  descricao VARCHAR(250) NOT NULL,
  preco DECIMAL(10, 2) NOT NULL,
  imagem VARCHAR(250) NOT NULL,
  fk_funcionario_pk INT NOT NULL,
  FOREIGN KEY (fk_funcionario_pk) REFERENCES funcionarios(id_funcionario)
);

CREATE TABLE avaliacoes (
  id_avaliacao INT AUTO_INCREMENT PRIMARY KEY,
  nota_avaliacao INT NOT NULL
);

CREATE TABLE produtos_avaliacoes (
  fk_Produto_id_produto INT NOT NULL,
  fk_Avaliacao_id_avaliacao INT NOT NULL,
  FOREIGN KEY (fk_Produto_id_produto) REFERENCES produtos(id_produto),
  FOREIGN KEY (fk_Avaliacao_id_avaliacao) REFERENCES avaliacoes(id_avaliacao)
);

CREATE TABLE carrinhos (
  id INT AUTO_INCREMENT PRIMARY KEY,
  id_pedido INT,
  id_carrinho INT,
  id_entrega INT,

  dt_pedido TIMESTAMP,
  dt_entrega TIMESTAMP,
  
  preco_total INT,
  status VARCHAR(250) DEFAULT 'Carrinho',
  forma_pagamento VARCHAR(250)
);

CREATE TABLE itens_carrinhos (
  fk_cliente INT NOT NULL,
  fk_carrinho INT NOT NULL,
  fk_produto INT NOT NULL,
  FOREIGN KEY (fk_cliente) REFERENCES clientes(id_cliente),
  FOREIGN KEY (fk_carrinho) REFERENCES carrinhos(id),
  FOREIGN KEY (fk_produto) REFERENCES produtos(id_produto)
);

INSERT INTO enderecos (rua, bairro, cidade, estado) VALUES 
('Rua Santo Elias', 'Graças', 'Recife', 'Pernambuco');

INSERT INTO telefones (telefone) VALUES
('8199841341, 898231931');

INSERT INTO pessoas (cpf, nome, dt_nascimento, fk_endereco_pk, fk_telefone_pk) VALUES 
('13755959461', 'Edmar', '10/12/2003', 1, 1);

INSERT INTO clientes (fk_pessoa_cpf) VALUES 
('13755959461');

INSERT INTO funcionarios (fk_pessoa_cpf, cargo) VALUES 
('13755959461', 'Administrador');

INSERT INTO produtos (nome, descricao, preco, imagem, fk_funcionario_pk) VALUES
('Suplemento 1', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsum, atque. Libero maiores rem minus repellendus ab officiis voluptatum pariatur eius earum delectus. Iste iure laudantium architecto officia optio eum repellat. 1', 20.00, 'https://images.unsplash.com/photo-1559087316-6b27308e53f6?q=80&w=2894&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1),
('Suplemento 2', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsum, atque. Libero maiores rem minus repellendus ab officiis voluptatum pariatur eius earum delectus. Iste iure laudantium architecto officia optio eum repellat. 2', 35.00, 'https://images.unsplash.com/photo-1559087316-6b27308e53f6?q=80&w=2894&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1),
('Suplemento 3', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsum, atque. Libero maiores rem minus repellendus ab officiis voluptatum pariatur eius earum delectus. Iste iure laudantium architecto officia optio eum repellat. 3', 40.00, 'https://images.unsplash.com/photo-1559087316-6b27308e53f6?q=80&w=2894&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D', 1);

INSERT INTO avaliacoes (nota_avaliacao) VALUES 
(3),
(5),
(4),
(1),
(2);

INSERT INTO produtos_avaliacoes (fk_Produto_id_produto, fk_Avaliacao_id_avaliacao) VALUES 
(1, 1),
(1, 4),
(1, 5),
(2, 2),
(3, 3);