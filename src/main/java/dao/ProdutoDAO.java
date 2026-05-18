package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Produto;

public class ProdutoDAO {

    private ArrayList<Produto> lista = new ArrayList<>();

    public void adicionar(Produto produto) {
        lista.add(produto);
    }

    public ArrayList<Produto> listar() {
        return lista;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : lista) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public boolean atualizar(String nome, Produto novoProduto) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
                lista.set(i, novoProduto);
                return true;
            }
        }
        return false;
    }

    public boolean remover(String nome) {
        Produto p = buscarPorNome(nome);
        if (p != null) {
            lista.remove(p);
            return true;
        }
        return false;
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
