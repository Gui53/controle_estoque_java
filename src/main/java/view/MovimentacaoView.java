package view;

import dao.MovimentacaoDAO;
import dao.ProdutoDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Movimentacao;
import model.Produto;
import service.MovimentacaoService;

public class MovimentacaoView extends JFrame {

    // ── campos da classe (ficam AQUI, fora de qualquer método) ──
    private JComboBox<String> cbProduto;
    private JTextField txtQuantidade;
    private JComboBox<String> cbTipo;
    private JButton btnMovimentar;
    private JTable tblMovimentacoes;

    private ProdutoDAO produtoDAO = new ProdutoDAO();
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private MovimentacaoService service = new MovimentacaoService();
    private ArrayList<Produto> listaProdutos = new ArrayList<>();

    // ── construtor ──────────────────────────────────────────────
    public MovimentacaoView() {
        setTitle("Movimentações de Estoque");
        setSize(750, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(criarHeader(), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.CENTER);
        add(criarPainelTabela(), BorderLayout.SOUTH);

        carregarProdutos();
        carregarTabela();
    }

    // ── cabeçalho ───────────────────────────────────────────────
    private JPanel criarHeader() {
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(45, 53, 97));
        pnlHeader.setPreferredSize(new Dimension(getWidth(), 50));
        pnlHeader.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 12));

        JLabel lblTitulo = new JLabel("🔄 Sistema de Controle de Estoque — Movimentações");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
        pnlHeader.add(lblTitulo);

        return pnlHeader;
    }

    private JPanel criarFormulario() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(new Color(240, 236, 228));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fontLabel = new Font("SansSerif", Font.BOLD, 13);
        Font fontCampo = new Font("SansSerif", Font.PLAIN, 13);

        // linha 0 – label Produto
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        JLabel lblProduto = new JLabel("Produto:");
        lblProduto.setFont(fontLabel);
        pnl.add(lblProduto, gbc);

        // linha 1 – combobox de produtos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        cbProduto = new JComboBox<>();
        cbProduto.setFont(fontCampo);
        cbProduto.setPreferredSize(new Dimension(400, 32));
        pnl.add(cbProduto, gbc);

        // linha 2 – labels Quantidade e Tipo
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblQtd = new JLabel("Quantidade:");
        lblQtd.setFont(fontLabel);
        pnl.add(lblQtd, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setFont(fontLabel);
        pnl.add(lblTipo, gbc);

        // linha 3 – campo quantidade e combobox tipo
        gbc.gridx = 0;
        gbc.gridy = 3;
        txtQuantidade = new JTextField();
        txtQuantidade.setFont(fontCampo);
        txtQuantidade.setPreferredSize(new Dimension(180, 32));
        pnl.add(txtQuantidade, gbc);

        gbc.gridx = 2;
        gbc.gridy = 3;
        cbTipo = new JComboBox<>(new String[]{"ENTRADA", "SAIDA"});
        cbTipo.setFont(fontCampo);
        cbTipo.setSelectedIndex(-1);
        pnl.add(cbTipo, gbc);

        // linha 4 – botão
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnMovimentar = new JButton("✅ Movimentar");
        estilizarBotao(btnMovimentar, new Color(45, 158, 95));
        btnMovimentar.setPreferredSize(new Dimension(200, 38));
        btnMovimentar.addActionListener(e -> realizarMovimentacao());
        pnl.add(btnMovimentar, gbc);

        return pnl;
    }

    private JPanel criarPainelTabela() {
        JPanel pnlTabela = new JPanel(new BorderLayout());
        pnlTabela.setBackground(new Color(240, 236, 228));
        pnlTabela.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        JLabel lblHistorico = new JLabel("📋 Histórico de Movimentações");
        lblHistorico.setFont(new Font("SansSerif", Font.BOLD, 13));
        lblHistorico.setBorder(BorderFactory.createEmptyBorder(10, 0, 6, 0));
        pnlTabela.add(lblHistorico, BorderLayout.NORTH);

        tblMovimentacoes = new JTable();
        estilizarTabela(tblMovimentacoes);

        JScrollPane scroll = new JScrollPane(tblMovimentacoes);
        scroll.setPreferredSize(new Dimension(700, 220));
        pnlTabela.add(scroll, BorderLayout.CENTER);

        return pnlTabela;
    }

    private void carregarProdutos() {
        listaProdutos = produtoDAO.select();
        cbProduto.removeAllItems();
        for (Produto p : listaProdutos) {
            cbProduto.addItem(p.getNome() + "  [estoque: " + p.getQuantidade() + "]");
        }
        cbProduto.setSelectedIndex(-1);
    }
    
    private void carregarTabela() {
        String[] colunas = {"ID", "Data", "Produto", "Quantidade", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        for (Movimentacao m : movimentacaoDAO.select()) {
            model.addRow(new Object[]{
                m.getId(),
                m.getDataFormatada(),
                m.getProduto().getNome(),
                m.getQuantidade(),
                m.getTipo()
            });
        }
        tblMovimentacoes.setModel(model);
    }
    
    private void realizarMovimentacao() {
        if (cbProduto.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um produto.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (cbTipo.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Selecione ENTRADA ou SAIDA.",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double quantidade;
        try {
            quantidade = Double.parseDouble(
                    txtQuantidade.getText().replace(",", ".").trim());
            if (quantidade <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Informe uma quantidade válida (maior que zero).",
                    "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Produto produto = listaProdutos.get(cbProduto.getSelectedIndex());
        String tipo = cbTipo.getSelectedItem().toString();

        if (tipo.equals("ENTRADA")) {
            service.entradaProduto(produto, quantidade);
            JOptionPane.showMessageDialog(this,
                    "Entrada de " + quantidade + " unidade(s) registrada!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        } else {
            if (produto.getQuantidade() < quantidade) {
                JOptionPane.showMessageDialog(this,
                        "Estoque insuficiente!\nDisponível: " + produto.getQuantidade(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            service.saidaProduto(produto, quantidade);
            JOptionPane.showMessageDialog(this,
                    "Saída de " + quantidade + " unidade(s) registrada!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        }
        
        if(produto.getQuantidade() <= produto.getMinimo()){
            JOptionPane.showMessageDialog(this, "Quantidade abaixo do mínimo. \n"
                    + "MÍNIMO: " + produto.getMinimo());
        }
        if(produto.getQuantidade() > produto.getMaximo()){
            JOptionPane.showMessageDialog(this, "Quantidade acima do máximo! \n"
                    + "MÁXIMO: " + produto.getMaximo());
        }

        carregarProdutos();
        carregarTabela();
        txtQuantidade.setText("");
        cbProduto.setSelectedIndex(-1);
        cbTipo.setSelectedIndex(-1);
    }

    private void estilizarBotao(JButton btn, Color cor) {
        btn.setBackground(cor);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void estilizarTabela(JTable tabela) {
        tabela.getTableHeader().setBackground(new Color(45, 53, 97));
        tabela.getTableHeader().setForeground(Color.WHITE);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));
        tabela.setRowHeight(28);
        tabela.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabela.setGridColor(new Color(220, 215, 205));
        tabela.setSelectionBackground(new Color(208, 232, 255));
        tabela.setSelectionForeground(new Color(30, 30, 30));
    }
}
