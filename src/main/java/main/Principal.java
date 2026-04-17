package main;

import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import java.time.LocalDate;
import javax.swing.JOptionPane;
import model.Movimentacao;
import model.Produto;
import model.TipoMovimentacao;

public class Principal {

    public static void main(String[] args) {
        ProdutoDAO pd = new ProdutoDAO();
        MovimentacaoDAO md = new MovimentacaoDAO();

        Produto pm = new Produto("Arroz", 30, 5, 3, 100);

        Produto pm2 = new Produto("Macarrão", 36, 5, 5, 100);
        
        Produto pm3 = new Produto("Trigo", 15, 10, 3, 100);

        pd.adicionar(pm);
        Movimentacao m = new Movimentacao(pm, LocalDate.now(), 30, TipoMovimentacao.ENTRADA);
        m.executar();
        md.adicionar(m);

        pd.adicionar(pm2);
        Movimentacao m2 = new Movimentacao(pm2, LocalDate.now(), 36, TipoMovimentacao.ENTRADA);
        m2.executar();
        md.adicionar(m2);

        pd.adicionar(pm3);
        Movimentacao m3 = new Movimentacao(pm3, LocalDate.now(), 30, TipoMovimentacao.ENTRADA);
        m3.executar();
        md.adicionar(m3);
        
        for (int i = 0; i < md.listar().size(); i++) {
            Movimentacao movi = md.listar().get(i);
            
            JOptionPane.showMessageDialog(null, " Produto: " + movi.getProduto().getNome() + 
                    "\n Data: " + movi.getDataFormatada() + 
                    "\n Quantidade: " + movi.getQuantidade() + 
                    "\n Tipo: " + movi.getTipo());
        }
    }
}
