# Clyvo API

## Descrição do Projeto

A Clyvo API é uma aplicação REST desenvolvida para gerenciamento de informações veterinárias, permitindo o cadastro e gerenciamento de animais, responsáveis, veterinários, doenças, vacinas, consultas e históricos clínicos.

O sistema foi construído utilizando arquitetura em camadas e segue princípios de desenvolvimento com Java e Spring Boot, oferecendo endpoints documentados via Swagger/OpenAPI e autenticação baseada em JWT.

---

## Objetivo

O projeto tem como objetivo centralizar o gerenciamento de dados relacionados ao ambiente veterinário, permitindo:

- Cadastro e gerenciamento de animais;
- Controle de responsáveis;
- Cadastro de veterinários;
- Registro de doenças e vacinas;
- Histórico clínico de animais;
- Agendamento e gerenciamento de consultas;
- Segurança de acesso utilizando autenticação JWT.

---

## Tecnologias Utilizadas

- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Oracle Database
- Swagger/OpenAPI
- JWT Authentication
- Maven

---

## Arquitetura

O projeto segue arquitetura em camadas:

### Controller Layer
Responsável por receber requisições HTTP e retornar respostas ao cliente.

### Service Layer
Responsável pelas regras de negócio e processamento das informações.

### Repository Layer
Responsável pela comunicação com o banco de dados através do Spring Data JPA.

### Model Layer
Contém as entidades utilizadas no sistema.

### Security Layer
Responsável pela autenticação, autorização e geração/validação de tokens JWT.

### Validation Layer
Responsável pela validação de dados enviados e tratamento centralizado de erros.

---

## Configuração

### Clonar repositório

```bash
git clone https://github.com/nfreitas2000/Challenge_Clyvo_JavaApplication.git

cd clyvo_java
```

### Executar projeto

```bash
mvn clean install

mvn spring-boot:run
```

---

## Banco de Dados

A aplicação está configurada para Oracle Database.

Exemplo:

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=update
```

---

# Autenticação

A API utiliza autenticação JWT.

Para utilizar os endpoints protegidos:

### 1 - Criar ou possuir um usuário SwaggerUser

Exemplo:

```sql
insert into T_CLYVO_SWAGGER_PESSOA
(nome,cpf,data_nascimento,email_pessoal)
values
('Pessoa 1','529.982.247-25',DATE '2002-05-05','pessoal1@gmail.com');

insert into T_CLYVO_SWAGGER_USER
(fk_pessoa,username,senha,permissao,data_criacao)
values
(1, 'RM1', '$2a$10$0BhZzH8hG2FEW6qhs9UpV.XlaRPf.ODWDDevV67y3TzXeDzUUaRTK', 'USER', DATE '2026-05-05');
```

Senha correspondente:

```text
1234
```

---

### 2 - Realizar login

Endpoint:

```http
POST /autenticacao/login
```

Exemplo:

```http
localhost:8080/autenticacao/login?username=RM1&password=1234&duracao=10
```

A resposta retornará um token JWT.

---

### 3 - Inserir token no Swagger

Após receber o token:

1. Abrir Swagger:

```http
http://localhost:8080/swagger-ui/index.html
```

2. Clicar em:

```text
Authorize
```

(localizado no topo da página)

3. Inserir:

```text
Bearer seu_token
```

4. Confirmar.

Todos os endpoints protegidos estarão liberados para uso.

---

# Endpoints

## Veterinários

### GET

```http
GET /veterinarios/todos
```

Retorna todos os veterinários.

```http
GET /veterinarios/{id}
```

Retorna veterinário por ID.

```http
GET /veterinarios/buscarNome?substring=
```

Busca veterinários pelo nome.

```http
GET /veterinarios/paginado
```

Retorna veterinários utilizando DTO e paginação.

### POST

```http
POST /veterinarios/inserir
```

Body:

```json
{
  "nm_veterinario":"Gabriel",
  "cpf":"11111111111",
  "dt_nascimento":"2000-01-01",
  "email":"gabriel@gmail.com",
  "num_celular":"+55 (11) 11111-1111",
  "especialidade":"Cardiologia"
}
```

### PUT

```http
PUT /veterinarios/{id}
```

Atualiza veterinário.

### DELETE

```http
DELETE /veterinarios/{id}
```

Remove veterinário.

---

## Responsáveis

```http
GET /responsaveis/todos
GET /responsaveis/{id}
GET /responsaveis/buscarNome
GET /responsaveis/paginado

POST /responsaveis/inserir
PUT /responsaveis/{id}
DELETE /responsaveis/{id}
```

---

## Animais

```http
GET /animais/todos
GET /animais/{id}
GET /animais/buscarNome
GET /animais/paginado

POST /animais/inserir
PUT /animais/{id}
DELETE /animais/{id}
```

---

## Vacinas

```http
GET /vacinas/todos
GET /vacinas/{id}

POST /vacinas/inserir
PUT /vacinas/{id}
DELETE /vacinas/{id}
```

---

## VacinaAnimal

```http
GET /vacinaAnimal/todos
GET /vacinaAnimal/{id}

POST /vacinaAnimal/inserir
PUT /vacinaAnimal/{id}
DELETE /vacinaAnimal/{id}
```

---

## Doenças

```http
GET /doencas/todos
GET /doencas/{id}

POST /doencas/inserir
PUT /doencas/{id}
DELETE /doencas/{id}
```

---

## DoençaAnimal

```http
GET /doencaAnimal/todos
GET /doencaAnimal/{id}

POST /doencaAnimal/inserir
PUT /doencaAnimal/{id}
DELETE /doencaAnimal/{id}
```

---

## Consultas

```http
GET /consultas/todos
GET /consultas/{id}

POST /consultas/inserir
PUT /consultas/{id}
DELETE /consultas/{id}
```

---

## Histórico

```http
GET /historicos/todos
GET /historicos/{id}

POST /historicos/inserir
PUT /historicos/{id}
DELETE /historicos/{id}
```

---

## Integrantes

- RM562979 - Caio Kenzo Tayra – 2TDSPI
- RM563000 - Enzo Vieira Bernardini - 2TDSPI
- RM564992 - Natan Freitas de Moraes – 2TDSPI
- RM561857 - Nicolas Mota Cândido - 2TDSPI