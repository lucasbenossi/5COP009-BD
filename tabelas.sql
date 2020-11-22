CREATE SCHEMA prjbd;
CREATE TABLE prjbd.loja (
    id SERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(30) UNIQUE NOT NULL,
    url VARCHAR(100) NOT NULL
);
CREATE TABLE prjbd.produto (
    id SERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(500) NOT NULL,
    nomeTratado VARCHAR(500) NOT NULL,
    preco numeric NOT NULL,
    parcelas int NOT NULL,
    valorParcela NUMERIC NOT NULL,
    idLoja SERIAL NOT NULL,
    url VARCHAR(500) NOT NULL,
    CONSTRAINT produto_fk_loja FOREIGN KEY (idLoja) REFERENCES prjbd.loja(id)
);
CREATE TABLE prjbd.cpu (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    cores INT NOT NULL,
    threads INT NOT NULL,
    frequency INT NOT NULL,
    maxFrequency INT NOT NULL,
    scoreSingleCore INT NOT NULL,
    scoreMultiCore INT NOT NULL,
    url VARCHAR(100) NOT NULL
);
CREATE TABLE prjbd.gpu (
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL UNIQUE,
    g3dMark INT NOT NULL,
    g2dMark INT NOT NULL
);