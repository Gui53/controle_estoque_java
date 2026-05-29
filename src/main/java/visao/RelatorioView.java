package visao;

import dao.RelatorioDAO;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;

public class RelatorioView extends JFrame {

    private final RelatorioDAO dao;

    public RelatorioView() {

        dao = new RelatorioDAO();

        setTitle("Relatórios");

        setSize(400, 300);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        painel.setLayout(new GridLayout(4, 1, 10, 10));

        JButton btnMinimo =
            new JButton("Produtos abaixo do mínimo");

        JButton btnMaximo =
            new JButton("Produtos acima do máximo");

        JButton btnValor =
            new JButton("Valor total do estoque");

        JButton btnSair =
            new JButton("Fechar");

        painel.add(btnMinimo);

        painel.add(btnMaximo);

        painel.add(btnValor);

        painel.add(btnSair);

        add(painel);

        btnMinimo.addActionListener(e -> {

            dao.produtosAbaixoMinimo();

        });

        btnMaximo.addActionListener(e -> {

            dao.produtosAcimaMaximo();

        });

        btnValor.addActionListener(e -> {

            dao.valorTotalEstoque();

        });

        btnSair.addActionListener(e -> {

            dispose();

        });

        setVisible(true);
    }
}