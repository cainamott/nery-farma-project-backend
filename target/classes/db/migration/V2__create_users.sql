CREATE SCHEMA nf_owner

CREATE TABLE users (
 id                 UUID          NOT NULL ,
 name               varchar(100) NOT NULL,
 senha_hash         varchar(100) NOT NULL,
 email              varchar(60)  unique NOT NULL,
 num_registro       INTEGER,
 perfil             varchar(50)  NOT NULL,
 ativo              boolean       NOT NULL,
 CONSTRAINT pk_users PRIMARY KEY (id)
);

