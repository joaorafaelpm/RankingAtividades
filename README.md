
# 📘 Documentação da API - Ranking de Atividades Extracurriculares

## 📌 Visão Geral

Este projeto é uma **API RESTful** desenvolvida para gerenciar e ranquear as **atividades extracurriculares** de alunos em uma instituição de ensino. 

A aplicação permite:
- Cadastro e gerenciamento de **alunos**.
- Registro, consulta e controle de **atividades extracurriculares**.
- Encerramento e reabertura de atividades.
- Organização de dados para possíveis fins estatísticos e competitivos (ranking).

---

## 📁 Endpoints

### 📍 `/alunos`

#### ✅ GET `/alunos`
Retorna a lista de todos os alunos cadastrados.

#### ✅ GET `/alunos/{id}`
Retorna os dados de um aluno específico pelo seu ID.

#### ✅ POST `/alunos`
Cria um novo aluno.

**Body (JSON):**
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "dataNascimento": "2005-08-15T00:00:00Z",
  "curso": "Informática",
  "classe": "3B"
}
```
```

#### 🧾 Tabela de Campos

| Campo              | Tipo                     | Obrigatório | Descrição                    |
|--------------------|--------------------------|-------------|------------------------------|
| `name`             | `String`                 | Sim         | Nome do aluno.               |
| `email`            | `String`                 | Sim         | Email do aluno.              |
| `dataNascimento`   | `(YYYY-MM-DDTHH:MM:SSZ)` | Sim         | Data de nascimento do aluno. |
| `curso`            | `String`                 | Sim         | Curso do aluno.              |
| `classe`           | `String`                 | Sim         | Classe do aluno.             |

---
````


#### ✅ PUT `/alunos/{id}`
Atualiza os dados de um aluno existente.

**Body (JSON):**
```json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "dataNascimento": "2005-08-15T00:00:00Z",
  "curso": "Informática",
  "classe": "3B"
}
```
```

#### 🧾 Tabela de Campos

| Campo              | Tipo                     | Obrigatório | Descrição                    |
|--------------------|--------------------------|-------------|------------------------------|
| `name`             | `String`                 | Sim         | Nome do aluno.               |
| `email`            | `String`                 | Sim         | Email do aluno.              |
| `dataNascimento`   | `(YYYY-MM-DDTHH:MM:SSZ)` | Sim         | Data de nascimento do aluno. |
| `curso`            | `String`                 | Sim         | Curso do aluno.              |
| `classe`           | `String`                 | Sim         | Classe do aluno.             |
---
````
#### ❌ DELETE `/alunos/{id}`
Remove um aluno e suas respectivas atividades do sistema.

---

### 📍 `/atividades`

#### ✅ GET `/atividades`
Retorna todas as atividades cadastradas.

#### ✅ GET `/atividades/{id}`
Retorna os dados de uma atividade específica pelo seu ID.

#### ✅ POST `/atividades`
Cria uma nova atividade vinculada a um aluno.

**Body (JSON):**
```json
{
  "alunoId": {
    "id": 1
  },
  "descricao": "Participação na feira de ciência"
}
```
```

#### 🧾 Tabela de Campos

| Campo      | Tipo     | Obrigatório | Descrição                              |
|------------|----------|-------------|----------------------------------------|
| `alunoId`  | `Object` | Sim         | Objeto contendo o ID do aluno.         |
| `id`       | `Long`   | Sim         | ID do aluno a quem a atividade pertence.|
| `descricao`| `String` | Sim         | Descrição da atividade realizada.      |

---
````
#### ✅ PUT `/atividades/{id}`
Atualiza a descrição de uma atividade.

**Body (JSON):**
```json
{
  "descricao": "Nova descrição da atividade"
}
```

```

#### 🧾 Tabela de Campos

| Campo      | Tipo     | Obrigatório | Descrição                              |
|------------|----------|-------------|----------------------------------------|
| `descricao`| `String` | Sim         | Descrição da atividade realizada.      |

---
````

#### ❌ DELETE `/atividades/{id}`
Remove uma atividade específica.

---

## 🔁 Funcionalidades Especiais (não CRUD)

### ✅ PUT `/atividades/{id}/finalizada`
Marca a atividade como **finalizada**, definindo a data de término e atualizando seu status.

### ❌ DELETE `/atividades/{id}/finalizada`
**Desfaz o encerramento** de uma atividade, alterando seu status para **pendente** e removendo a data de término.

---

## 🧱 Considerações Técnicas

- A API utiliza **JPA com Hibernate** e relacionamento `@OneToMany` entre `Aluno` e `Atividade`.
- Utiliza o padrão DTO + Assembler para separação das camadas.
- Todos os mapeamentos entre DTOs e entidades são realizados com **MapStruct**, oferecendo alta performance.
- Datas são manipuladas com **OffsetDateTime**, permitindo melhor suporte a fusos horários.

---
