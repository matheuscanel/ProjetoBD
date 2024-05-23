SET FOREIGN_KEY_CHECKS = 0; 
DROP TABLE IF EXISTS pessoas;
DROP TABLE IF EXISTS produtos;
DROP TABLE IF EXISTS avaliacoes;
DROP TABLE IF EXISTS produtos_avaliacoes;
SET FOREIGN_KEY_CHECKS = 1; 

CREATE TABLE pessoas (
  cpf VARCHAR(250) PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  dt_nascimento VARCHAR(250) NOT NULL
);

-- finalizar
-- CREATE TABLE funcionarios (
--   id INT AUTO_INCREMENT PRIMARY KEY,
--   cargo VARCHAR(250) NOT NULL,
-- )

CREATE TABLE produtos (
  id_produto INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(250) NOT NULL,
  descricao VARCHAR(250) NOT NULL,
  preco DECIMAL(10, 2) NOT NULL,
  imagem VARCHAR(250) NOT NULL
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

INSERT INTO produtos (nome, descricao, preco, imagem) VALUES
('Suplemento 1', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsum, atque. Libero maiores rem minus repellendus ab officiis voluptatum pariatur eius earum delectus. Iste iure laudantium architecto officia optio eum repellat. 1', 20.00, 'https://images.unsplash.com/photo-1559087316-6b27308e53f6?q=80&w=2894&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Suplemento 2', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsum, atque. Libero maiores rem minus repellendus ab officiis voluptatum pariatur eius earum delectus. Iste iure laudantium architecto officia optio eum repellat. 2', 35.00, 'https://images.unsplash.com/photo-1559087316-6b27308e53f6?q=80&w=2894&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D'),
('Suplemento 3', 'Lorem ipsum dolor, sit amet consectetur adipisicing elit. Ipsum, atque. Libero maiores rem minus repellendus ab officiis voluptatum pariatur eius earum delectus. Iste iure laudantium architecto officia optio eum repellat. 3', 40.00, 'https://images.unsplash.com/photo-1559087316-6b27308e53f6?q=80&w=2894&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D');

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