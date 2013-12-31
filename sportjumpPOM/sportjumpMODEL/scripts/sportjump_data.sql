insert into tb_user values (1, '', '', '1981-12-11', '31722664y', 'juanminet@gmail.com', 'Juan Miguel', 'Ro','', '', 'jumanji');
insert into tb_user values (2, '', '', '1981-12-11', '11111111p', 'ambrin@gmail.com', 'Ambrin', 'Chaudary','', '', 'ambrin');
insert into tb_user values (3, '', '', '1981-12-11', '22222222p', 'antonio@gmail.com', 'Antonio', 'Rivas','', '', 'antonio');
insert into tb_user values (4, '', '', '1981-12-11', '33333333p', 'fost@gmail.com', 'Juan', 'De la casa','', '', 'juaniyo');
insert into tb_user values (5, '', '', '1981-12-11', '44444444p', 'pedro@gmail.com', 'Pedro', 'De la casa','', '', 'pedro');
insert into tb_user values (6, '', '', '1981-12-11', '55555555p', 'ris@gmail.com', 'Risto', 'Mejide','', '', 'ris');
insert into tb_user values (7, '', '', '1981-12-11', '66666666i', 'rock@gmail.com', 'Rock', 'Rockstar','', '', 'rock');
insert into tb_user values (8, '', '', '1981-12-11', '77777777p', 'sonia@gmail.com', 'Sonia', 'Argentina','', '', 'soniar');
insert into tb_user values (9, '', '', '1981-12-11', '88888888p', 'acemab@gmail.com', 'Alfonso', 'Cerezo','', '', 'acema5b');
insert into tb_user values (10, '', '', '1981-12-11', '99999999p', 'lucas@gmail.com', 'Lucas', 'Moura','', '', 'lucas');

insert into tb_coach values (1);
insert into tb_coach values (3);
insert into tb_coach values (5);
insert into tb_coach values (9);


insert into tb_team values(1,'2013-10-23','Saltadores que saltan y saltan y no paran de saltar','saltadores de postin', 'saltadores', 1);
insert into tb_team values(2,'2013-09-22','Corredores que corren como la centella','corredores como rayos', 'velocistas', 1);
insert into tb_team values(3,'2013-08-21','Tiran pesos, tiran discos tiran de todo muy lejos','Lanzadores infernales', 'Lanzadores', 1);

insert into tb_athlete values (2,1);
insert into tb_athlete values (4,1);
insert into tb_athlete values (6,1);
insert into tb_athlete values (7,1);
insert into tb_athlete values (8,1);
insert into tb_athlete values (10,1);


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
