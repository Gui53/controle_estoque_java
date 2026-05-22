package dao;

import connection.Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Produto selectById(int id) {
        Produto produto = new Produto();

        try {
            String sql = "SELECT * FROM tb_produto WHERE id = ?";

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                produto.setId(res.getInt("id"));

                produto.setNome(res.getString("nome"));
            }

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return produto;
    }

    public Produto buscarPorNome(String nome) {
        for (Produto p : lista) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }

    public boolean update(Produto produto) {

        String sql = """
        UPDATE tb_produto
        SET nome = ?,
            preco_unitario = ?,
            unidade = ?,
            quantidade_estoque = ?,
            quantidade_minima = ?,
            quantidade_maxima = ?,
            tb_categoria_id = ?
        WHERE id = ?
        """;

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getUnidade().name());

            stmt.setDouble(4, produto.getQuantidade());
            stmt.setInt(5, produto.getMinimo());
            stmt.setInt(6, produto.getMaximo());

            stmt.setInt(7, produto.getCategoria().getId());

            stmt.setInt(8, produto.getId());

            stmt.executeUpdate();

            stmt.close();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro: " + e);

            throw new RuntimeException(e);
        }
    }

    public boolean updateQuantidade(Produto produto) {

        String sql = """
        UPDATE tb_produto
        SET quantidade_estoque = ?
        WHERE id = ?
        """;

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

            stmt.setDouble(1, produto.getQuantidade());
            stmt.setInt(2, produto.getId());

            stmt.executeUpdate();

            stmt.close();

            return true;

        } catch (SQLException e) {

            System.out.println("Erro: " + e);

            throw new RuntimeException(e);
        }
    }

    public boolean remover(String nome) {
        Produto p = buscarPorNome(nome);
        if (p != null) {
            lista.remove(p);
            return true;
        }
        return false;
    }
}
