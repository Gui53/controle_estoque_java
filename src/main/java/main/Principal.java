package main;

import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.Movimentacao;
import model.Produto;
import enums.TipoMovimentacao;

public class Principal {

    public static void main(String[] args) {
        ProdutoDAO DAO = new ProdutoDAO();
        DAO.getConexao();
    }
}
