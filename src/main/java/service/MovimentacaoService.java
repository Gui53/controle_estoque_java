/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import enums.TipoMovimentacao;
import java.time.LocalDate;
import model.Movimentacao;
import model.Produto;

/**
 *
 * @author guilherme
 */
public class MovimentacaoService {

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();

    public void entradaProduto(Produto produto, /*deve ser mudado para double*/ int quantidade) {

        try {
            produto.adicionar(quantidade);

            produtoDAO.updateQuantidade(produto);
            Movimentacao movimentacao = new Movimentacao(produto, LocalDate.now(), quantidade, TipoMovimentacao.ENTRADA);

            movimentacaoDAO.insert(movimentacao);

            System.out.println("Entrada concluída!");
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void saidaProduto(Produto produto, /*deve ser mudado para double*/ int quantidade) {
        try {
            boolean removeu = produto.remover(quantidade);

            if (!removeu) {
                System.out.println("Estoque insuficiente!");
                return;
            }

            produtoDAO.updateQuantidade(produto);
            Movimentacao movimentacao = new Movimentacao( produto, LocalDate.now(), quantidade, TipoMovimentacao.SAIDA);

            movimentacaoDAO.insert(movimentacao);

            System.out.println("Saida Realizada!");

        } catch (RuntimeException e) {
            System.out.println("Erro: " + e);

        }
    }
}
