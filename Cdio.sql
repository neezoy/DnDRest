-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cdio
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema cdio
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cdio` DEFAULT CHARACTER SET utf8 ;
USE `cdio` ;

-- -----------------------------------------------------
-- Table `cdio`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdio`.`User` ;

CREATE TABLE IF NOT EXISTS `cdio`.`User` (
  `UserID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Username` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NOT NULL,
  `UStatus` TINYINT UNSIGNED DEFAULT 0,
  `Roles` TINYINT NULL DEFAULT 0,
  UNIQUE INDEX `UserID_UNIQUE` (`UserID` ASC) VISIBLE,
  PRIMARY KEY (`UserID`))
ENGINE = InnoDB;
Alter table cdio.user Auto_INCREMENT=100;


-- -----------------------------------------------------
-- Table `cdio`.`Character`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdio`.`Character` ;

CREATE TABLE IF NOT EXISTS `cdio`.`Character` (
  `CharacterID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `CName` VARCHAR(45) NOT NULL,
  `CLocation` VARCHAR(45) NULL DEFAULT "Frontier City",
  `Strength` INT UNSIGNED NULL,
  `BonusCapacity` INT NULL,
  `CStatus` TINYINT UNSIGNED DEFAULT 0,
  PRIMARY KEY (`CharacterID`),
  UNIQUE INDEX `CharacterID_UNIQUE` (`CharacterID` ASC) VISIBLE)
ENGINE = InnoDB;
Alter table cdio.character AUTO_INCREMENT=1000;


-- -----------------------------------------------------
-- Table `cdio`.`Item`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdio`.`Item` ;

CREATE TABLE IF NOT EXISTS `cdio`.`Item` (
  `ItemID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `ItemName` VARCHAR(45) NOT NULL,
  `Weight` DOUBLE UNSIGNED NULL DEFAULT 0,
  `IDescription` TEXT NULL,
  PRIMARY KEY (`ItemID`),
  UNIQUE INDEX `ItemID_UNIQUE` (`ItemID` ASC) VISIBLE)
ENGINE = InnoDB;
alter table cdio.item AUTO_INCREMENT=1000;


-- -----------------------------------------------------
-- Table `cdio`.`Group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdio`.`Group` ;

