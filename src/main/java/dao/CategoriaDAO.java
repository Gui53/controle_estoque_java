package dao;

import conexao.Conexao;
import enums.TipoEmbalagem;
import enums.TipoTamanho;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.Categoria;

/**
 * Classe responsável pelas operações de banco de dados relacionadas às
 * categorias.
 *
 * @author Gabriel Conci
 * @see java.sql.Connection
 */
public class CategoriaDAO {

    /**
     * Insere uma categoria no banco de dados.
     *
     * @param objeto Categoria a ser cadastrada
     * @return boolean Retorna true caso a inserção seja realizada
     */
    public boolean inserir(Categoria objeto) {

        String sql = "INSERT INTO tb_categoria(nome,tamanho,embalagem) VALUES(?,?,?)";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

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

    /**
     * Recupera todas as categorias cadastradas no banco de dados.
     *
     * @return ArrayList Lista de categorias
     */
    public ArrayList<Categoria> visualizar() {

        ArrayList<Categoria> lista = new ArrayList<>();

        lista.clear();

        try {

            Statement stmt = Conexao.getConexao().createStatement();

            ResultSet res = stmt.executeQuery(
                    "SELECT * FROM tb_categoria"
            );

            while (res.next()) {

                lista.add(montarCategoria(res));
            }

            res.close();
            stmt.close();

        } catch (SQLException ex) {

            System.out.println("Erro: " + ex);
        }

        return lista;
    }

    /**
     * Recupera uma categoria pelo seu identificador.
     *
     * @param id Identificador da categoria
     * @return Categoria Categoria encontrada ou null caso não exista
     */
    public Categoria selecionarPorId(int id) {

        String sql = "SELECT * FROM tb_categoria WHERE id = ?";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

            stmt.setInt(1, id);

            ResultSet res = stmt.executeQuery();

            if (res.next()) {

                Categoria objeto = montarCategoria(res);

                res.close();
                stmt.close();

                return objeto;
            }

            res.close();
            stmt.close();

        } catch (SQLException erro) {

            System.out.println("Erro: " + erro);

            throw new RuntimeException(erro);
        }

        return null;
    }

    /**
     * Atualiza os dados de uma categoria no banco de dados.
     *
     * @param objeto Categoria com os dados atualizados
     * @return boolean Retorna true caso a atualização seja realizada
     */
    public boolean atualizar(Categoria objeto) {

        String sql = "UPDATE tb_categoria SET nome = ?, tamanho = ?, embalagem = ? WHERE id = ?";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTamanho().name());
            stmt.setString(3, objeto.getEmbalagem().name());
            stmt.setInt(4, objeto.getId());

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {

            System.out.println("Erro: " + erro);

            throw new RuntimeException(erro);
        }
    }

    /**
     * Remove uma categoria do banco de dados pelo seu identificador.
     *
     * @param id Identificador da categoria a ser removida
     * @return boolean Retorna true caso a remoção seja realizada
     */
    public boolean apagar(int id) {

        String sql = "DELETE FROM tb_categoria WHERE id = ?";

        try {

            PreparedStatement stmt = Conexao.getConexao().prepareStatement(sql);

            stmt.setInt(1, id);

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException erro) {

            System.out.println("Erro: " + erro);

            return false;
        }

        return true;
    }

    /**
     * Monta um objeto Categoria a partir do resultado de uma query.
     *
     * @param res ResultSet com os dados da categoria
     * @return Categoria Objeto montado com os dados do banco
     * @throws SQLException Caso ocorra erro na leitura do ResultSet
     */
    private Categoria montarCategoria(ResultSet res) throws SQLException {

        int id = res.getInt("id");

        String nome = res.getString("nome");

        TipoEmbalagem tipoEmbalagem
                = TipoEmbalagem.valueOf(res.getString("embalagem"));

        TipoTamanho tipoTamanho
                = TipoTamanho.valueOf(res.getString("tamanho"));

        return new Categoria(
                id,
                nome,
                tipoTamanho,
                tipoEmbalagem
        );
    }
}
