
    alter table TB_ATHLETE 
        drop 
        foreign key FKF033B8CE981F9C23;

    alter table TB_ATHLETE 
        drop 
        foreign key FKF033B8CE9820EEFF;

    alter table TB_CALENDAR_EVENT 
        drop 
        foreign key FKD7005C2A981F9C23;

    alter table TB_CALENDAR_EVENT 
        drop 
        foreign key FKD7005C2A68374C9D;

    alter table TB_COACH 
        drop 
        foreign key FK5F2F6F699820EEFF;

    alter table TB_EXERCISE 
        drop 
        foreign key FK8495F489BAB80CCB;

    alter table TB_EXERCISE_BLOCK 
        drop 
        foreign key FK6666D937DF747AE4;

    alter table TB_TEAM 
        drop 
        foreign key FKD18D3D8E69FDDEEB;

    alter table TB_TRAINING 
        drop 
        foreign key FK5614FB8BDF747AE4;

    alter table TB_TRAINING_TB_EXERCISE_BLOCK 
        drop 
        foreign key FK8AF911834B70C8D9;

    alter table TB_TRAINING_TB_EXERCISE_BLOCK 
        drop 
        foreign key FK8AF9118368374C9D;

    drop table if exists TB_ATHLETE;

    drop table if exists TB_CALENDAR_EVENT;

    drop table if exists TB_COACH;

    drop table if exists TB_EXERCISE;

    drop table if exists TB_EXERCISE_BLOCK;

    drop table if exists TB_TEAM;

    drop table if exists TB_TRAINING;

    drop table if exists TB_TRAINING_TB_EXERCISE_BLOCK;

    drop table if exists TB_USER;

    create table TB_ATHLETE (
        ID_USER bigint not null,
        ID_TEAM bigint not null,
        primary key (ID_USER)
    ) ENGINE=InnoDB;

    create table TB_CALENDAR_EVENT (
        ID_EVENT bigint not null auto_increment,
        EVENT_DATE datetime,
        PERIODIC_EVENT bit,
        ID_TEAM bigint not null,
        ID_TRAINING bigint not null,
        primary key (ID_EVENT),
        unique (ID_TEAM, EVENT_DATE)
    ) ENGINE=InnoDB;

    create table TB_COACH (
        ID_USER bigint not null,
        primary key (ID_USER)
    ) ENGINE=InnoDB;

    create table TB_EXERCISE (
        ID_EXERCISE bigint not null auto_increment,
        NAME varchar(255) not null,
        POSITION integer not null,
        ID_BLOCK_FK bigint not null,
        primary key (ID_EXERCISE),
        unique (POSITION, ID_BLOCK_FK)
    ) ENGINE=InnoDB;

    create table TB_EXERCISE_BLOCK (
        ID_BLOCK bigint not null auto_increment,
        DESCRIPTION varchar(255),
        NAME varchar(255) not null,
        TYPE varchar(255) not null,
        ID_USER bigint not null,
        primary key (ID_BLOCK),
        unique (NAME, ID_USER)
    ) ENGINE=InnoDB;

    create table TB_TEAM (
        ID_Team bigint not null auto_increment,
        DATE_CREATE datetime not null,
        DESCRIPTION varchar(255) not null,
        NAME varchar(255) not null,
        TEAM_TYPE varchar(255) not null,
        ID_COACH bigint not null,
        primary key (ID_Team)
    ) ENGINE=InnoDB;

    create table TB_TRAINING (
        id_training bigint not null auto_increment,
        description varchar(255),
        name varchar(255) not null,
        type varchar(255) not null,
        id_user bigint not null,
        primary key (id_training),
        unique (name, id_user)
    ) ENGINE=InnoDB;

    create table TB_TRAINING_TB_EXERCISE_BLOCK (
        id_training bigint not null,
        id_block bigint not null
    ) ENGINE=InnoDB;

    create table TB_USER (
        ID_USER bigint not null auto_increment,
        ADDRESS varchar(255),
        COMMENTS varchar(255),
        DATE_BIRTH datetime,
        DNI varchar(255) not null unique,
        EMAIL varchar(255),
        NAME varchar(255),
        SURNAME varchar(255),
        TELEPHONE varchar(255),
        TYPE varchar(255),
        USER_NAME varchar(255) not null unique,
        primary key (ID_USER),
        unique (USER_NAME)
    ) ENGINE=InnoDB;

    alter table TB_ATHLETE 
        add index FKF033B8CE981F9C23 (ID_TEAM), 
        add constraint FKF033B8CE981F9C23 
        foreign key (ID_TEAM) 
        references TB_TEAM (ID_Team);

    alter table TB_ATHLETE 
        add index FKF033B8CE9820EEFF (ID_USER), 
        add constraint FKF033B8CE9820EEFF 
        foreign key (ID_USER) 
        references TB_USER (ID_USER);

    alter table TB_CALENDAR_EVENT 
        add index FKD7005C2A981F9C23 (ID_TEAM), 
        add constraint FKD7005C2A981F9C23 
        foreign key (ID_TEAM) 
        references TB_TEAM (ID_Team);

    alter table TB_CALENDAR_EVENT 
        add index FKD7005C2A68374C9D (ID_TRAINING), 
        add constraint FKD7005C2A68374C9D 
        foreign key (ID_TRAINING) 
        references TB_TRAINING (id_training);

    alter table TB_COACH 
        add index FK5F2F6F699820EEFF (ID_USER), 
        add constraint FK5F2F6F699820EEFF 
        foreign key (ID_USER) 
        references TB_USER (ID_USER);

    alter table TB_EXERCISE 
        add index FK8495F489BAB80CCB (ID_BLOCK_FK), 
        add constraint FK8495F489BAB80CCB 
        foreign key (ID_BLOCK_FK) 
        references TB_EXERCISE_BLOCK (ID_BLOCK);

    alter table TB_EXERCISE_BLOCK 
        add index FK6666D937DF747AE4 (ID_USER), 
        add constraint FK6666D937DF747AE4 
        foreign key (ID_USER) 
        references TB_COACH (ID_USER);

    alter table TB_TEAM 
        add index FKD18D3D8E69FDDEEB (ID_COACH), 
        add constraint FKD18D3D8E69FDDEEB 
        foreign key (ID_COACH) 
        references TB_COACH (ID_USER);

    alter table TB_TRAINING 
        add index FK5614FB8BDF747AE4 (id_user), 
        add constraint FK5614FB8BDF747AE4 
        foreign key (id_user) 
        references TB_COACH (ID_USER);

    alter table TB_TRAINING_TB_EXERCISE_BLOCK 
        add index FK8AF911834B70C8D9 (id_block), 
        add constraint FK8AF911834B70C8D9 
        foreign key (id_block) 
        references TB_EXERCISE_BLOCK (ID_BLOCK);

    alter table TB_TRAINING_TB_EXERCISE_BLOCK 
        add index FK8AF9118368374C9D (id_training), 
        add constraint FK8AF9118368374C9D 
        foreign key (id_training) 
        references TB_TRAINING (id_training);
