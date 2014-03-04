create database questionario;
use questionario;

create table avaliacao (
        cod_avaliacao integer not null auto_increment,
        primary key (cod_avaliacao)
);

create table comentario (
        cod_comentario integer not null auto_increment,
        comentario TEXT,
        cod_avaliacao integer,
        cod_usuario integer,
        primary key (cod_comentario)
);

create table curso (
        cod_curso integer not null auto_increment,
        nome varchar(255),
        primary key (cod_curso)
);

create table login (
        cod_login integer not null auto_increment,
        login varchar(255),
        perfil enum('P,A,S'),
        senha varchar(255),
        cod_usuario integer,
        primary key (cod_login)
);

create table questao (
        cod_questao integer not null auto_increment,
        descricao varchar(255),
        opcao varchar(255),
        primary key (cod_questao)
);

create table resposta (
        cod_resposta integer not null auto_increment,
        cod_avaliacao integer,
        cod_questao integer,
        primary key (cod_resposta)
);

create table usuario (
        cod_usuario integer not null auto_increment,
        cpf varchar(255),
        nome varchar(255),
        primary key (cod_usuario)
);

alter table comentario 
        add index FK9E0DE7E1A38C6652 (cod_avaliacao), 
        add constraint FK9E0DE7E1A38C6652 
        foreign key (cod_avaliacao) 
        references avaliacao (cod_avaliacao);

alter table comentario 
        add index FK9E0DE7E156F9737C (cod_usuario), 
        add constraint FK9E0DE7E156F9737C 
        foreign key (cod_usuario) 
        references usuario (cod_usuario);

alter table login 
        add index FK625EF6956F9737C (cod_usuario), 
        add constraint FK625EF6956F9737C 
        foreign key (cod_usuario) 
		references usuario (cod_usuario);

alter table resposta 
        add index FKEBB72721A38C6652 (cod_avaliacao), 
        add constraint FKEBB72721A38C6652 
        foreign key (cod_avaliacao) 
        references avaliacao (cod_avaliacao);

alter table resposta 
        add index FKEBB72721B4E87640 (cod_questao), 
        add constraint FKEBB72721B4E87640 
        foreign key (cod_questao) 
        references questao (cod_questao);
        
insert into usuario values(null,'116.365.407-83','Jean Fonseca');
insert into login values(null,'jean','P',md5('jean'),1);