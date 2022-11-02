drop table Usuario cascade constraints;
drop table Bicicleta cascade constraints;
drop table Ponto cascade constraints;
drop table Aluguel cascade constraints;

create table Usuario(
idUsuario numeric(6) generated as identity(start with 1 increment by 1),
login varchar2(30),
nome varchar2(60),
email varchar2(70),
senha varchar2(30)
);

alter table Usuario add constraint Usuario_PK primary key (idUsuario);

create table Ponto(
idPonto numeric(6) generated as identity(start with 1 increment by 1),
endereco varchar2(100)
);

alter table Ponto add constraint Ponto_PK primary key (idPonto);
alter table Ponto add idUsuario numeric(6) references Usuario;

create table Bicicleta(
idBicicleta numeric(6) generated as identity(start with 1 increment by 1),
serial varchar2(100),
tamanho varchar2(6)
);

alter table Bicicleta add constraint Bicicleta_PK primary key (idBicicleta);

create table Aluguel(
tempoDeUso numeric(3)
);

alter table Aluguel add idUsuario numeric(6) references Usuario;
alter table Aluguel add idBicicleta numeric(6) references Bicicleta;

