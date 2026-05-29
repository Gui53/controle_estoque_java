package dao;

import conexao.Conexao;
import java.util.ArrayList;
import model.Movimentacao;
import enums.TipoMovimentacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import model.Produto;

/**
 * Classe responsável pelas operações de banco de dados
 * relacionadas às movimentações.
 * 
 * @author Guilherme
 */
public class MovimentacaoDAO {

    /**
     * Insere uma movimentação no banco de dados.
     * 
     * @param m Movimentação a ser cadastrada
     * @return boolean Retorna true caso a inserção seja realizada
     */
    public boolean insert(Movimentacao m) {

        String sql = "INSERT INTO tb_movimentacao(data_movimentacao, quantidade_movimentada, tipo_movimentacao, tb_produto_id) VALUES(?,?,?,?) ";

        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

            stmt.setDate(1, java.sql.Date.valueOf(m.getData()));
            stmt.setDouble(2, m.getQuantidade());
            stmt.setString(3, m.getTipo().name());
            stmt.setInt(4, m.getProduto().getId());

            stmt.execute();
            stmt.close();

            System.out.println("MOVIMENTAÇÃO CADASTRADA!");

            return true;

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Recupera todas as movimentações cadastradas.
     * 
     * @return ArrayList Lista de movimentações
     */
    public ArrayList<Movimentacao> select() {

        ArrayList<Movimentacao> lista = new ArrayList<>();

        lista.clear();

        try {

            String sql = "SELECT * FROM tb_movimentacao";

            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {

                int id = res.getInt("id");
                LocalDate data = res.getDate("data_movimentacao").toLocalDate();
                double quantidade = res.getDouble("quantidade_movimentada");

                TipoMovimentacao tipoMovimentacao =
                        TipoMovimentacao.valueOf(res.getString("tipo_movimentacao"));

                int produtoId = res.getInt("tb_produto_id");

                ProdutoDAO dao = new ProdutoDAO();

                Produto p = dao.selectById(produtoId);

                Movimentacao obj =
                        new Movimentacao(id, p, data, quantidade, tipoMovimentacao);

                lista.add(obj);
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }

        return lista;
    }

    /**
     * Calcula o total de entradas de um produto.
     * 
     * @param produtoId ID do produto
     * @return double Total de entradas
     */
    public double totalEntradas(int produtoId) {

        double total = 0;

        String sql = """
                     SELECT SUM(quantidade_movimentada) AS total
                     FROM tb_movimentacao 
                     WHERE tipo_movimentacao = 'ENTRADA'
                     AND tb_produto_id = ?
                     """;

        try {

            PreparedStatement stmt =
                    Conexao.getConexao().prepareStatement(sql);

            stmt.setInt(1, produtoId);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                total = res.getDouble("total");
            }

            res.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        return total;
    }

    /**
     * Calcula o total de saídas de um produto.
     * 
     * @param produtoId ID do produto
     * @return double Total de saídas
     */
    public double totalSaidas(int produtoId) {

        double total = 0;

        String sql = """
                     SELECT SUM(quantidade_movimentada) AS total
                     FROM tb_movimentacao 
                     WHERE tipo_movimentacao = 'SAIDA'
                     AND tb_produto_id = ?
                     """;

        try {

            PreparedStatement stmt =
                    Conexao.getConexao().prepareStatement(sql);

            stmt.setInt(1, produtoId);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                total = res.getDouble("total");
            }

            res.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        return total;
    }
}