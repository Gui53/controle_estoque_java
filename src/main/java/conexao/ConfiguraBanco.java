package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsável por criar o banco de dados e as tabelas do sistema caso
 * ainda não existam.
 *
 * @author Gabriel Conci
 * @see java.sql.Connection
 */
public class ConfiguraBanco {

    /**
     * Inicializa o banco de dados e cria as tabelas necessárias caso ainda não
     * existam.
     *
     * @param user Usuário do banco de dados
     * @param password Senha do banco de dados
     */
    public static void inicializar(String user, String password) {
        String url = "jdbc:mysql://localhost:3306/?useTimezone=true&serverTimezone=UTC";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS db_controledeestoque");
            stmt.executeUpdate("USE db_controledeestoque");

            stmt.executeUpdate("""
                CREATE TABLE IF NOT EXISTS `db_controledeestoque`.`tb_categoria` (
                  `id` INT NOT NULL AUTO_INCREMENT,
                  `nome` VARCHAR(45) NOT NULL,
                  `tamanho` ENUM('PEQUENO', 'MEDIO', 'GRANDE') NOT NULL,
                  `embalagem` ENUM('LATA', 'VIDRO', 'PLASTICO') NOT NULL,
                  PRIMARY KEY (`id`))
                ENGINE = InnoDB
            """);

            stmt.executeUpdate("""
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
                ENGINE = InnoDB
            """);

            stmt.executeUpdate("""
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
                ENGINE = InnoDB
            """);

            System.out.println("Banco de dados inicializado com sucesso!");

            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado: " + e);
        } catch (SQLException e) {
            System.out.println("Erro ao inicializar banco: " + e);
        }
    }
}
