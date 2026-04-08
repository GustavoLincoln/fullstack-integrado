# 🏗️ Desafio Fullstack Integrado

## 📌 Visão Geral

Este projeto implementa uma solução completa em camadas, contendo:

- 🗄️ Banco de dados (PostgreSQL)
- ⚙️ Backend (Spring Boot)
- 🧠 Módulo EJB (lógica de negócio com controle de concorrência)
- 💻 Frontend (Angular — executado separadamente)

A aplicação permite o gerenciamento de benefícios e a transferência de valores entre eles, garantindo consistência transacional e integridade dos dados.

---

## 🚀 Tecnologias Utilizadas

- Java 8+
- Spring Boot
- JPA / Hibernate
- PostgreSQL
- Docker / Docker Compose
- Angular
- Swagger (OpenAPI)

---

## 📦 Estrutura do Projeto

```bash
fullstack-integrado/
 ├── db/                  # Scripts SQL (schema e seed)
 ├── ejb-module/          # Lógica de negócio (EJB)
 ├── backend-module/      # API REST (Spring Boot)
 ├── frontend/            # Aplicação Angular
 ├── docker-compose.yml   # Orquestração dos serviços