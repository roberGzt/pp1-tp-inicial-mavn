DROP database if exists tpi_g2;

create database tpi_g2
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

use tpi_g2;



drop table if exists personas;
drop table if exists tiposDeContacto; 
drop table if exists domicilios;
drop table if exists localidades;

CREATE TABLE if not exists tiposDeContacto
(
	idTipo int not null auto_increment,
	nombreTipo varchar(30) not null unique,
    primary key (idTipo)
);

CREATE TABLE if not exists localidades
(
	idLocalidad int not null auto_increment,
    nombreLocalidad varchar(45) not null,
    primary key (idLocalidad)
);

CREATE TABLE if not exists domicilios
(
	idDomicilio int not null auto_increment,
	calle varchar (45) not null,
	altura int not null,
	piso int not null,
	departamento varchar(15) not null,
	idLocalidad int not null,
    primary key (idDomicilio),
    foreign key (idLocalidad) references localidades (idLocalidad)
);

CREATE TABLE if not exists personas 
(
  idPersona int(11) NOT NULL AUTO_INCREMENT,
  Nombre varchar(45) NOT NULL,
  Telefono varchar(20) NOT NULL,
  Mail varchar(45) NOT NULL,
  Cumpleanios date NOT NULL,
  idTipo int NOT NULL,
  idDomicilio int not null,
  PRIMARY KEY (idPersona),
  FOREIGN KEY (idTipo) references tiposDeContacto (idTipo),
  FOREIGN KEY (idDomicilio) references domicilios (idDomicilio)
);



insert into tiposDeContacto (nombreTipo) values ('Familia');
insert into tiposDeContacto (nombreTipo) values ('Amigos');
insert into tiposDeContacto (nombreTipo) values ('Trabajo');
insert into tiposDeContacto (nombreTipo) values ('Universidad');
insert into tiposDeContacto (nombreTipo) values ('Club');


insert into localidades (nombreLocalidad) values ('San miguel');
insert into localidades (nombreLocalidad) values ('Tortuguitas');
insert into localidades (nombreLocalidad) values ('Pablo Nogues');
insert into localidades (nombreLocalidad) values ('Polvorines');
insert into localidades (nombreLocalidad) values ('Don Tortcuato');