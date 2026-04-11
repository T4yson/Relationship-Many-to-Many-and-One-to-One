# Relationship: Many-to-Many & One-to-One

> API REST em Spring Boot demonstrando na prática o mapeamento de relacionamentos JPA: **Many-to-Many** (Funcionário ↔ Projeto) e **One-to-One** (Funcionário ↔ Assento).

---

## 📋 Sobre o Projeto

Este projeto tem como objetivo central explorar e demonstrar dois dos principais tipos de relacionamento entre entidades no JPA/Hibernate:

| Relacionamento | Entidades envolvidas | Descrição |
|---|---|---|
| `@OneToOne` | `Employee` ↔ `Seat` | Cada funcionário possui exatamente um assento exclusivo |
| `@ManyToMany` | `Employee` ↔ `Project` | Um funcionário pode participar de vários projetos; um projeto pode ter vários funcionários |

O projeto é construído como uma **API RESTful** completa, com camadas bem definidas e boas práticas de desenvolvimento.

---

## 🗂️ Modelo de Domínio

```
┌──────────────┐        ┌──────────────┐
│   Employee   │ 1────1 │     Seat     │
│  ----------  │        │  ----------  │
│  id (PK)     │        │  id (PK)     │
│  name        │        │  cod         │
└──────┬───────┘        └──────────────┘
       │
       │  N
       │
  ─────┴──────
  join table
  ─────┬──────
       │  N
       │
┌──────┴───────┐
│   Project    │
│  ----------  │
│  id (PK)     │
│  name        │
└──────────────┘
```

- **Employee** é o **dono** dos dois relacionamentos (lado que controla a chave estrangeira / tabela de junção).
- **Seat** utiliza `mappedBy = "assento"` no lado inverso — o `CascadeType.ALL` em `Employee` garante que operações em cascata (persistência, remoção) se propaguem automaticamente ao assento vinculado.
- **Project** utiliza `mappedBy = "projects"` no lado inverso da relação Many-to-Many, e a tabela de junção é gerenciada pelo JPA.

---

## 🛠️ Stack Tecnológica

| Tecnologia | Versão | Papel |
|---|---|---|
| **Java** | 21 | Linguagem principal |
| **Spring Boot** | 4.0.5 | Framework base |
| **Spring Web MVC** | — | Camada HTTP / Controllers REST |
| **Spring Data JPA** | — | Abstração de persistência |
| **Hibernate** | (via JPA) | Implementação ORM |
| **H2 Database** | — | Banco de dados em memória |
| **Lombok** | — | Redução de boilerplate (getters, setters, construtores) |
| **Bean Validation** | — | Validação de entradas via anotações |
| **Maven** | 3.x | Gerenciador de dependências e build |

---

## 🏗️ Estrutura do Projeto

```
AnaliseEstrutura/
└── src/
    └── main/
        ├── java/com/example/AnaliseEstrutura/
        │   ├── controller/          # Camada de entrada HTTP (REST)
        │   │   ├── EmployeeController.java
        │   │   ├── ProjectController.java
        │   │   └── SeatController.java
        │   ├── service/             # Regras de negócio
        │   │   ├── EmployeeService.java
        │   │   ├── ProjectService.java
        │   │   └── SeatService.java
        │   ├── repository/          # Acesso a dados (Spring Data JPA)
        │   │   ├── EmployeeRepository.java
        │   │   ├── ProjectRepository.java
        │   │   └── SeatRepository.java
        │   ├── model/               # Entidades JPA
        │   │   ├── Employee.java
        │   │   ├── Project.java
        │   │   └── Seat.java
        │   ├── dto/                 # Objetos de transferência (Records Java)
        │   │   ├── employee/
        │   │   ├── project/
        │   │   └── seat/
        │   └── mapper/              # Conversão Entity ↔ DTO
        │       ├── EmployeeMapper.java
        │       ├── ProjectMapper.java
        │       └── SeatMapper.java
        └── resources/
            └── application.properties
```

---

## 🚀 Como Executar

### Pré-requisitos

- **Java 21+**
- **Maven 3.8+** (ou use o wrapper incluído `./mvnw`)

### Passos

