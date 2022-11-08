drop table Usuario cascade constraints;
drop table Bicicleta cascade constraints;
drop table Ponto cascade constraints;
drop table Aluguel cascade constraints;

create table Usuario(
idUsuario numeric(6) generated as identity(start with 1 increment by 1),
login varchar2(30) not null unique,
nome varchar2(60) not null,
email varchar2(70) not null unique,
senha varchar2(30) not null
);

alter table Usuario add constraint Usuario_PK primary key (idUsuario);

create table Ponto(
idPonto numeric(6) generated as identity(start with 1 increment by 1),
endereco varchar2(100) not null
);

alter table Ponto add constraint Ponto_PK primary key (idPonto);
alter table Ponto add idUsuario numeric(6) references Usuario;

create table Bicicleta(
idBicicleta numeric(6) generated as identity(start with 1 increment by 1),
serial varchar2(50) not null,
tamanho varchar2(6) not null,
disponibilidade varchar2(13) not null
);

alter table Bicicleta add constraint Bicicleta_PK primary key (idBicicleta);

create table Aluguel(
idAluguel numeric(6) generated as identity(start with 1 increment by 1),
tempoDeUso numeric(4) not null
);

alter table Aluguel add constraint Aluguel_PK primary key (idAluguel);
alter table Aluguel add idUsuario numeric(6) references Usuario;
alter table Aluguel add idBicicleta numeric(6) references Bicicleta;
