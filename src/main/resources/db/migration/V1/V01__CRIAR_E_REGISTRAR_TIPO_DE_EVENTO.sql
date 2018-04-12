CREATE TABLE tipo_evento (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome_singular VARCHAR(50),
	nome_plural VARCHAR(50),
	descricao VARCHAR(50),
);


INSERT INTO tipo_evento (nome_singular,nome_plural,descricao) values ('PALESTRA','PALESTRAS', '');
INSERT INTO tipo_evento (nome_singular,nome_plural,descricao) values ('CURSO','CURSOS', '');
