CREATE TABLE tipo_evento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_singular VARCHAR(50),
	nome_plural VARCHAR(50),
	descricao VARCHAR(50),
);

CREATE TABLE cat_participante (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(250)
);

INSERT INTO tipo_evento (nome_singular,nome_plural,descricao) values ('PALESTRA','PALESTRAS', '');
INSERT INTO tipo_evento (nome_singular,nome_plural,descricao) values ('CURSO','CURSOS', '');

INSERT INTO cat_participante (titulo) values ('ALUNO');
INSERT INTO cat_participante (titulo) values ('PROFESSOR');
INSERT INTO cat_participante (titulo) values ('ADVOGADO');
INSERT INTO cat_participante (titulo) values ('SERVIDOR');
INSERT INTO cat_participante (titulo) values ('OUTROS');