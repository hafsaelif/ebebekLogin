CREATE TABLE `myusers`.`users` (
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `conf_pass` VARCHAR(45) NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB