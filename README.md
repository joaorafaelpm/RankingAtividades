# 📘 Ranking de Atividades Extra Curriculares
Segue a documentação da API :

 Documentação da API - Ranking de Atividades Extracurriculares
📌 Visão Geral

A ideia desta api é extremamente parecida com o meu outro projeto da AlgaWorks, porém, neste caso, eu me desafiei a fazer sozinho. Fiz com o conceito de hierarquia aplicado e usei outro modelo de classes nos meus DTOs (aplicado a partir do 3º commit).

A aplicação permite:

Cadastro e gerenciamento de alunos.

Registro, consulta e controle de atividades extracurriculares.

Encerramento e reabertura de atividades.

Organização de dados para possíveis fins estatísticos e competitivos (ranking).

📁 Endpoints
📍 /alunos
✅ GET /alunos
Retorna a lista de todos os alunos cadastrados.

✅ GET /alunos/{id}
Retorna os dados de um aluno específico pelo seu ID.

✅ POST /alunos
Cria um novo aluno.

Body (JSON):

json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "dataNascimento": "2005-08-15T00:00:00Z",
  "curso": "Informática",
  "classe": "3B"
}
✅ PUT /alunos/{id}
Atualiza os dados de um aluno existente.

Body (JSON):

json
{
  "name": "João Silva",
  "email": "joao@email.com",
  "dataNascimento": "2005-08-15T00:00:00Z",
  "curso": "Informática",
  "classe": "3B"
}
❌ DELETE /alunos/{id}
Remove um aluno e suas respectivas atividades do sistema.

📍 /atividades
✅ GET /atividades
Retorna todas as atividades cadastradas.

✅ GET /atividades/{id}
Retorna os dados de uma atividade específica pelo seu ID.

✅ POST /atividades
Cria uma nova atividade vinculada a um aluno.

Body (JSON):

json
{
  "alunoId": {
    "id": 1
  },
  "descricao": "Participação na feira de ciência"
}
✅ PUT /atividades/{id}
Atualiza a descrição de uma atividade.

Body (JSON):

json
{
  "descricao": "Nova descrição da atividade"
}
❌ DELETE /atividades/{id}
Remove uma atividade específica.

🔁 Funcionalidades Especiais (não CRUD)
✅ PUT /atividades/{id}/finalizada
Marca a atividade como finalizada, definindo a data de término e atualizando seu status.

❌ DELETE /atividades/{id}/finalizada
Desfaz o encerramento de uma atividade, alterando seu status para pendente e removendo a data de término.

🧱 Considerações Técnicas
A API utiliza JPA com Hibernate e relacionamento @OneToMany entre Aluno e Atividade.

Utiliza o padrão DTO e Modelos representativos + Assembler para separação das camadas.

Todos os mapeamentos entre DTOs e entidades são realizados com MapStruct, oferecendo alta performance.

Datas são manipuladas com OffsetDateTime, permitindo melhor suporte a fusos horários.
