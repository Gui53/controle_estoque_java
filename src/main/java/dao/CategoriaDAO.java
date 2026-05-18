package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.Categoria;

public class CategoriaDAO {

    private ArrayList<Categoria> lista = new ArrayList<>();

    public void adicionar(Categoria categoria) {
        lista.add(categoria);
    }

    public ArrayList<Categoria> listar() {
        return lista;
    }

    public Categoria buscarPorNome(String nome) {
        for (Categoria c : lista) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    public boolean atualizar(String nome, Categoria novaCategoria) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
                lista.set(i, novaCategoria);
                return true;
            }
        }
        return false;
    }

    public boolean remover(String nome) {
        Categoria c = buscarPorNome(nome);

        if (c != null) {
            lista.remove(c);
            return true;
        }

        return false;
    }

    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_alunos");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }

   public boolean insertCategoria(Categoria objeto) {
        String sql = "INSERT INTO tb_alunos(id,nome,idade,curso,fase) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

           /* stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setInt(3, objeto.getEmbalagem());
            stmt.setString(4, objeto.getTamanho());
 */

            stmt.execute();
            stmt.close();

            return true;
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
            throw new RuntimeException(erro);
        }
    }

    public Connection getConexao() {
        Connection connection = null;
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            String server = "localhost";
            String database = "db_controledeestoque";
            String url = "jdbc:mysql://" + server + ":3306/"
                    + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "SUA SENHA";
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }
            return connection;
        } catch (ClassNotFoundException e) { //Driver não encontrado
            System.out.println("O driver nao foi encontrado.");
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        }

    }

}
