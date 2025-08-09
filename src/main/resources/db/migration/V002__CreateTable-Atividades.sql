CREATE TABLE IF NOT EXISTS atividades (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_aluno BIGINT NOT NULL,
    descricao TEXT NOT NULL,
    data_inicio DATETIME NOT NULL ,
    data_fim DATETIME,
    status varchar(20) NOT NULL ,
    FOREIGN KEY (id_aluno) REFERENCES alunos(id) ON DELETE CASCADE ON UPDATE CASCADE
);
