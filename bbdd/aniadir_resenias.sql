-- -----------------------------------------------------
-- Table `gestiondocente`.`resenias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gestiondocente`.`resenias` (
  `codigo` INT NOT NULL AUTO_INCREMENT,
  `resenia` TEXT(250) NOT NULL,
  `fecha` DATE NOT NULL,
  `alumno_codigo` INT NOT NULL,
  `curso_codigo` INT NOT NULL,
  PRIMARY KEY (`codigo`),
  INDEX `fk_resenias_alumno1_idx` (`alumno_codigo` ASC) VISIBLE,
  INDEX `fk_resenias_curso1_idx` (`curso_codigo` ASC) VISIBLE,
  CONSTRAINT `fk_resenias_alumno1`
    FOREIGN KEY (`alumno_codigo`)
    REFERENCES `gestiondocente`.`alumno` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_resenias_curso1`
    FOREIGN KEY (`curso_codigo`)
    REFERENCES `gestiondocente`.`curso` (`codigo`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;