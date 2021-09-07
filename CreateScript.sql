DROP TABLE IF EXISTS status CASCADE;
DROP TABLE IF EXISTS fakultet CASCADE;
DROP TABLE IF EXISTS departman CASCADE;
DROP TABLE IF EXISTS student CASCADE;

DROP SEQUENCE IF EXISTS status_seq;
DROP SEQUENCE IF EXISTS fakultet_seq;
DROP SEQUENCE IF EXISTS departman_seq;
DROP SEQUENCE IF EXISTS student_seq;



CREATE TABLE fakultet
(
	id int not null,
	naziv varchar(100) not null,
	sediste varchar(100) not null,
	CONSTRAINT PK_fakultet PRIMARY KEY (id)
);


CREATE TABLE departman
(	
	id int not null,
	naziv varchar(100) not null,
	oznaka varchar(10) not null,
	fakultet int not null,
	CONSTRAINT PK_departman PRIMARY KEY (id),
	CONSTRAINT FK_departman_fakultet FOREIGN KEY (fakultet)
		REFERENCES fakultet (id)
);


CREATE TABLE status
(
	id int not null,
	naziv varchar (100) not null,
	oznaka varchar(10) not null,
	CONSTRAINT PK_status PRIMARY KEY (id)
);	

CREATE TABLE student
(
	id int not null,
	ime varchar(50) not null,
	prezime varchar (50) not null,
	broj_indeksa varchar(20) not null,
	status int not null,
	departman int not null,
	CONSTRAINT PK_student PRIMARY KEY (id),
	CONSTRAINT FK_student_status FOREIGN KEY (status)
		REFERENCES status (id),
	CONSTRAINT FK_student_departman FOREIGN KEY (departman)
		REFERENCES departman (id)
);

CREATE INDEX IDXFK_departman_fakultet
ON departman (fakultet);
CREATE INDEX IDXFK_student_departman
ON student (departman);
CREATE INDEX IDXFK_student_status
ON student (status);

CREATE SEQUENCE fakultet_seq
INCREMENT 1;
CREATE SEQUENCE status_seq
INCREMENT 1;
CREATE SEQUENCE departman_seq
INCREMENT 1;
CREATE SEQUENCE student_seq
INCREMENT 1;


