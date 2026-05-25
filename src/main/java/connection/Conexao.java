package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static Connection instancia = null;

    // Inicializa a conexão com usuário e senha fornecidos pelo usuário
    public static boolean inicializar(String user, String password) {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "localhost";
            String database = "db_controledeestoque";

            String url = "jdbc:mysql://" + server + ":3306/"
                    + database + "?useTimezone=true&serverTimezone=UTC";

            instancia = DriverManager.getConnection(url, user, password);

            if (instancia != null) {
                System.out.println("Status: Conectado!");
                return true;
            }

            return false;

        } catch (ClassNotFoundException e) {
            System.out.println("Driver não encontrado.");
            return false;

        } catch (SQLException e) {
            System.out.println("Erro de conexão.");
            return false;
        }
    }

    // Retorna a conexão já inicializada
    public static Connection getConexao() {
        return instancia;
    }
}
