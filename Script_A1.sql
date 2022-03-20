-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema sd_project
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema sd_project
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `sd_project` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
-- -----------------------------------------------------
-- Schema sd_assignment1
-- -----------------------------------------------------
USE `sd_project` ;

-- -----------------------------------------------------
-- Table `sd_project`.`area`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_project`.`area` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_project`.`ground type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_project`.`ground type` (
  `id` INT NOT NULL,
  `typeOfGround` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_project`.`user status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_project`.`user status` (
  `userStatusID` INT NOT NULL,
  `status` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`userStatusID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_project`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_project`.`user` (
  `userID` INT NOT NULL,
  `firstName` VARCHAR(45) NULL DEFAULT NULL,
  `lastName` VARCHAR(45) NULL DEFAULT NULL,
  `username` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `statusID` INT NOT NULL,
  PRIMARY KEY (`userID`),
  INDEX `status_idx` (`statusID` ASC) VISIBLE,
  CONSTRAINT `status`
    FOREIGN KEY (`statusID`)
    REFERENCES `sd_project`.`user status` (`userStatusID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_project`.`tennis court`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_project`.`tennis court` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `areaId` INT NULL DEFAULT NULL,
  `manager` INT NULL DEFAULT NULL,
  `typeOfGroundId` INT NULL DEFAULT NULL,
  `pricePerHour` INT NOT NULL,
  `description` VARCHAR(255) NULL DEFAULT NULL,
  `image` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `ground_type_idx` (`typeOfGroundId` ASC) VISIBLE,
  INDEX `area_idx` (`areaId` ASC) VISIBLE,
  INDEX `manager_idx` (`manager` ASC) VISIBLE,
  CONSTRAINT `area`
    FOREIGN KEY (`areaId`)
    REFERENCES `sd_project`.`area` (`id`),
  CONSTRAINT `ground_type`
    FOREIGN KEY (`typeOfGroundId`)
    REFERENCES `sd_project`.`ground type` (`id`),
  CONSTRAINT `manager`
    FOREIGN KEY (`manager`)
    REFERENCES `sd_project`.`user` (`userID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_project`.`booking`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_project`.`booking` (
  `id` INT NOT NULL,
  `user` INT NULL DEFAULT NULL,
  `tennisCourt` INT NULL DEFAULT NULL,
  `date` DATE NULL DEFAULT NULL,
  `startHour` INT NULL DEFAULT NULL,
  `endHour` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`user` ASC) VISIBLE,
  INDEX `tenniscourt_idx` (`tennisCourt` ASC) VISIBLE,
  CONSTRAINT `tenniscourt`
    FOREIGN KEY (`tennisCourt`)
    REFERENCES `sd_project`.`tennis court` (`id`),
  CONSTRAINT `user`
    FOREIGN KEY (`user`)
    REFERENCES `sd_project`.`user` (`userID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `sd_project`.`review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sd_project`.`review` (
  `id` INT NOT NULL,
  `review` VARCHAR(255) NULL DEFAULT NULL,
  `userID` INT NULL DEFAULT NULL,
  `tennisCourt` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_idx` (`userID` ASC) VISIBLE,
  INDEX `tennisCourt_idx` (`tennisCourt` ASC) VISIBLE,
  CONSTRAINT `client`
    FOREIGN KEY (`userID`)
    REFERENCES `sd_project`.`user` (`userID`),
  CONSTRAINT `tennisCourtreview`
    FOREIGN KEY (`tennisCourt`)
    REFERENCES `sd_project`.`tennis court` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