CREATE TABLE IF NOT EXISTS `cdio`.`Group` (
  `GroupID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `GroupName` VARCHAR(45) NOT NULL,
  `GDescription` TEXT NULL,
  PRIMARY KEY (`GroupID`),
  UNIQUE INDEX `GroupID_UNIQUE` (`GroupID` ASC) VISIBLE)
ENGINE = InnoDB;
alter table cdio.group AUTO_INCREMENT=10000;

-- -----------------------------------------------------
-- Table `cdio`.`Session`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdio`.`Session` ;

CREATE TABLE IF NOT EXISTS `cdio`.`Session` (
  `SessionID` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Date` Datetime NOT NULL,
  `SLocation` VARCHAR(45) NOT NULL,
  `Amount` TINYINT NOT NULL,
  UNIQUE INDEX `SessionID_UNIQUE` (`SessionID` ASC) VISIBLE,
  PRIMARY KEY (`SessionID`))
ENGINE = InnoDB;
Alter table cdio.Session Auto_INCREMENT=10000;

-- -----------------------------------------------------
-- Table `cdio`.`CharacterRelation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdio`.`CharacterRelation` ;

CREATE TABLE IF NOT EXISTS `cdio`.`CharacterRelation` (
  `CharacterID` INT UNSIGNED NOT NULL,
  `UserID` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`CharacterID`),
  INDEX `UserID_idx` (`UserID` ASC) VISIBLE,
  CONSTRAINT `CharacterID1`
    FOREIGN KEY (`CharacterID`)
    REFERENCES `cdio`.`Character` (`CharacterID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `UserID`
    FOREIGN KEY (`UserID`)
    REFERENCES `cdio`.`User` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cdio`.`ItemRelation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdio`.`ItemRelation` ;

CREATE TABLE IF NOT EXISTS `cdio`.`ItemRelation` (
  `ItemID` INT UNSIGNED NOT NULL,
  `CharacterID` INT UNSIGNED NOT NULL,
  `Amount` INT UNSIGNED NOT NULL DEFAULT 1,
  PRIMARY KEY (`ItemID`, `CharacterID`),
  INDEX `CharacterID_idx` (`CharacterID` ASC) VISIBLE,
  CONSTRAINT `ItemID`
    FOREIGN KEY (`ItemID`)
    REFERENCES `cdio`.`Item` (`ItemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CharacterID3`
    FOREIGN KEY (`CharacterID`)
    REFERENCES `cdio`.`Character` (`CharacterID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cdio`.`GroupRelation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cdio`.`GroupRelation` ;

CREATE TABLE IF NOT EXISTS `cdio`.`GroupRelation` (
  `GroupID` INT UNSIGNED NOT NULL,
  `CharacterID` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`GroupID`, `CharacterID`),
  INDEX `CharacterID_idx` (`CharacterID` ASC) VISIBLE,
  CONSTRAINT `GroupID`
    FOREIGN KEY (`GroupID`)
    REFERENCES `cdio`.`Group` (`GroupID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CharacterID2`
    FOREIGN KEY (`CharacterID`)
    REFERENCES `cdio`.`Character` (`CharacterID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

DROP TABLE IF EXISTS `cdio`.`SessionRelation` ;

CREATE TABLE IF NOT EXISTS `cdio`.`SessionRelation` (
  `SessionID` INT UNSIGNED NOT NULL,
  `CharacterID` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`SessionID`, `CharacterID`),
  INDEX `CharacterID_idx` (`CharacterID` ASC) VISIBLE,
  CONSTRAINT `SessionID`
    FOREIGN KEY (`SessionID`)
    REFERENCES `cdio`.`Session` (`SessionID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `CharacterID3`
    FOREIGN KEY (`CharacterID`)
    REFERENCES `cdio`.`Character` (`CharacterID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


INSERT `Character` (CName, Location, Strength, BonusCapacity) VALUES ( 'Thanos', 'Narnia', 10, 5), ( 'CaptainAmerica', 'Stormwind', 70, 0), ( 'KaelThas', 'Azeroth', 1, 1);
insert `user` (Username, `Password`, Roles) values ('Zaccie', 1234, 1), ('Wowser', 2843762, 3), ('Bowser', 2828, 2);

insert item (ItemName, Weight, `IDescription`) values ('Logg of gods', 5, 'Insane'), ('Sword of a thousand screams', 1, 'Girly screams'), ('Spear of the barbarian', 5, 'Spear that bastard');

insert `group` (groupname, `Gdescription`) values ('Crusaders', 'Apply here'), ('Method EU', 'L33t'), ('The bois', 'No girls allowed');




DROP USER if exists administrator@localhost;
DROP USER if exists gamemaster@localhost;

CREATE USER administrator@localhost IDENTIFIED BY 'password';
CREATE USER gamemaster@localhost IDENTIFIED BY 'password';

GRANT ALL privileges ON Cdio.* TO administrator@localhost;
GRANT SELECT ON Cdio.* to gamemaster@localhost;
GRANT UPDATE, INSERT ON Cdio.`character` TO gamemaster@localhost;
GRANT UPDATE, INSERT ON Cdio.`item` TO gamemaster@localhost;
GRANT UPDATE, INSERT ON Cdio.`group` TO gamemaster@localhost;
GRANT UPDATE, INSERT ON Cdio.`character` TO gamemaster@localhost;
GRANT UPDATE, INSERT ON Cdio.`characterrelation` TO gamemaster@localhost;
GRANT UPDATE, INSERT ON Cdio.`itemrelation` TO gamemaster@localhost;
GRANT UPDATE, INSERT ON Cdio.`grouprelation` TO gamemaster@localhost;

GRANT INSERT (UserID, Username, `Password`, Roles ) ON Cdio.`user` TO gamemaster@localhost;









SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
SET GLOBAL time_zone = '+1:00';