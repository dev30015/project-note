DROP DATABASE IF EXISTS `project-note`;
CREATE DATABASE IF NOT EXISTS `project-note`;
USE `project-note`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `uid`     bigint  NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    `username` varchar(16),  
    `password` varchar(100)        NOT NULL, 
    `email`    varchar(30)       NOT NULL,
    /*start of user details*/
    `enabled` boolean default 1,
    `account_non_expired` boolean default 1,
    `account_non_locked` boolean default 1,
    `credentials_non_expired` boolean default 1,
    /*end of user details*/
    UNIQUE (`username`),
    UNIQUE (`uid`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*
Admin
Username: admin
Password: admin

User1
Username: user1
Password: user1
*/
INSERT INTO `users` VALUES 
  (1,"admin","$2a$10$cia23Z4TPAfj8tAgvrweSuCkE4Sdyb8wfDJYeZAf.jWpJzzrbt9aO","admin@test.com","1","1","1","1"),
  (2,"user1","$2a$10$xR2LbFyV4hhoNAC3USu7F.z/ri66Rl6LwsIkmFX3NeuxBVJf.CPnK","user1@test.com","1","1","1","1");

DROP TABLE IF EXISTS `profile`;
CREATE TABLE `profile` (
    `pid`     bigint  NOT NULL AUTO_INCREMENT PRIMARY KEY, 
    `country` varchar(100),
    `phone_number` varchar(50),
    `language` varchar(50),
    `profile_image_link` varchar(80),
    `user_uid` bigint NOT NULL,
    FOREIGN KEY (`user_uid`) REFERENCES `users`(`uid`)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `profile` VALUES 
  (1,"Australia","61499877653","English","images/profilePicture.jpg",1),
  (2,"Japan","61499877653","Japanese","images/profilePicture.jpg",2);

CREATE TABLE `notes` (
    `nid` bigint not null AUTO_INCREMENT PRIMARY KEY,
    `content` varchar(500),
    `date_posted` DATETIME NOT NULL,
    `user_uid` bigint not null,
    FOREIGN KEY (`user_uid`) REFERENCES `users`(`uid`)
);

INSERT INTO `notes` VALUES
  (1,"test note1","2000-12-01 13:00:00",1),
  (2,"test note2","2000-12-01 13:01:00",1),
  (3,"test note3","2000-12-01 13:01:00",1),
  (4,"test note4","2000-12-01 13:01:00",1),
  (5,"test note5","2000-12-01 13:01:00",1),
  (6,"test note6","2000-12-01 13:01:00",1),
  (7,"test note7","2000-12-01 13:01:00",1),
  (8,"test note8","2000-12-01 13:01:00",1),
  (9,"test note9","2000-12-01 13:01:00",1),
  (10,"test note10","2000-12-01 13:01:00",1),
  (11,"test note11","2000-12-01 13:01:00",1),
  (12,"test note12","2000-12-01 13:01:00",1),
  (13,"test note13","2000-12-01 13:01:00",1),
  (14,"test note14","2000-12-01 13:01:00",1),
  (15,"test note15","2000-12-01 13:01:00",1),
  (16,"test note16","2000-12-01 13:01:00",1),
  (17,"test note17","2000-12-01 13:01:00",1),
  (18,"test note18","2000-12-01 13:01:00",1),
  (19,"test note19","2000-12-01 13:01:00",1),
  (20,"test note20","2000-12-01 13:01:00",1),
  (21,"test note21","2000-12-01 13:01:00",1),
  (22,"test note22","2000-12-01 13:01:00",1),
  (23,"test note23","2000-12-01 13:01:00",1),
  (24,"test note24","2000-12-01 13:01:00",1),
  (25,"test note25","2000-12-01 13:01:00",1),
  (26,"test note26","2000-12-01 13:01:00",1),
  (27,"test note27","2000-12-01 13:01:00",1),
  (28,"test note28","2000-12-01 13:01:00",1),
  (29,"test note29","2000-12-01 13:01:00",1),
  (30,"test note30","2000-12-01 13:01:00",1),
  (31,"test note31","2000-12-01 13:01:00",1),
  (32,"test note32","2000-12-01 13:01:00",1),
  (33,"test note33","2000-12-01 13:01:00",1),
  (34,"test note34","2000-12-01 13:01:00",1),
  (35,"test note35","2000-12-01 13:01:00",1),
  (36,"test note36","2000-12-01 13:01:00",1),
  (37,"test note37","2000-12-01 13:01:00",1),
  (38,"test note38","2000-12-01 13:01:00",1),
  (39,"test note39","2000-12-01 13:01:00",1),
  (40,"test note40","2000-12-01 13:01:00",1),
  (41,"test note41","2000-12-01 13:01:00",1),
  (42,"test note42","2000-12-01 13:01:00",1),
  (43,"test note43","2000-12-01 13:01:00",1),
  (44,"test note44","2000-12-01 13:01:00",1),
  (45,"test note45","2000-12-01 13:01:00",1),
  (46,"test note46","2000-12-01 13:01:00",1),
  (47,"test note47","2000-12-01 13:01:00",1),
  (48,"test note48","2000-12-01 13:01:00",1),
  (49,"test note49","2000-12-01 13:01:00",1),
  (50,"test note50","2000-12-01 13:01:00",1);

DROP TABLE IF EXISTS `Role`;
CREATE TABLE `Role` (
    `rid` int  NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `role_name` varchar(30),
    `role_description` varchar(80)
); 

INSERT INTO `Role` VALUES 
  (1,"ROLE_ADMIN",'Administrator'),
  (2,"ROLE_MANAGER",'Manager'),
  (3,"ROLE_USER",'User');


DROP TABLE IF EXISTS `Permission`;
CREATE TABLE `Permission` (
    `pid` int NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `permission_name` varchar(30),
    `permission_tag` varchar(50)
); 


/*Many user can have many roles */
DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE `users_roles` (
  `user_id` bigint not null,
  `role_id` int not null,
  PRIMARY KEY (`user_id`,`role_id`),
  FOREIGN KEY (`user_id`) REFERENCES `users`(`uid`),
  FOREIGN KEY (`role_id`) REFERENCES `role`(`rid`)
);

insert  into `users_roles`(`user_id`,`role_id`) values (1,1),(2,3);

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int default null,
  `permission_id` int default null,
  FOREIGN KEY (`role_id`) REFERENCES `role`(`rid`),
  FOREIGN KEY (`permission_id`) REFERENCES `permission`(`pid`)
);