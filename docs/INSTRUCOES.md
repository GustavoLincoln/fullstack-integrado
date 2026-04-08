# 📘 Instruções de Execução e Avaliação

## 📌 Objetivo

Este documento descreve como executar o projeto e os critérios utilizados para avaliação da solução proposta.

---

# 🚀 Como Executar o Projeto

## 🐳 Pré-requisitos

Certifique-se de ter instalado:

- Docker
- Docker Compose
- Node.js (para frontend)
- Angular CLI (opcional)

---

## ▶️ 1. Subir Backend + Banco

Na raiz do projeto:

```bash
docker-compose up --build -d

## 🔍 2. Verificar logs

```bash
docker-compose logs -f

## 🌐 3. Acessar Backend
API disponível em:
http://localhost:8080

📘 4. Swagger (documentação da API)
http://localhost:8080/swagger-ui.html

💻 5. Executar Frontend
O frontend deve ser executado separadamente:

```bash
cd frontend
npm install
ng serve

Acesse:
http://localhost:4200

🧪 6. Executar Testes
```bash
cd backend-module
mvn test

🗄️ Banco de Dados
O PostgreSQL é iniciado automaticamente via Docker.

Scripts executados:
db/schema.sql
db/seed.sql

🔄 Funcionalidade Principal
Transferência entre benefícios
Endpoint:
POST /beneficios/transferencias

Exemplo de requisição:
{
  "fromId": 1,
  "toId": 2,
  "valor": 50.00
}

🧠 Regras de Negócio

A transferência garante:
Validação de saldo suficiente
Proibição de transferência para o mesmo benefício
Consistência transacional
Controle de concorrência

🐞 Correção do Bug (EJB)
Problemas encontrados:
Transferência sem validação de saldo
Ausência de controle de concorrência
Possibilidade de inconsistência de dados

Soluções implementadas:
Validação de saldo insuficiente
Uso de LockModeType.PESSIMISTIC_WRITE
Execução dentro de transação
Garantia de atomicidade