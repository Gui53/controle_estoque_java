package dao;

import connection.Conexao;
import java.util.ArrayList;
import model.Movimentacao;
import enums.TipoMovimentacao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import model.Produto;

public class MovimentacaoDAO {

    private ArrayList<Movimentacao> lista = new ArrayList<>();

    public boolean insert(Movimentacao m) {
        String sql = "INSERT INTO tb_movimentacao(data_movimentacao, quantidade_movimentada, tipo_movimentacao, tb_produto_id) VALUES(?,?,?,?) ";

        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setDate(1, java.sql.Date.valueOf(m.getData()));
            stmt.setInt(2, m.getQuantidade());
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
                int quantidade = res.getInt("quantidade_movimentada");
                TipoMovimentacao tipoMovimentacao = TipoMovimentacao.valueOf(res.getString("tipo_movimentacao"));
                int produtoId = res.getInt("tb_produto_id");

                ProdutoDAO dao = new ProdutoDAO();

                Produto p = dao.selectById(produtoId);

                Movimentacao obj = new Movimentacao(id, p, data, quantidade, tipoMovimentacao);
                
                lista.add(obj);
            }

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        return lista;
    }

    public int totalEntradas() {
        int total = 0;

        for (int i = 0; i < lista.size(); i++) {
            Movimentacao m = lista.get(i);

            if (m.getTipo() == TipoMovimentacao.ENTRADA) {
                total += m.getQuantidade();
            }
        }
        return total;
    }

    public int totalSaidas() {
        int total = 0;

        for (int i = 0; i < lista.size(); i++) {
            Movimentacao m = lista.get(i);

            if (m.getTipo() == TipoMovimentacao.SAIDA) {
                total += m.getQuantidade();
            }
        }
        return total;
    }
}
