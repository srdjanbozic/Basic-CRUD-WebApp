
DROP SEQUENCE IF EXISTS status_seq;
DROP SEQUENCE IF EXISTS fakultet_seq;
DROP SEQUENCE IF EXISTS departman_seq;
DROP SEQUENCE IF EXISTS student_seq;

CREATE SEQUENCE fakultet_seq
INCREMENT 1;
CREATE SEQUENCE status_seq
INCREMENT 1;
CREATE SEQUENCE departman_seq
INCREMENT 1;
CREATE SEQUENCE student_seq
INCREMENT 1;

INSERT INTO "fakultet"("id","naziv","sediste")
VALUES (nextval('fakultet_seq'),'Fakultet tehnickih nauka','Novi Sad');
INSERT INTO "fakultet"("id","naziv","sediste")
VALUES (nextval('fakultet_seq'),'Prirodno matematicki fakultet','Novi Sad');
INSERT INTO "fakultet"("id","naziv","sediste")
VALUES (nextval('fakultet_seq'),'Poljoprivredni fakultet','Novi Sad');
INSERT INTO "fakultet"("id","naziv","sediste")
VALUES (nextval('fakultet_seq'),'Filozofski fakultet','Novi Sad');
INSERT INTO "fakultet"("id","naziv","sediste")
VALUES (-100,'test upadte','Novi Sad');

--departman
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (nextval('departman_seq'),'Departman za industrijsko inzenjerstvo i menadzment','IIM',1);
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (nextval('departman_seq'),'Departman za graficko inzenjerstvo i dizajn','GRID',1);
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (nextval('departman_seq'),'Departman za analiticku hemiju','anhem',2);
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (nextval('departman_seq'),'Departman za opstu i neorgansku hemiju hemiju','ophem',2);
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (nextval('departman_seq'),'Departman za stocarstvo','stoc',3);
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (nextval('departman_seq'),'Departman za veterinarsku medicinu','vetmed',3);
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (nextval('departman_seq'),'Odsek za filozofiju','filo',4);
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (nextval('departman_seq'),'Odsek za pedagogiju','pedag',4);
INSERT INTO "departman"("id","naziv","oznaka","fakultet")
VALUES (-100,'test update','IIM',1);

--status
INSERT INTO "status"("id","naziv","oznaka")
VALUES (nextval('status_seq'),'budzet','B');
INSERT INTO "status"("id","naziv","oznaka")
VALUES (nextval('status_seq'),'samofinansiranje','S');
INSERT INTO "status"("id","naziv","oznaka")
VALUES (-100,'test update','S');



--student
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (nextval('student_seq'),'Srdjan','Bozic','IT72/2017',1,1);
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (nextval('student_seq'),'Djordje','Djordjevic','GR6/2017',1,2);
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (nextval('student_seq'),'Nikola','Nikolic','AH87/2017',2,3);
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (nextval('student_seq'),'Ana','Jovanovic','ONH43/2017',2,4);
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (nextval('student_seq'),'Nina','Comic','S77/2017',1,5);
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (nextval('student_seq'),'Jelena','Marinkovic','VM97/2017',2,6);
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (nextval('student_seq'),'Jovan','Ilic','FI64/2017',2,7);
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (nextval('student_seq'),'Petar','Petrovic','PE13/2017',1,8);
INSERT INTO "student"("id","ime","prezime","broj_indeksa","status","departman")
VALUES (-100,'test update','test update','IT7/2017',1,1);




