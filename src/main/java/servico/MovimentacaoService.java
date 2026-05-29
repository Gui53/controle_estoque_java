package servico;

import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import enums.TipoMovimentacao;
import java.time.LocalDate;
import modelo.Movimentacao;
import modelo.Produto;

/**
 * Classe responsável pelas regras de negócio
 * relacionadas às movimentações de estoque.
 * 
 * @author Guilherme
 */
public class MovimentacaoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();

    /**
     * Realiza a entrada de produtos no estoque.
     * 
     * @param produto Produto movimentado
     * @param quantidade Quantidade adicionada ao estoque
     */
    public void entradaProduto(Produto produto, double quantidade) {

        try {

            produto.adicionar(quantidade);

            produtoDAO.updateQuantidade(produto);

            Movimentacao movimentacao =
                    new Movimentacao(
                            produto,
                            LocalDate.now(),
                            quantidade,
                            TipoMovimentacao.ENTRADA
                    );

            movimentacaoDAO.insert(movimentacao);

            System.out.println("Entrada concluída!");

        } catch (RuntimeException e) {

            System.out.println("Erro: " + e);
        }
    }

    /**
     * Realiza a saída de produtos do estoque.
     * 
     * @param produto Produto movimentado
     * @param quantidade Quantidade removida do estoque
     */
    public void saidaProduto(Produto produto, double quantidade) {

        try {

            boolean removeu = produto.remover(quantidade);

            if (!removeu) {
                System.out.println("Estoque insuficiente!");
                return;
            }

            produtoDAO.updateQuantidade(produto);

            Movimentacao movimentacao =
                    new Movimentacao(
                            produto,
                            LocalDate.now(),
                            quantidade,
                            TipoMovimentacao.SAIDA
                    );

            movimentacaoDAO.insert(movimentacao);

            System.out.println("Saida Realizada!");

        } catch (RuntimeException e) {

            System.out.println("Erro: " + e);
        }
    }
}