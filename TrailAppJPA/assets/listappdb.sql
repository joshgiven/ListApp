SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
SHOW WARNINGS;
-- -----------------------------------------------------
-- Schema listappdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `listappdb` ;
CREATE SCHEMA IF NOT EXISTS `listappdb` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `listappdb` ;

-- -----------------------------------------------------
-- Table `User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `User` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `User` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(127) NULL DEFAULT NULL,
  `email` VARCHAR(127) NULL DEFAULT NULL,
  `password` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username` (`username` ASC),
  UNIQUE INDEX `email` (`email` ASC))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Item` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Item` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(127) NULL,
  `description` VARCHAR(256) NULL,
  `image_url` VARCHAR(127) NULL,
  `url` VARCHAR(127) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `AggList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `AggList` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `AggList` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(127) NULL,
  `owner_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_alist_user_idx` (`owner_id` ASC),
  CONSTRAINT `fk_alist_owner`
    FOREIGN KEY (`owner_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `UserList`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UserList` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `UserList` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(127) NULL,
  `alist_id` INT(11) NULL,
  `user_id` INT(11) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_ulist_alist_idx` (`alist_id` ASC),
  INDEX `fk_ulist_user_idx` (`user_id` ASC),
  CONSTRAINT `fk_ulist_alist`
    FOREIGN KEY (`alist_id`)
    REFERENCES `AggList` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ulist_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Category` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Category` (
  `id` INT(11) NOT NULL,
  `name` VARCHAR(127) NULL,
  `desc` VARCHAR(255) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `UserList_Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `UserList_Item` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `UserList_Item` (
  `ulist_id` INT(11) NOT NULL,
  `item_id` INT(11) NOT NULL,
  PRIMARY KEY (`ulist_id`, `item_id`),
  INDEX `fk_ulistitem_item_idx` (`item_id` ASC),
  CONSTRAINT `fk_ulistitem_ulist`
    FOREIGN KEY (`ulist_id`)
    REFERENCES `UserList` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ulistitem_item`
    FOREIGN KEY (`item_id`)
    REFERENCES `Item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `Item_Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Item_Category` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `Item_Category` (
  `item_id` INT(11) NOT NULL,
  `cat_id` INT(11) NOT NULL,
  PRIMARY KEY (`item_id`, `cat_id`),
  INDEX `fk_itemcat_category_idx` (`cat_id` ASC),
  CONSTRAINT `fk_itemcat_category`
    FOREIGN KEY (`cat_id`)
    REFERENCES `Category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_itemcat_item`
    FOREIGN KEY (`item_id`)
    REFERENCES `Item` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
SET SQL_MODE = '';
GRANT USAGE ON *.* TO lister@localhost;
 DROP USER lister@localhost;
SET SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';
SHOW WARNINGS;
CREATE USER 'lister'@'localhost' IDENTIFIED BY 'lister';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'lister'@'localhost';
GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'lister'@'localhost';
SHOW WARNINGS;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
