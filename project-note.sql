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

User2
Username: john
Password: johnPass123

User3
Username: johnson
Password: johnsonPass123

User4
Username: julia
Password: juliaPass123


User5
Username: irelia
Password: ireliaPass123

*/
INSERT INTO `users` VALUES 
  (1,"admin","$2a$10$cia23Z4TPAfj8tAgvrweSuCkE4Sdyb8wfDJYeZAf.jWpJzzrbt9aO","admin@test.com","1","1","1","1"),
  (2,"john","$2a$12$1udvJfFypmhb1gudYeftSuCq4HWqZ5qJ7VuJuhQaDAJz6BHDMEb6u","user1@test.com","1","1","1","1"),
  (3,"johnson","$2a$12$7ucc0xtQ8EqHjtC62SQ/G.srQHKH1PIRXd1FqlMep1RmbpOovhnQW","user2@test.com","1","1","1","1"),
  (4,"julia","$2a$12$DVnzjTOoBnKhqf5SltyAWu2zWsu2jzKzLXZDfCpJ.jRcuIWMwOwSq","user2@test.com","1","1","1","1"),
  (5,"irelia","$2a$12$fbU1AZMDVLFXVGO7rIEd1.BsIY5MgaEuLT1X6cRN9lZmEU41b.0dy","user2@test.com","1","1","1","1");

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
  (2,"Japan","61499877653","English","images/profilePicture.jpg",2),
  (3,"UK","61499877655","English","images/profilePicture.jpg",3),
  (4,"UK","61499877652","English","images/profilePicture.jpg",4),
  (5,"UK","61499877651","English","images/profilePicture.jpg",5);

CREATE TABLE `notes` (
    `nid` bigint not null AUTO_INCREMENT PRIMARY KEY,
    `content` varchar(500),
    `date_posted` DATETIME NOT NULL,
    `user_uid` bigint not null,
    FOREIGN KEY (`user_uid`) REFERENCES `users`(`uid`)
);

INSERT INTO `notes` VALUES
  (1,"admin test note1","2000-12-01 13:00:00",1),
  (2,"admin test note2","2000-12-02 13:01:00",1),
  (3,"admin test note3","2000-12-03 13:01:00",1),
  (4,"admin test note4","2000-12-04 13:01:00",1),
  (5,"admin test note5","2000-12-05 13:01:00",1),
  (6,"admin test note6","2000-12-06 13:01:00",1),
  (7,"admin test note7","2000-12-07 13:01:00",1),
  (8,"admin test note8","2000-12-08 13:01:00",1),
  (9,"admin test note9","2000-12-09 13:01:00",1),
  (10,"admin test note10","2000-12-10 13:01:00",2),
  (11,"john note 1","2000-12-01 13:01:00",2),
  (12,"john note 2","2000-12-02 13:01:00",2),
  (13,"john note 3","2000-12-03 13:01:00",2),
  (14,"john note 4","2000-12-04 13:01:00",2),
  (15,"john note 5","2000-12-05 13:01:00",2),
  (16,"john note 6","2000-12-06 13:01:00",2),
  (17,"john note 7","2000-12-07 13:01:00",2),
  (18,"john note 8","2000-12-08 13:01:00",2),
  (19,"john note 9","2000-12-09 13:01:00",2),
  (20,"john note 10","2000-12-10 13:01:00",2),
  (21,"johnson note 1","2000-12-01 13:01:00",3),
  (22,"johnson note 2","2000-12-02 13:01:00",3),
  (23,"johnson note 3","2000-12-03 13:01:00",3),
  (24,"johnson note 4","2000-12-04 13:01:00",3),
  (25,"johnson note 5","2000-12-05 13:01:00",3),
  (26,"johnson note 6","2000-12-06 13:01:00",3),
  (27,"johnson note 7","2000-12-07 13:01:00",3),
  (28,"johnson note 8","2000-12-08 13:01:00",3),
  (29,"johnson note 9","2000-12-09 13:01:00",3),
  (30,"johnson note 10","2000-12-10 13:01:00",3),
  (31,"julia note 1","2000-12-01 13:01:00",4),
  (32,"julia note 2","2000-12-02 13:01:00",4),
  (33,"julia note 3","2000-12-03 13:01:00",4),
  (34,"julia note 4","2000-12-04 13:01:00",4),
  (35,"julia note 5","2000-12-05 13:01:00",4),
  (36,"julia note 6","2000-12-06 13:01:00",4),
  (37,"julia note 7","2000-12-07 13:01:00",4),
  (38,"julia note 8","2000-12-08 13:01:00",4),
  (39,"julia note 9","2000-12-09 13:01:00",4),
  (40,"julia note 10","2000-12-10 13:01:00",4),
  (41,"irelia note 1","2000-12-01 13:01:00",5),
  (42,"irelia note 2","2000-12-02 13:01:00",5),
  (43,"irelia note 3","2000-12-03 13:01:00",5),
  (44,"irelia note 4","2000-12-04 13:01:00",5),
  (45,"irelia note 5","2000-12-05 13:01:00",5),
  (46,"irelia note 6","2000-12-06 13:01:00",5),
  (47,"irelia note 7","2000-12-07 13:01:00",5),
  (48,"irelia note 8","2000-12-08 13:01:00",5),
  (49,"irelia note 9","2000-12-09 13:01:00",5),
  (50,"irelia note 10","2000-12-10 13:01:00",5);


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

insert  into `users_roles`(`user_id`,`role_id`) values 
  (1,1),
  (2,3),
  (3,3),
  (4,3),
  (5,3);

DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `role_id` int default null,
  `permission_id` int default null,
  FOREIGN KEY (`role_id`) REFERENCES `role`(`rid`),
  FOREIGN KEY (`permission_id`) REFERENCES `permission`(`pid`)
);