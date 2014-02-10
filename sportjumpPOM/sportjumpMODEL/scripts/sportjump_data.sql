INSERT INTO tb_user (ID_USER,ADDRESS,COMMENTS,DATE_BIRTH,DNI,EMAIL,NAME,SURNAME,TELEPHONE,TYPE,USER_NAME) VALUES (1,'','',{ts '1981-12-11 00:00:00.'},'31722664y','juanminet@gmail.com','Juan Miguel','Ro','','','jumanji');
INSERT INTO tb_user (ID_USER,ADDRESS,COMMENTS,DATE_BIRTH,DNI,EMAIL,NAME,SURNAME,TELEPHONE,TYPE,USER_NAME) VALUES (2,'Av Plutarco 69 3ºD','',{ts '1987-11-23 00:00:00.'},'22222222y','antonio@antonio.com','Antonio ','Lopez de Araujo','657879809','fondista','Antonio');
INSERT INTO tb_user (ID_USER,ADDRESS,COMMENTS,DATE_BIRTH,DNI,EMAIL,NAME,SURNAME,TELEPHONE,TYPE,USER_NAME) VALUES (3,'Caller Reina 4 Madrid','',{ts '1981-04-09 00:00:00.'},'33221122O','soniar@soniar','Sonia','Hernandez Fernandes','987876765','Saltadores','Soniar');
INSERT INTO tb_user (ID_USER,ADDRESS,COMMENTS,DATE_BIRTH,DNI,EMAIL,NAME,SURNAME,TELEPHONE,TYPE,USER_NAME) VALUES (4,'Calle el Jeranio 2ª puerta detrás del rosal','',{ts '1989-12-11 00:00:00.'},'48787483P','jacinto@jacinto','Jacinto','De las Jaces','980908732','corredores','Jacinto');
INSERT INTO tb_user (ID_USER,ADDRESS,COMMENTS,DATE_BIRTH,DNI,EMAIL,NAME,SURNAME,TELEPHONE,TYPE,USER_NAME) VALUES (5,'Palacio real 7º Piso, Minas Tirith','',{ts '1009-03-01 00:00:00.'},'12345678P','aragorn@gondo.com','Aragorn','Hijo de Arathorn','956345675','lanzamiento','Aragorn');

insert into tb_coach values (1);

insert into tb_team values(1,'2013-10-23','Saltadores que saltan y saltan y no paran de saltar','saltadores de postin', 'saltadores', 1);
insert into tb_team values(2,'2013-09-22','Corredores que corren como la centella','corredores como rayos', 'velocistas', 1);
insert into tb_team values(3,'2013-08-21','Tiran pesos, tiran discos tiran de todo muy lejos','Lanzadores infernales', 'Lanzadores', 1);

INSERT INTO tb_athlete (ID_USER,ID_TEAM) VALUES (2,1);
INSERT INTO tb_athlete (ID_USER,ID_TEAM) VALUES (3,1);
INSERT INTO tb_athlete (ID_USER,ID_TEAM) VALUES (4,2);
INSERT INTO tb_athlete (ID_USER,ID_TEAM) VALUES (5,2);


INSERT INTO tb_exercise_block (ID_BLOCK,DESCRIPTION,NAME,TYPE,ID_USER) VALUES (1,'','Fuerza explosiva','Fuerza',1);
INSERT INTO tb_exercise_block (ID_BLOCK,DESCRIPTION,NAME,TYPE,ID_USER) VALUES (2,'','previo saltos','salto',1);
INSERT INTO tb_exercise_block (ID_BLOCK,DESCRIPTION,NAME,TYPE,ID_USER) VALUES (3,'tanda saltos','saltos longitud','salto',1);

INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (1,'10 X sentadilla 30kg',0,1);
INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (2,'15 x semisentadilla 60kg',1,1);
INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (3,'2 x progresivos 80m',2,1);
INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (4,'5 x pies juntos',0,2);
INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (5,'10 x triple 10m',1,2);
INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (6,'3 x salto 40m',2,2);
INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (7,'3 x saltos completo',0,3);
INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (8,'10 min recuperacion',1,3);
INSERT INTO tb_exercise (ID_EXERCISE,NAME,POSITION,ID_BLOCK_FK) VALUES (9,'3 x saltos completo',2,3);

INSERT INTO tb_training (ID_TRAINING,description,NAME,type,ID_USER) VALUES (1,'Entrenamiento saltos','Entreno saltos','salto',1);
INSERT INTO tb_training (ID_TRAINING,description,NAME,type,ID_USER) VALUES (2,'','Musculación explosiva','Musculación',1);

INSERT INTO tb_training_tb_exercise_block (ID_TRAINING, ID_BLOCK) VALUES (1,2);
INSERT INTO tb_training_tb_exercise_block (ID_TRAINING, ID_BLOCK) VALUES (1,3);
INSERT INTO tb_training_tb_exercise_block (ID_TRAINING, ID_BLOCK) VALUES (2,1);

