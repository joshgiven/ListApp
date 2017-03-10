DROP SCHEMA IF EXISTS `listappdb` ;
CREATE SCHEMA IF NOT EXISTS `listappdb` ;
USE `listappdb` ;

DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(127) UNIQUE,
  `email` VARCHAR(127) UNIQUE,
  `password` VARCHAR(255),
  PRIMARY KEY (`id`) );
-- ENGINE = InnoDB;

-- DROP TABLE IF EXISTS `todo` ;
--
-- CREATE TABLE IF NOT EXISTS `todo` (
--   `id` INT(11) NOT NULL AUTO_INCREMENT,
--   `user_id` INT(11),
--   `task` VARCHAR(127),
--   `description` VARCHAR(255),
--   `completed` BOOLEAN,
--   PRIMARY KEY (`id`) );
-- -- ENGINE = InnoDB;

-- ALTER TABLE `todo`
--   ADD CONSTRAINT `fk_todo_user` FOREIGN KEY (`user_id`)
--   REFERENCES `user` (`id`);

-- SET SQL_MODE = '';
GRANT USAGE ON *.* TO lister@localhost;
 DROP USER lister@localhost;
-- SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'lister'@'localhost' IDENTIFIED BY 'lister';

GRANT SELECT, INSERT, TRIGGER ON TABLE * TO 'lister'@'localhost';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'lister'@'localhost';
