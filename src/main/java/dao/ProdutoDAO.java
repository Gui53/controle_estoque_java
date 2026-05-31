package dao;

import conexao.Conexao;
import enums.TipoUnidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Categoria;
import modelo.Produto;

/**
 * Classe responsável pelas operações de banco de dados relacionadas aos
 * produtos.
 *
 * @author Gabriel Conci
 * @see java.sql.Connection
 */
public class ProdutoDAO {

    /**
     * Insere um produto no banco de dados.
     *
     * @param produto Produto a ser cadastrado
     * @return boolean Retorna true caso a inserção seja realizada
     */
    public boolean inserir(Produto produto) {
        String sql = """
                INSERT INTO tb_produto(nome, preco_unitario, unidade, quantidade_estoque, quantidade_minima, quantidade_maxima, tb_categoria_id)
                VALUES(?, ?, ?, ?, ?, ?, ?)
                """;

        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setString(3, produto.getUnidade().name());
            stmt.setDouble(4, produto.getQuantidade());
            stmt.setDouble(5, produto.getMinimo());
            stmt.setDouble(6, produto.getMaximo());
            stmt.setInt(7, produto.getCategoria().getId());

            stmt.execute();
            stmt.close();

            System.out.println("PRODUTO CADASTRADO!");

            return true;

        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            throw new RuntimeException(erro);
        }
    }

    /**
     * Recupera todos os produtos cadastrados no banco de dados.
     *
     * @return ArrayList Lista de produtos
     */
    public ArrayList<Produto> visualizar() {
        ArrayList<Produto> lista = new ArrayList<>();

        try {
            Statement stmt = Conexao.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_produto");

            while (res.next()) {
                lista.add(montarProduto(res));
            }

            res.close();
            stmt.close();

        } catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }

        return lista;
    }

    /**
     * Remove um produto do banco de dados pelo seu identificador.
     *
     * @param id Identificador do produto a ser removido
     * @return boolean Retorna true caso a remoção seja realizada
     */
    public boolean apagar(int id) {
        String sql = "DELETE FROM tb_produto WHERE id = ?";

        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            System.out.println("Erro: " + erro);
            return false;
        }
    }

    /**
     * Monta um objeto Produto a partir do resultado de uma query.
     *
     * @param res ResultSet com os dados do produto
     * @return Produto Objeto montado com os dados do banco
     * @throws SQLException Caso ocorra erro na leitura do ResultSet
     */
    private Produto montarProduto(ResultSet res) throws SQLException {
        Produto produto = new Produto();

        produto.setId(res.getInt("id"));
        produto.setNome(res.getString("nome"));
        produto.setPreco(res.getDouble("preco_unitario"));
        produto.setUnidade(TipoUnidade.valueOf(res.getString("unidade")));
        produto.setQuantidade(res.getDouble("quantidade_estoque"));
        produto.setMinimo(res.getDouble("quantidade_minima"));
        produto.setMaximo(res.getDouble("quantidade_maxima"));

        int categoriaId = res.getInt("tb_categoria_id");
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        Categoria categoria = categoriaDAO.selecionarPorId(categoriaId);
        produto.setCategoria(categoria);

        return produto;
    }

    /**
     * Recupera um produto pelo seu identificador.
     *
     * @param id Identificador do produto
     * @return Produto Produto encontrado ou objeto vazio caso não exista
     */
    public Produto selecionarPorId(int id) {
        Produto produto = new Produto();

        try {
            String sql = "SELECT * FROM tb_produto WHERE id = ?";
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();

            if (res.next()) {
                produto = montarProduto(res);
            }

            res.close();
            stmt.close();

        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }

        return produto;
    }

    /**
     * Atualiza os dados de um produto no banco de dados.
     *
     * @param produto Produto com os dados atualizados
     * @return boolean Retorna true caso a atualização seja realizada
     */
    public boolean atualizar(Produto produto) {

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
            stmt.setDouble(5, produto.getMinimo());
            stmt.setDouble(6, produto.getMaximo());
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

    /**
     * Atualiza apenas a quantidade em estoque de um produto.
     *
     * @param produto Produto com a quantidade atualizada
     * @return boolean Retorna true caso a atualização seja realizada
     */
    public boolean atualizarQuantidade(Produto produto) {
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

    /**
     * Reajusta o preço de todos os produtos cadastrados de acordo com o
     * percentual informado.
     *
     * @param percentual Percentual de reajuste a ser aplicado
     * @return boolean Retorna true caso o reajuste seja realizado
     */
    public boolean reajustarPrecos(double percentual) {
        String sql = "UPDATE tb_produto SET preco_unitario = preco_unitario * (1 + ? / 100)";

        try {
            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);
            stmt.setDouble(1, percentual);
            stmt.executeUpdate();
            stmt.close();

            System.out.println("Preços reajustados em " + percentual + "%");
            return true;

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
            throw new RuntimeException(e);
        }
    }
}
