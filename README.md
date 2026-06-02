# controle_estoque_java
# 📦 Sistema de Controle de Estoque

Projeto desenvolvido para a disciplina de Programação da UNISUL, com o objetivo de implementar um sistema de controle de estoque utilizando Java.

---

## 🎯 Objetivo

Desenvolver uma aplicação capaz de gerenciar produtos, categorias e movimentações de estoque, permitindo o controle eficiente de entradas, saídas e disponibilidade de itens.

---

## ⚙️ Funcionalidades

### 📌 Produtos
- Cadastro de produtos
- Listagem de produtos
- Atualização de dados
- Remoção de produtos

### 📌 Categorias
- Cadastro de categorias
- Listagem de categorias
- Atualização de dados
- Remoção de categorias

### 📌 Movimentações
- Registro de entrada de produtos
- Registro de saída de produtos
- Atualização automática do estoque
- Validação de estoque mínimo e máximo

### 📊 Relatórios
- Lista de preços
- Balanço físico/financeiro
- Produtos abaixo da quantidade mínima
- Quantidade de produtos por categoria
- Produto com maior entrada e saída

---

# Requisitos Funcionais

## RF01 - Gerenciar Categorias

O sistema deve permitir cadastrar, consultar, alterar e excluir categorias de produtos.

## RF02 - Gerenciar Produtos

O sistema deve permitir cadastrar, consultar, alterar e excluir produtos.

## RF03 - Associar Produto à Categoria

O sistema deve permitir vincular um produto a uma categoria cadastrada.

## RF04 - Registrar Entrada de Produtos

O sistema deve permitir registrar movimentações de entrada no estoque.

## RF05 - Registrar Saída de Produtos

O sistema deve permitir registrar movimentações de saída no estoque.

## RF06 - Atualizar Quantidade em Estoque

O sistema deve atualizar automaticamente a quantidade disponível após cada movimentação.

## RF07 - Registrar Histórico de Movimentações

O sistema deve armazenar todas as entradas e saídas realizadas.

## RF08 - Emitir Relatório de Lista de Preços

O sistema deve gerar uma relação de produtos contendo nome, preço, unidade e categoria.

## RF09 - Emitir Relatório de Balanço Físico-Financeiro

O sistema deve apresentar a quantidade em estoque, valor individual e valor total do estoque.

## RF10 - Emitir Relatório de Produtos Abaixo do Mínimo

O sistema deve listar produtos cuja quantidade esteja abaixo do estoque mínimo.

## RF11 - Emitir Relatório de Produtos por Categoria

O sistema deve apresentar a quantidade de produtos agrupados por categoria.

## RF12 - Emitir Relatório de Maior Entrada e Maior Saída

O sistema deve identificar os produtos com maior volume de entrada e saída.

## RF13 - Reajustar Preços

O sistema deve permitir reajustar os preços dos produtos através de um percentual informado.

## RF14 - Alertar Estoque Mínimo

O sistema deve emitir aviso quando a quantidade atingir ou ficar abaixo do estoque mínimo.

## RF15 - Alertar Estoque Máximo

O sistema deve emitir aviso quando a quantidade ultrapassar o estoque máximo.

# Requisitos Não Funcionais

## RNF01 - Linguagem de Programação

O sistema deve ser desenvolvido utilizando Java.

## RNF02 - Banco de Dados

O sistema deve utilizar MySQL para persistência dos dados.

## RNF03 - Interface Gráfica

O sistema deve possuir interface gráfica desenvolvida com Java Swing.

## RNF04 - Arquitetura

O sistema deve seguir o padrão de organização em camadas (View, Service, DAO e Model).

## RNF05 - Persistência

Os dados cadastrados devem permanecer armazenados após o encerramento da aplicação.

## RNF06 - Padronização de Código

O código-fonte deve seguir convenções de nomenclatura, identação e organização de pacotes.

## RNF07 - Documentação

O sistema deve possuir documentação JavaDoc nas principais classes e métodos.

## RNF08 - Controle de Versão

O desenvolvimento deve ser realizado utilizando Git e GitHub.

## RNF09 - Usabilidade

A interface deve permitir a execução das operações de forma simples e intuitiva.

## RNF10 - Integridade dos Dados

O sistema deve impedir movimentações que resultem em estoque negativo.

---

## 🛠️ Tecnologias Utilizadas

- Java SE 25 (OpenJDK 25.0.2 LTS)
- JDK 25 (OpenJDK 25.0.2+10-LTS)
- Apache NetBeans IDE 29
- MySQL Workbench 8.0
- MySQL 9.7.0
- GitHub
  
---

## 🚀 Como Executar

1. Clone o repositório:

git clone https://github.com/seu-usuario/controle_estoque_java.git

2. Abra o projeto no NetBeans

3. Execute a classe `Principal.java`

---

## 🗄️ Banco de Dados

O sistema utiliza MySQL para persistência de dados.

O script de criação do banco está disponível na pasta do projeto.

---

## 👥 Integrantes

- Gabriel Alexandre Signori Conci  RA: 10725213812 GitHub: https://github.com/gabrielconci
  
- Guilherme Rafael de Souza  RA: 10726111483 GitHub: https://github.com/Gui53
  
- Bruno Nedel de Souza  RA: 1072619644 GitHub: https://github.com/BrunoNedel007
  
- Enzo Lindemayer Silva  RA: 10725214856 Github: https://github.com/enzolsilva
   
- Guilherme Paulo de Souza Goes  RA: 10726112930 GitHub: https://github.com/guilhermepsg
