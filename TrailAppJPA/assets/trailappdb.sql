-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema traildb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `traildb` ;

-- -----------------------------------------------------
-- Schema traildb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `traildb` DEFAULT CHARACTER SET utf8 ;
USE `traildb` ;

-- -----------------------------------------------------
-- Table `traildb`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traildb`.`user` ;

CREATE TABLE IF NOT EXISTS `traildb`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(200) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `description` VARCHAR(500) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traildb`.`trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traildb`.`trail` ;

CREATE TABLE IF NOT EXISTS `traildb`.`trail` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NULL,
  `name` VARCHAR(45) NOT NULL,
  `api_id` VARCHAR(45) NOT NULL,
  `directions` BLOB NULL,
  `latitude` DOUBLE NULL,
  `longitude` DOUBLE NULL,
  `description` BLOB NULL,
  `length` DOUBLE NULL,
  `image_url` BLOB NULL,
  `activity_type` VARCHAR(45) NOT NULL DEFAULT 'Hiking',
  `recent_report_id` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traildb`.`report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traildb`.`report` ;

CREATE TABLE IF NOT EXISTS `traildb`.`report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT(1000) NULL,
  `timestamp` DATETIME NULL DEFAULT NOW(),
  `heading` VARCHAR(255) NULL,
  `trail_id` INT NOT NULL,
  PRIMARY KEY (`id`, `trail_id`),
  INDEX `fk_report_trail1_idx` (`trail_id` ASC),
  CONSTRAINT `fk_report_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `traildb`.`trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traildb`.`tstatus`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traildb`.`tstatus` ;

CREATE TABLE IF NOT EXISTS `traildb`.`tstatus` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NULL,
  `status_type` ENUM('snow', 'ground', 'passability') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traildb`.`user_has_trail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traildb`.`user_has_trail` ;

CREATE TABLE IF NOT EXISTS `traildb`.`user_has_trail` (
  `user_id` INT NOT NULL,
  `trail_id` INT NOT NULL,
  PRIMARY KEY (`user_id`, `trail_id`),
  INDEX `fk_user_has_trail_trail1_idx` (`trail_id` ASC),
  INDEX `fk_user_has_trail_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_user_has_trail_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `traildb`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_trail_trail1`
    FOREIGN KEY (`trail_id`)
    REFERENCES `traildb`.`trail` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `traildb`.`status_has_report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `traildb`.`status_has_report` ;

CREATE TABLE IF NOT EXISTS `traildb`.`status_has_report` (
  `status_id` INT NOT NULL,
  `report_id` INT NOT NULL,
  PRIMARY KEY (`status_id`, `report_id`),
  INDEX `fk_status_has_report_report1_idx` (`report_id` ASC),
  INDEX `fk_status_has_report_status1_idx` (`status_id` ASC),
  CONSTRAINT `fk_status_has_report_status1`
    FOREIGN KEY (`status_id`)
    REFERENCES `traildb`.`tstatus` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_status_has_report_report1`
    FOREIGN KEY (`report_id`)
    REFERENCES `traildb`.`report` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
GRANT USAGE ON *.* TO trailuser@localhost;
 DROP USER trailuser@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
CREATE USER 'trailuser'@'localhost' IDENTIFIED BY 'trailuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE `traildb`.* TO 'trailuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
