package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConexao() {
        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "localhost";
            String database = "db_controledeestoque";

            String url = "jdbc:mysql://" + server + ":3306/"
                    + database + "?useTimezone=true&serverTimezone=UTC";

            String user = "root";
            String password = "123456";

            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Status: Conectado!");
            }

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("Driver não encontrado.");
            return null;

        } catch (SQLException e) {

            System.out.println("Erro de conexão.");
            return null;
        }
    }

}
