# FOR DUMMIES -> docker compose down -v 
# -> docker compose up --build


# RASCUNHO  nery-farma-project
# 💊 NeryFarma

> Sistema de gestão de medicamentos e insumos para Unidades Básicas de Saúde (UBS).
> Inspirado em Anna Nery, mãe da enfermagem brasileira.

---

## 📋 Sobre o Projeto

As UBS são a porta de entrada do SUS e atendem milhões de brasileiros diariamente. Um dos problemas mais críticos nessas unidades é o controle manual e ineficiente de medicamentos e insumos — resultando em desperdício por vencimento, desabastecimento e falta de rastreabilidade.

O **NeryFarma** é um sistema web desenvolvido para auxiliar profissionais de saúde no controle de estoque, com alertas automáticos, rastreamento de lotes por validade (FEFO) e relatórios gerenciais acessíveis.

Projeto desenvolvido como trabalho prático da disciplina de **Sistemas de Informação em Saúde**.

---

## ✨ Funcionalidades

- 🔐 Autenticação com JWT e controle de acesso por perfil (Farmacêutico, Enfermeiro, Administrador, Gestor)
- 📦 Cadastro de medicamentos e insumos com categorias
- 🧪 Gestão de lotes com rastreamento de validade e controle **FEFO** *(First Expired, First Out)*
- 📥 Registro de entradas, saídas e ajustes de estoque com histórico completo
- 🚨 Alertas automáticos: estoque baixo, vencimento próximo (7/15/30 dias) e lotes vencidos
- 📊 Dashboard com KPIs, gráficos de movimentação e itens críticos
- 📄 Relatórios de estoque atual, movimentações e vencimentos com exportação CSV

---

## 🛠️ Stack Tecnológica

| Camada | Tecnologia |
|--------|-----------|
| Backend | Java 21 + Spring Boot 3 |
| Frontend | React 18 + TypeScript |
| Banco de Dados | PostgreSQL 16 |
| ORM | Spring Data JPA + Hibernate |
| Autenticação | Spring Security + JWT |
| Migrations | Flyway |
| Estilização | Tailwind CSS + shadcn/ui |
| Estado Servidor | TanStack React Query |
| Formulários | React Hook Form + Zod |
| Gráficos | Recharts |
| Containerização | Docker + Docker Compose |

---

## ⚙️ Pré-requisitos

Antes de começar, garanta que você tem instalado:

- [Docker](https://www.docker.com/products/docker-desktop) e Docker Compose
- [Java 21 (JDK)](https://adoptium.net/) — recomendado Eclipse Temurin
- [Node.js 20+](https://nodejs.org/) e npm
- [IntelliJ IDEA](https://www.jetbrains.com/idea/) (backend) e [VS Code](https://code.visualstudio.com/) (frontend)

---

## 🚀 Como Rodar Localmente

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/neryfarma.git
cd neryfarma
```

### 2. Configure as variáveis de ambiente

```bash
cp .env.example .env
```

Edite o arquivo `.env` com os seus valores:

```env
# Banco de Dados
POSTGRES_DB=neryfarma
POSTGRES_USER=postgres
POSTGRES_PASSWORD=sua_senha_aqui

# pgAdmin
PGADMIN_EMAIL=admin@neryfarma.com
PGADMIN_PASSWORD=admin

# JWT (gere uma chave segura de pelo menos 256 bits)
JWT_SECRET=sua_chave_secreta_aqui
JWT_EXPIRATION_MS=86400000
```

### 3. Suba o banco de dados

```bash
docker-compose up -d postgres pgadmin
```

O PostgreSQL estará disponível em `localhost:5432`.
O pgAdmin estará disponível em [http://localhost:5050](http://localhost:5050).

### 4. Rode o backend

```bash
cd backend
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

A API estará disponível em [http://localhost:8080](http://localhost:8080).
A documentação Swagger estará em [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

### 5. Rode o frontend

```bash
cd frontend
npm install
npm run dev
```

A aplicação estará disponível em [http://localhost:5173](http://localhost:5173).

---

## 🐳 Rodando Tudo com Docker Compose

Para subir o ambiente completo (banco + backend + frontend) com um único comando:

```bash
docker-compose up --build
```

| Serviço | URL |
|---------|-----|
| Frontend | http://localhost:5173 |
| Backend (API) | http://localhost:8080 |
| Swagger UI | http://localhost:8080/swagger-ui.html |
| pgAdmin | http://localhost:5050 |

Para parar tudo:

```bash
docker-compose down
```

---

## 🧪 Rodando os Testes

**Backend:**

```bash
cd backend
./mvnw test
```

**Frontend:**

```bash
cd frontend
npm run test
```

---

## 🗺️ Roadmap

- [x] Setup do projeto e infraestrutura
- [x] Autenticação com JWT e controle de perfis
- [x] CRUD de medicamentos e categorias
- [x] Gestão de lotes e movimentações (FEFO)
- [x] Alertas automáticos
- [x] Dashboard com KPIs e gráficos
- [x] Relatórios com exportação CSV
- [ ] Suporte a múltiplas UBS (multi-tenant)
- [ ] Integração com leitor de código de barras
- [ ] App mobile (React Native)
- [ ] Previsão de demanda com Machine Learning

---

## 📐 Arquitetura

```
┌─────────────────────┐        ┌──────────────────────┐
│     React (SPA)     │◄──────►│  Spring Boot (REST)  │
│  Tailwind + shadcn  │  HTTP  │  Spring Security/JWT │
└─────────────────────┘        └──────────┬───────────┘
                                           │ JPA
                                ┌──────────▼───────────┐
                                │     PostgreSQL 16     │
                                │   (via Flyway)        │
                                └──────────────────────┘
```

O backend segue arquitetura em camadas: **Controller → Service → Repository → Entity**. O frontend consome a API REST com Axios, gerencia estado servidor com React Query e estado global com Zustand.

---

## 🤝 Contribuindo

Este é um projeto acadêmico individual, mas sugestões são bem-vindas. Siga o padrão de commits:

```
feat: adiciona tela de relatório de vencimentos
fix: corrige cálculo de estoque mínimo no alerta
docs: atualiza instruções de setup no README
chore: atualiza dependências do frontend
```

---

## 📜 Licença

Distribuído sob a licença MIT. Veja `LICENSE` para mais informações.

---

## 🙏 Referências

- [Documentação Spring Boot](https://spring.io/projects/spring-boot)
- [Documentação React](https://react.dev)
- [shadcn/ui](https://ui.shadcn.com)
- [Assistência Farmacêutica no SUS — Ministério da Saúde](https://www.gov.br/saude/pt-br/composicao/sctie/daf)
- Anna Nery (1814–1880) — pioneira da enfermagem brasileira na Guerra do Paraguai

---

<p align="center">Desenvolvido com ☕ e dedicação para melhorar a saúde pública brasileira</p>
