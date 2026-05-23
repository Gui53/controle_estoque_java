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

        // Eventos dos botões
        btnCategorias.addActionListener(e -> new CategoriaView().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));
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