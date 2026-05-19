package dao;

import enums.TipoEmbalagem;
import enums.TipoTamanho;
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

    public int maiorID() {
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_categoria");
            res.next();
            maiorID = res.getInt("id");
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("Erro:" + ex);
        }
        return maiorID;
    }
//TESTANDO

    public boolean insertCategoria(Categoria objeto) {
        String sql = "INSERT INTO tb_categoria(nome,tamanho,embalagem) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTamanho().name());
            stmt.setString(3, objeto.getEmbalagem().name());

            stmt.execute();
            stmt.close();

            System.out.println("CATEGORIA CADASTRADA!");

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
            String password = "123456";
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

    public boolean deleteCategoriaBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_categoria WHERE id = " + id);
            stmt.close();
        } catch (SQLException erro) {
            System.out.println("Erro:" + erro);
        }
        return true;
    }

    public boolean updateCategoriaBD(Categoria objeto) {

        String sql = "UPDATE tb_categoria SET nome = ? WHERE id = ?";

        try {

            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setInt(2, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {

            System.out.println("Erro: " + erro);

            throw new RuntimeException(erro);
        }
    }

    public ArrayList<Categoria> getLista() {

        lista.clear(); // Limpa nosso ArrayList

        try {

            Statement stmt = this.getConexao().createStatement();

            ResultSet res = stmt.executeQuery("SELECT * FROM tb_categoria");

            while (res.next()) {

                int id = res.getInt("id");
                String nome = res.getString("nome");

                TipoEmbalagem tipoEmbalagem
                        = TipoEmbalagem.valueOf(res.getString("tipo_embalagem"));

                TipoTamanho tipoTamanho
                        = TipoTamanho.valueOf(res.getString("tipo_tamanho"));

                Categoria objeto = new Categoria(
                        id,
                        nome,
                        tipoTamanho,
                        tipoEmbalagem
                        
                );

                lista.add(objeto);
            }

            res.close();
            stmt.close();

        } catch (SQLException ex) {

            System.out.println("Erro: " + ex);
        }

        return lista;
    }
}
