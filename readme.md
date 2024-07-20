# TrilhaBackEndJR-JUN15

## Descrição

Este é um projeto feito em Java com Sprigboot para a trilha  BackEnd da Codigo Certo Coders, oferecendo funcionalidades para registro e login de usuários, bem como operações CRUD (Criar, Ler, Atualizar, Excluir) para usuários e para tarefas. A aplicação não dispõem de interface gráfica, porém pode ser integrada a um front-end que utilize os endpoints fornecidos.

## Funcionalidades

1. **Registro e Login de Usuários**
   - **Registro de Usuário:** Permite que novos usuários se registrem no sistema.
   - **Login de Usuário:** Permite que usuários registrados façam login no sistema usando credenciais válidas.

2. **CRUD de Tarefas**
   - **Criar Tarefa:** Adiciona novas tarefas ao sistema.
   - **Ler Tarefas:** Recupera e lista todas as tarefas armazenadas.
   - **Atualizar Tarefa:** Atualiza informações de tarefas existentes.
   - **Excluir Tarefa:** Remove tarefas do sistema.

   **CRUD de Usuários**
   - É possível realizar as mesas funcionalidades das tarefas para usários.

## Tecnologias

- **Java:** Linguagem de programação base do projeto.
- **Spring Boot:** Framework principal para desenvolvimento do backend.
- **SQLite:** Banco de dados utilizado para persistência.
- **Railway Deploy:** Plataforma para hospedagem do projeto.
- **JWT (JSON Web Token):** Para autenticação e gerenciamento de sessões de usuário.

## Endpoints

- Utilize ferramenta de teste de API (recomendo postman)
- Insira a URL no campo da ferramenta escolhida: https://trilhabackendjr-jun15-production-edf6.up.railway.app
- cole a URL relativa ao método HTTP desejado (ex.: para o método de registro de usuário seria https://trilhabackendjr-jun15-production-edf6.up.railway.app/user/save)

### Usuários (registro e login)

- **Registro de Usuário**
  - **Método:** `POST`
  - **URL:** `/user/save`
  - **Corpo da Requisição:** 
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```

- **Login de Usuário**
  - **Método:** `POST`
  - **URL:** `/user/login`
  - **Corpo da Requisição:** 
    ```json
    {
      "username": "string",
      "password": "string"
    }
     
    ``` Resposta: JSON contendo um token JWT se as credenciais forem válidas.
  - **OBS.:** Para os próximos métodos, deve-se escolher o tipo de autentiação "bearer token" e inserir o token JWT gerado no login (o token expira 2 horas depois de gerado).

### Usuários (CRUD)
- **Ver todos os usuários**
  - **Método:** `GET`
  - **URL:** `/user`
  - **Resposta:** Lista de usuários em formato JSON

- **Localizar por nome / Localizar por ID**
  - **Método:** `GET`
  - **URLs:** `/user/name/{nome do usuário}` ou `/user/id/{id do usuário}`
  - **Respota:** usuário em formato JSON

- **Atualizar usuário**
  - **Método:** `PUT`
  - **URL:** `/user/update/{id do usuário}`
  - **Corpo da Requisição:** 
    ```json
    {
      "username": "string",
      "password": "string"
    }
    ```
- **Excluir usuário**
  - **Método:** `DELETE`
  - **URL:** `/user/delete/{id do usuário}`
    ```
- **Excluir todos os usuários**
  - **Método:** `DELETE`
  - **URL:** `/user/deleteAll`
    ```
### Tarefas (CRUD)

- **Criar Tarefa**
  - **Método:** `POST`
  - **URL:** `/task/save/{id do usuário}` (passar o id do usuário ao qual a tarefa será vinculada)
  - **Corpo da Requisição:** 
    ```json
    {
      "name": "string",
      "status": "pending / in_progress / complete" 
    }
    ``` (só é permitido um desses três)
- **Ver todas as Tarefas**
  - **Método:** `GET`
  - **URL:** `/task`
  - **Resposta:** Lista de tarefas em formato JSON.

- **Localizar por nome / Localizar por ID**
  - **Método:** `GET`
  - **URL:** `/task/name/{nome da tarefa}` ou `/task/id/{id da tarefa}`
  - **Respota:** tarefa em formato JSON

- **Ver todas as Tarefas por status / por usuário**
  - **Método:** `GET`
  - **URLs:** `/task/status/{um dos status permitidos}` ou `/task/user/{nome do usuário}`
  - **Resposta:** Lista de tarefas em formato JSON com o mesmo status ou pertencentes ao mesmo usuário.

- **Atualizar Tarefa**
  - **Método:** `PUT`
  - **URL:** `/task/{id}`
  - **Corpo da Requisição:** 
    ```json
    {
      "name": "string",
      "status": "string"
    }
    ```

- **Excluir Tarefa**
  - **Método:** `DELETE`
  - **URL:** `/task/delete/{id da tarefa}`
  
- **Excluir todas as Tarefa**
  - **Método:** `DELETE`
  - **URL:** `/task/deleteAll`

## Estrutura do Projeto

```plaintext
trilhaBackEndJR-JUN15/
├── .gitignore
├── mvnw
├── mvnw.cmd
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── candido/
│   │   │           └── trilhaBackEndJR_JUN15/
│   │   │               ├── TrilhaBackEndJrJun15Application.java
|   |   |               ├── ServletInitializer
│   │   │               ├── controller/
│   │   │               │   ├── TaskController.java
│   │   │               │   ├── UserController.java
│   │   │               │   └── UserLoginController.java
│   │   │               ├── entity/
│   │   │               │   ├── task/
│   │   │               │   │   ├── Task.java
│   │   │               │   │   └── Status.java
│   │   │               │   └── user/
│   │   │               │       ├── User.java
│   │   │               │       └── Role.java
|   |   |               ├── service/
│   │   │               │   ├── UserLoginService
│   │   │               └── repository/
│   │   │                   ├── TaskRepository.java
│   │   │                   └── UserRepository.java
│   │   └── resources/
│   │      ├── application.properties
│   │      └── database/
|   |          └── sqlite.db
│   └── test/
│       └── java/
│           └── com/
│               └── candido/
│                   └── trilhaBackEndJR_JUN15/
│                       ├── TaskControllerTest.java
│                       └── UserControllerTest.java
└── target/
    ├── classes/
    └── (arquivos compilados e gerados)
