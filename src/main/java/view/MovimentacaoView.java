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

    private ProdutoDAO produtoDAO           = new ProdutoDAO();
    private MovimentacaoDAO movimentacaoDAO = new MovimentacaoDAO();
    private MovimentacaoService service     = new MovimentacaoService();
    private ArrayList<Produto> listaProdutos = new ArrayList<>();

    // ── construtor ──────────────────────────────────────────────
    public MovimentacaoView() {
        setTitle("Movimentações de Estoque");
        setSize(750, 620);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        add(criarHeader(),       BorderLayout.NORTH);
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
}