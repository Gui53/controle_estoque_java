package view;

public class PrincipalView extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PrincipalView.class.getName());

    // Declaração dos botões como campos da classe
    private javax.swing.JButton btnCategorias;
    private javax.swing.JButton btnProdutos;
    private javax.swing.JButton btnMovimentacoes;
    private javax.swing.JButton btnRelatorios;
    private javax.swing.JButton btnSair;

    public PrincipalView() {
        initComponents();
        setSize(800, 550);
        setLocationRelativeTo(null);
        pnlMenu.setLayout(new java.awt.GridLayout(5, 1));

        btnCategorias = new javax.swing.JButton("🏷️ Categorias");
        btnProdutos = new javax.swing.JButton("📦 Produtos");
        btnMovimentacoes = new javax.swing.JButton("🔄 Movimentações");
        btnRelatorios = new javax.swing.JButton("📊 Relatórios");
        btnSair = new javax.swing.JButton("🚪 Sair");

        pnlMenu.add(btnCategorias);
        pnlMenu.add(btnProdutos);
        pnlMenu.add(btnMovimentacoes);
        pnlMenu.add(btnRelatorios);
        pnlMenu.add(btnSair);

        estilizarMenu();
        montarConteudo();

        // Eventos dos botões
        btnCategorias.addActionListener(e -> new CategoriaView().setVisible(true));
        btnProdutos.addActionListener(e -> new ProdutoView().setVisible(true));
        btnMovimentacoes.addActionListener(e -> new MovimentacaoView().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                int confirm = javax.swing.JOptionPane.showConfirmDialog(
                        null,
                        "Deseja realmente sair do sistema?",
                        "Confirmar saída",
                        javax.swing.JOptionPane.YES_NO_OPTION
                );
                if (confirm == javax.swing.JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        pnlMenu = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout pnlMenuLayout = new javax.swing.GroupLayout(pnlMenu);
        pnlMenu.setLayout(pnlMenuLayout);
        pnlMenuLayout.setHorizontalGroup(
                pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 160, Short.MAX_VALUE)
        );
        pnlMenuLayout.setVerticalGroup(
                pnlMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 470, Short.MAX_VALUE)
        );

        getContentPane().add(pnlMenu, java.awt.BorderLayout.LINE_START);

        pack();
    }// </editor-fold>

    private void montarConteudo() {
        // Cabeçalho azul
        javax.swing.JPanel pnlHeader = new javax.swing.JPanel();
        pnlHeader.setBackground(new java.awt.Color(45, 53, 97));
        pnlHeader.setPreferredSize(new java.awt.Dimension(getWidth(), 50));
        pnlHeader.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 12));

        javax.swing.JLabel lblTitulo = new javax.swing.JLabel("📦 Sistema de Controle de Estoque");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 16));
        pnlHeader.add(lblTitulo);

        // Área central
        javax.swing.JPanel pnlCentro = new javax.swing.JPanel();
        pnlCentro.setBackground(new java.awt.Color(240, 236, 228));
        pnlCentro.setLayout(new java.awt.GridBagLayout());

        javax.swing.JPanel pnlCard = new javax.swing.JPanel();
        pnlCard.setBackground(java.awt.Color.WHITE);
        pnlCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 215, 205)));
        pnlCard.setLayout(new java.awt.GridLayout(3, 1, 0, 10));
        pnlCard.setPreferredSize(new java.awt.Dimension(380, 140));

        javax.swing.JLabel lblBemVindo = new javax.swing.JLabel("👋 Bem-vindo ao Sistema", javax.swing.SwingConstants.CENTER);
        lblBemVindo.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18));
        lblBemVindo.setForeground(new java.awt.Color(45, 53, 97));

        javax.swing.JLabel lblSub = new javax.swing.JLabel("Selecione uma opção no menu lateral", javax.swing.SwingConstants.CENTER);
        lblSub.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));
        lblSub.setForeground(new java.awt.Color(120, 120, 120));

        // Badge de conexão
        boolean conectado = conexao.Conexao.getConexao() != null;
        javax.swing.JLabel lblConexao = new javax.swing.JLabel(
                conectado ? "✅ Conectado ao banco de dados" : "❌ Sem conexão com o banco",
                javax.swing.SwingConstants.CENTER
        );
        lblConexao.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
        lblConexao.setForeground(conectado ? new java.awt.Color(45, 122, 45) : new java.awt.Color(192, 57, 43));

        pnlCard.add(lblBemVindo);
        pnlCard.add(lblSub);
        pnlCard.add(lblConexao);

        pnlCentro.add(pnlCard);

        getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);
        getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);
    }

    private void estilizarMenu() {
        pnlMenu.setBackground(new java.awt.Color(45, 53, 97));

        java.awt.Color corBotao = new java.awt.Color(45, 53, 97);
        java.awt.Color corTexto = new java.awt.Color(200, 210, 220);
        java.awt.Font fonteBotao = new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13);

        javax.swing.JButton[] botoes = {btnCategorias, btnProdutos, btnMovimentacoes, btnRelatorios, btnSair};

        for (javax.swing.JButton btn : botoes) {
            btn.setBackground(corBotao);
            btn.setForeground(corTexto);
            btn.setFont(fonteBotao);
            btn.setBorderPainted(false);
            btn.setFocusPainted(false);
            btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }

        btnSair.setForeground(new java.awt.Color(255, 120, 120));
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new PrincipalView().setVisible(true));
    }

    // Variables declaration - do not modify
    private javax.swing.JPanel pnlMenu;
    // End of variables declaration
}
