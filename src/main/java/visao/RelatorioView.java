package visao;

import dao.RelatorioDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.Produto;

public class RelatorioView extends JFrame {

    private final RelatorioDAO dao = new RelatorioDAO();
    private JTable tabela;
    private JLabel lblTotalEstoque;

    public RelatorioView() {
        setTitle("Relatórios");
        setSize(800, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(criarHeader(), BorderLayout.NORTH);
        add(criarPainelBotoes(), BorderLayout.WEST);
        add(criarPainelTabela(), BorderLayout.CENTER);
    }

    private JPanel criarHeader() {
        JPanel pnl = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 12));
        pnl.setBackground(new Color(45, 53, 97));
        pnl.setPreferredSize(new Dimension(getWidth(), 50));

        JLabel lbl = new JLabel("📊 Sistema de Controle de Estoque — Relatórios");
        lbl.setForeground(Color.WHITE);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 15));
        pnl.add(lbl);
        return pnl;
    }

    private JPanel criarPainelBotoes() {
        JPanel pnl = new JPanel(new java.awt.GridLayout(6, 1, 8, 8));
        pnl.setBackground(new Color(45, 53, 97));
        pnl.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
        pnl.setPreferredSize(new Dimension(210, getHeight()));

        JButton btnPrecos       = criarBotao("📋 Lista de Preços");
        JButton btnBalanco      = criarBotao("💰 Balanço Físico/Financeiro");
        JButton btnMinimo       = criarBotao("⚠️ Abaixo do Mínimo");
        JButton btnCategoria    = criarBotao("🏷️ Produtos por Categoria");
        JButton btnEntradaSaida = criarBotao("🔄 Maior Entrada/Saída");
        JButton btnFechar       = criarBotao("🚪 Fechar");
        btnFechar.setBackground(new Color(192, 57, 43));

        pnl.add(btnPrecos);
        pnl.add(btnBalanco);
        pnl.add(btnMinimo);
        pnl.add(btnCategoria);
        pnl.add(btnEntradaSaida);
        pnl.add(btnFechar);

        btnPrecos.addActionListener(e -> mostrarListaPrecos());
        btnBalanco.addActionListener(e -> mostrarBalanco());
        btnMinimo.addActionListener(e -> mostrarAbaixoMinimo());
        btnCategoria.addActionListener(e -> mostrarPorCategoria());
        btnEntradaSaida.addActionListener(e -> mostrarMaiorEntradaSaida());
        btnFechar.addActionListener(e -> dispose());

        return pnl;
    }

    private JPanel criarPainelTabela() {
        JPanel pnl = new JPanel(new BorderLayout());
        pnl.setBackground(new Color(240, 236, 228));
        pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        tabela = new JTable();
        estilizarTabela(tabela);

        JScrollPane scroll = new JScrollPane(tabela);
        pnl.add(scroll, BorderLayout.CENTER);

        lblTotalEstoque = new JLabel(" ");
        lblTotalEstoque.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblTotalEstoque.setForeground(new Color(45, 53, 97));
        lblTotalEstoque.setBorder(BorderFactory.createEmptyBorder(8, 4, 4, 4));
        pnl.add(lblTotalEstoque, BorderLayout.SOUTH);

        return pnl;
    }

    private void mostrarListaPrecos() {
        String[] colunas = {"Nome", "Preço Unitário (R$)", "Unidade", "Categoria"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Produto p : dao.listaDePrecos()) {
            model.addRow(new Object[]{
                p.getNome(),
                String.format("%.2f", p.getPreco()),
                p.getUnidade(),
                p.getCategoria() != null ? p.getCategoria().getNome() : "-"
            });
        }

        tabela.setModel(model);
        lblTotalEstoque.setText(" ");
    }

    private void mostrarBalanco() {
        String[] colunas = {"Nome", "Qtd. em Estoque", "Preço Unitário (R$)", "Total (R$)"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Object[] linha : dao.balanco()) {
            model.addRow(new Object[]{
                linha[0],
                linha[1],
                String.format("%.2f", linha[2]),
                String.format("%.2f", linha[3])
            });
        }

        tabela.setModel(model);
        lblTotalEstoque.setText("💰 Valor total do estoque: R$ " +
                String.format("%.2f", dao.valorTotalEstoque()));
    }

    private void mostrarAbaixoMinimo() {
        String[] colunas = {"Nome", "Qtd. em Estoque", "Qtd. Mínima"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        ArrayList<Produto> lista = dao.produtosAbaixoMinimo();

        for (Produto p : lista) {
            model.addRow(new Object[]{
                p.getNome(),
                p.getQuantidade(),
                p.getMinimo()
            });
        }

        tabela.setModel(model);

        if (lista.isEmpty()) {
            lblTotalEstoque.setText("✅ Nenhum produto abaixo do mínimo.");
        } else {
            lblTotalEstoque.setText("⚠️ " + lista.size() + " produto(s) abaixo do mínimo.");
        }
    }

    private void mostrarPorCategoria() {
        String[] colunas = {"Categoria", "Qtd. de Produtos"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        for (Object[] linha : dao.produtosPorCategoria()) {
            model.addRow(linha);
        }

        tabela.setModel(model);
        lblTotalEstoque.setText(" ");
    }

    private void mostrarMaiorEntradaSaida() {
        Object[] resultado = dao.produtoMaiorEntradaSaida();

        if (resultado[0] == null && resultado[2] == null) {
            JOptionPane.showMessageDialog(this,
                    "Nenhuma movimentação registrada.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String[] colunas = {"Tipo", "Produto", "Total Movimentado"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        if (resultado[0] != null) {
            model.addRow(new Object[]{"🟢 Maior Entrada", resultado[0],
                String.format("%.2f", resultado[1])});
        }
        if (resultado[2] != null) {
            model.addRow(new Object[]{"🔴 Maior Saída", resultado[2],
                String.format("%.2f", resultado[3])});
        }

        tabela.setModel(model);
        lblTotalEstoque.setText(" ");
    }

    private JButton criarBotao(String texto) {
        JButton btn = new JButton(texto);
        btn.setBackground(new Color(60, 72, 120));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void estilizarTabela(JTable t) {
        t.getTableHeader().setBackground(new Color(45, 53, 97));
        t.getTableHeader().setForeground(Color.WHITE);
        t.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        t.setRowHeight(28);
        t.setFont(new Font("SansSerif", Font.PLAIN, 13));
        t.setGridColor(new Color(220, 215, 205));
        t.setSelectionBackground(new Color(208, 232, 255));
        t.setSelectionForeground(new Color(30, 30, 30));
    }
}