package view;

import connection.Conexao;
import connection.DatabaseSetup;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginView extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtSenha;
    private JButton btnConectar;
    private JLabel lblStatus;

    public LoginView() {
        setTitle("Conexão com o Banco de Dados");
        setSize(420, 340);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        add(criarHeader(), BorderLayout.NORTH);
        add(criarFormulario(), BorderLayout.CENTER);
    }

    private JPanel criarHeader() {
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(45, 53, 97));
        pnlHeader.setPreferredSize(new Dimension(getWidth(), 70));
        pnlHeader.setLayout(new GridBagLayout());

        JLabel lblTitulo = new JLabel("📦 Sistema de Controle de Estoque");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));

        JLabel lblSub = new JLabel("Informe as credenciais do banco de dados");
        lblSub.setForeground(new Color(180, 190, 210));
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 11));

        JPanel pnlTextos = new JPanel();
        pnlTextos.setOpaque(false);
        pnlTextos.setLayout(new java.awt.GridLayout(2, 1));
        pnlTextos.add(lblTitulo);
        pnlTextos.add(lblSub);

        pnlHeader.add(pnlTextos);
        return pnlHeader;
    }

    private JPanel criarFormulario() {
        JPanel pnl = new JPanel(new GridBagLayout());
        pnl.setBackground(new Color(240, 236, 228));
        pnl.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        Font fontLabel = new Font("SansSerif", Font.BOLD, 12);
        Font fontCampo = new Font("SansSerif", Font.PLAIN, 13);

        // Usuário
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblUsuario = new JLabel("Usuário:");
        lblUsuario.setFont(fontLabel);
        pnl.add(lblUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        txtUsuario = new JTextField("root");
        txtUsuario.setFont(fontCampo);
        txtUsuario.setPreferredSize(new Dimension(300, 34));
        pnl.add(txtUsuario, gbc);

        // Senha
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(fontLabel);
        pnl.add(lblSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        txtSenha = new JPasswordField();
        txtSenha.setFont(fontCampo);
        txtSenha.setPreferredSize(new Dimension(300, 34));
        // Conecta ao pressionar Enter
        txtSenha.addActionListener(e -> conectar());
        pnl.add(txtSenha, gbc);

        // Status
        gbc.gridx = 0;
        gbc.gridy = 4;
        lblStatus = new JLabel(" ");
        lblStatus.setFont(new Font("SansSerif", Font.BOLD, 12));
        lblStatus.setForeground(new Color(192, 57, 43));
        lblStatus.setHorizontalAlignment(JLabel.CENTER);
        pnl.add(lblStatus, gbc);

        // Botão
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        btnConectar = new JButton("🔌 Conectar");
        btnConectar.setBackground(new Color(45, 158, 95));
        btnConectar.setForeground(Color.WHITE);
        btnConectar.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnConectar.setFocusPainted(false);
        btnConectar.setBorderPainted(false);
        btnConectar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnConectar.setPreferredSize(new Dimension(180, 38));
        btnConectar.addActionListener(e -> conectar());
        pnl.add(btnConectar, gbc);

        return pnl;
    }

    private void conectar() {
        String usuario = txtUsuario.getText().trim();
        String senha = new String(txtSenha.getPassword());

        if (usuario.isEmpty()) {
            lblStatus.setText("⚠ Informe o usuário.");
            return;
        }

        btnConectar.setEnabled(false);
        lblStatus.setForeground(new Color(45, 107, 191));
        lblStatus.setText("Conectando...");

        // Cria o banco e tabelas se não existirem
        DatabaseSetup.inicializar(usuario, senha);

        boolean conectado = Conexao.inicializar(usuario, senha);

        if (conectado) {
            lblStatus.setForeground(new Color(45, 158, 95));
            lblStatus.setText("✅ Conectado com sucesso!");
            new PrincipalView().setVisible(true);
            dispose();
        } else {
            lblStatus.setForeground(new Color(192, 57, 43));
            lblStatus.setText("❌ Falha na conexão. Verifique as credenciais.");
            btnConectar.setEnabled(true);
        }
    }
}
