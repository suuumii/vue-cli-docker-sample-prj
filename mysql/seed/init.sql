DROP DATABASE IF EXISTS gc2_db;
CREATE DATABASE gc2_db;

DROP TABLE IF EXSISTS sc_users;
CREATE TABLE "sc_users" (
	"id" BIGINT NOT NULL,
	"name" VARCHAR(256) NOT NULL primary key,
	"password" VARCHAR(30) NOT NULL,
    "del_flg" BOOLEAN NOT NULL,
    "created" DATETIME NOT NULL,
    "created_user" VARCHAR(256) NOT NULL,
    "modified" DATETIME NOT NULL,
    "modified_user" VARCHAR(256) NOT NULL,
);

INSERT INTO `sc_users` (`id`,`name`,`password`,`del_flg`,`created`,`created_user`,`modified`,`modified_user`) VALUES (1, 'devuser','devuser',0,now(),1,now(),1);
INSERT INTO `sc_users` (`id`,`name`,`password`,`del_flg`,`created`,`created_user`,`modified`,`modified_user`) VALUES (2, 'adminuser','adminuser',0,now(),1,now(),1);



