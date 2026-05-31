package dao;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Produto;

public class RelatorioDAO {

    public ArrayList<Produto> listaDePrecos() {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = """
            SELECT p.id, p.nome, p.preco_unitario, p.unidade, c.nome AS categoria
            FROM tb_produto p
            JOIN tb_categoria c ON p.tb_categoria_id = c.id
            ORDER BY p.nome ASC
        """;
        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco_unitario"));
                p.setUnidade(enums.TipoUnidade.valueOf(rs.getString("unidade")));
                modelo.Categoria cat = new modelo.Categoria();
                cat.setNome(rs.getString("categoria"));
                p.setCategoria(cat);
                lista.add(p);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return lista;
    }

    public ArrayList<Object[]> balanco() {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = """
            SELECT nome, quantidade_estoque, preco_unitario,
                   (quantidade_estoque * preco_unitario) AS total
            FROM tb_produto
            ORDER BY nome ASC
        """;
        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("nome"),
                    rs.getDouble("quantidade_estoque"),
                    rs.getDouble("preco_unitario"),
                    rs.getDouble("total")
                });
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return lista;
    }

    public double valorTotalEstoque() {
        String sql = "SELECT SUM(preco_unitario * quantidade_estoque) AS total FROM tb_produto";
        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                return rs.getDouble("total");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return 0;
    }

    public ArrayList<Produto> produtosAbaixoMinimo() {
        ArrayList<Produto> lista = new ArrayList<>();
        String sql = """
            SELECT id, nome, quantidade_estoque, quantidade_minima
            FROM tb_produto
            WHERE quantidade_estoque < quantidade_minima
        """;
        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQuantidade(rs.getDouble("quantidade_estoque"));
                p.setMinimo(rs.getDouble("quantidade_minima"));
                lista.add(p);
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return lista;
    }

    public ArrayList<Object[]> produtosPorCategoria() {
        ArrayList<Object[]> lista = new ArrayList<>();
        String sql = """
            SELECT c.nome AS categoria, COUNT(p.id) AS quantidade
            FROM tb_categoria c
            LEFT JOIN tb_produto p ON p.tb_categoria_id = c.id
            GROUP BY c.nome
            ORDER BY c.nome ASC
        """;
        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                lista.add(new Object[]{
                    rs.getString("categoria"),
                    rs.getInt("quantidade")
                });
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return lista;
    }

    public Object[] produtoMaiorEntradaSaida() {
        Object[] resultado = new Object[4]; // nomeMaiorEntrada, totalEntrada, nomeMaiorSaida, totalSaida
        String sqlEntrada = """
            SELECT p.nome, SUM(m.quantidade_movimentada) AS total
            FROM tb_movimentacao m
            JOIN tb_produto p ON m.tb_produto_id = p.id
            WHERE m.tipo_movimentacao = 'ENTRADA'
            GROUP BY p.nome
            ORDER BY total DESC
            LIMIT 1
        """;
        String sqlSaida = sqlEntrada.replace("ENTRADA", "SAIDA");
        try {
            Statement stmt = Conexao.getConexao().createStatement();

            ResultSet rs = stmt.executeQuery(sqlEntrada);
            if (rs.next()) {
                resultado[0] = rs.getString("nome");
                resultado[1] = rs.getDouble("total");
            }
            rs.close();

            rs = stmt.executeQuery(sqlSaida);
            if (rs.next()) {
                resultado[2] = rs.getString("nome");
                resultado[3] = rs.getDouble("total");
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return resultado;
    }
}