```bash
# Clone o repositório
git clone https://github.com/T4yson/Relationship-Many-to-Many-and-One-to-One.git
cd Relationship-Many-to-Many-and-One-to-One/AnaliseEstrutura

# Execute a aplicação
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

O console do H2 pode ser acessado em `http://localhost:8080/h2-console` (banco em memória, configurado automaticamente pelo Spring Boot).

---

## 📡 Endpoints da API

### 👤 Employees — `/api/employees`

| Método | Rota | Descrição | Status |
|--------|------|-----------|--------|
| `POST` | `/api/employees` | Cria um novo funcionário | `201 Created` |
| `GET` | `/api/employees` | Lista todos os funcionários | `200 OK` |
| `GET` | `/api/employees/{id}/seat` | Retorna o funcionário com seu assento vinculado | `200 OK` |
| `GET` | `/api/employees/multi-projects` | Lista funcionários em **mais de um projeto** | `200 OK` |
| `DELETE` | `/api/employees/{id}` | Remove um funcionário | `204 No Content` |

### 📁 Projects — `/api/projects`

| Método | Rota | Descrição | Status |
|--------|------|-----------|--------|
| `POST` | `/api/projects` | Cria um novo projeto | `201 Created` |
| `GET` | `/api/projects` | Lista todos os projetos | `200 OK` |
| `GET` | `/api/projects/{id}` | Busca projeto por ID | `200 OK` |
| `PUT` | `/api/projects/{id}` | Atualiza um projeto | `200 OK` |
| `DELETE` | `/api/projects/{id}` | Remove um projeto | `204 No Content` |

### 🪑 Seats — `/api/seats`

| Método | Rota | Descrição | Status |
|--------|------|-----------|--------|
| `POST` | `/api/seats` | Cria um novo assento | `201 Created` |
| `GET` | `/api/seats` | Lista todos os assentos | `200 OK` |
| `GET` | `/api/seats/{id}` | Busca assento por ID | `200 OK` |
| `PUT` | `/api/seats/{id}` | Atualiza um assento | `200 OK` |
| `DELETE` | `/api/seats/{id}` | Remove um assento | `204 No Content` |

---

## 🎯 Principais Decisões de Arquitetura

### 1. Separação por camadas (Layered Architecture)
O projeto segue a separação clássica em **Controller → Service → Repository → Model**, garantindo baixo acoplamento e facilitando manutenção e testes.

### 2. Padrão DTO com Java Records
Todos os dados trafegados pela API passam por **DTOs imutáveis** (declarados como `record`), protegendo as entidades JPA de exposição direta e evitando problemas como referências circulares na serialização JSON.

### 3. Mapper manual como componente Spring
A conversão entre entidades e DTOs é feita por classes `@Component` dedicadas (`EmployeeMapper`, `ProjectMapper`, `SeatMapper`), mantendo a lógica de transformação centralizada, testável e sem dependência de frameworks externos de mapeamento.

### 4. Propriedade do relacionamento (owning side)
- No `@OneToOne`: `Employee` é o dono, com `CascadeType.ALL` propagando operações para `Seat`.
- No `@ManyToMany`: `Employee` é o dono (controla a tabela de junção); `Project` declara `mappedBy`.

### 5. Consulta JPQL customizada
`EmployeeRepository` define uma query JPQL personalizada para buscar funcionários alocados em **mais de um projeto**:
```java
@Query("SELECT e FROM Employee e WHERE size(e.projects) > 1")
List<Employee> findEmployeesWithMultipleProjects();
```

### 6. Banco H2 em memória
O banco de dados H2 elimina a necessidade de qualquer infraestrutura externa para rodar o projeto, tornando-o ideal para fins educacionais e demonstrações de relacionamentos JPA.

### 7. Lombok
Anotações como `@Getter`, `@Setter`, `@NoArgsConstructor` e `@AllArgsConstructor` eliminam código boilerplate nas entidades, mantendo os modelos concisos e legíveis.

---

## 👨‍💻 Autor

<table>
  <tr>
    <td align="center">
      <a href="https://github.com/T4yson">
        <img src="https://github.com/T4yson.png" width="100px;" alt="T4yson"/><br />
        <sub><b>T4yson</b></sub>
      </a>
    </td>
  </tr>
</table>

[![GitHub](https://img.shields.io/badge/GitHub-T4yson-181717?style=flat&logo=github)](https://github.com/T4yson)
