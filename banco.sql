CREATE DATABASE IF NOT EXISTS db_controledeestoque;

USE db_controledeestoque;

CREATE TABLE IF NOT EXISTS `db_controledeestoque`.`tb_categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `tamanho` ENUM('PEQUENO', 'MEDIO', 'GRANDE') NOT NULL,
  `embalagem` ENUM('LATA', 'VIDRO', 'PLASTICO') NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_controledeestoque`.`tb_produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `preco_unitario` DECIMAL(10,2) NOT NULL,
  `unidade` ENUM('KG', 'LITRO', 'UNIDADE', 'PACOTE') NOT NULL,
  `quantidade_estoque` DECIMAL(10,2) NOT NULL,
  `quantidade_minima` DECIMAL(10,2) NOT NULL,
  `quantidade_maxima` DECIMAL(10,2) NOT NULL,
  `tb_categoria_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_produto_tb_categoria_idx` (`tb_categoria_id` ASC) VISIBLE,
  CONSTRAINT `fk_tb_produto_tb_categoria`
    FOREIGN KEY (`tb_categoria_id`)
    REFERENCES `db_controledeestoque`.`tb_categoria` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `db_controledeestoque`.`tb_movimentacao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_movimentacao` DATE NOT NULL,
  `quantidade_movimentada` DECIMAL(10,2) NOT NULL,
  `tipo_movimentacao` ENUM('SAIDA', 'ENTRADA') NOT NULL,
  `tb_produto_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tb_movimentacao_tb_produto1_idx` (`tb_produto_id` ASC) VISIBLE,
  CONSTRAINT `fk_tb_movimentacao_tb_produto1`
    FOREIGN KEY (`tb_produto_id`)
    REFERENCES `db_controledeestoque`.`tb_produto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;