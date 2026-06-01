package visao;

/**
 * Tela principal do Sistema de Controle de Estoque.
 *
 * Esta tela funciona como menu principal da aplicação, permitindo
 * acesso aos módulos de categorias, produtos, movimentações e
 * relatórios. Também exibe informações sobre o estado da conexão
 * com o banco de dados.
 *
 * @author Gabriel Conci
 * @version 1.0
 * @since 2026
 * @see CategoriaView
 * @see ProdutoView
 * @see MovimentacaoView
 * @see RelatorioView
 */
public class PrincipalView extends javax.swing.JFrame {

    /**
     * Logger utilizado para registrar mensagens e erros da aplicação.
     */
    private static final java.util.logging.Logger logger
            = java.util.logging.Logger.getLogger(PrincipalView.class.getName());

    /**
     * Botão responsável por abrir o módulo de categorias.
     */
    private javax.swing.JButton btnCategorias;

    /**
     * Botão responsável por abrir o módulo de produtos.
     */
    private javax.swing.JButton btnProdutos;

    /**
     * Botão responsável por abrir o módulo de movimentações de estoque.
     */
    private javax.swing.JButton btnMovimentacoes;

    /**
     * Botão responsável por abrir o módulo de relatórios.
     */
    private javax.swing.JButton btnRelatorios;

    /**
     * Botão responsável por encerrar a aplicação.
     */
    private javax.swing.JButton btnSair;

    /**
     * Painel lateral que contém os botões do menu principal.
     */
    private javax.swing.JPanel pnlMenu;

    /**
     * Constrói a tela principal do sistema.
     *
     * Inicializa os componentes gráficos, configura o menu lateral,
     * aplica a estilização da interface e registra os eventos dos botões.
     */
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

        btnCategorias.addActionListener(e -> new CategoriaView().setVisible(true));
        btnProdutos.addActionListener(e -> new ProdutoView().setVisible(true));
        btnMovimentacoes.addActionListener(e -> new MovimentacaoView().setVisible(true));
        btnRelatorios.addActionListener(e -> new RelatorioView().setVisible(true));
        btnSair.addActionListener(e -> System.exit(0));

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

    /**
     * Inicializa os componentes gráficos da interface.
     *
     * Método gerado automaticamente pela IDE.
     * Não deve ser modificado manualmente.
     */
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
    }
    // </editor-fold>

    /**
     * Monta os componentes centrais da tela principal.
     *
     * Cria o cabeçalho da aplicação, o painel de boas-vindas
     * e exibe o status atual da conexão com o banco de dados.
     */
    private void montarConteudo() {

        // Cabeçalho
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
        pnlCard.setBorder(
                javax.swing.BorderFactory.createLineBorder(
                        new java.awt.Color(220, 215, 205)
                )
        );
        pnlCard.setLayout(new java.awt.GridLayout(3, 1, 0, 10));
        pnlCard.setPreferredSize(new java.awt.Dimension(380, 140));

        javax.swing.JLabel lblBemVindo = new javax.swing.JLabel(
                "👋 Bem-vindo ao Sistema",
                javax.swing.SwingConstants.CENTER
        );
        lblBemVindo.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 18));
        lblBemVindo.setForeground(new java.awt.Color(45, 53, 97));

        javax.swing.JLabel lblSub = new javax.swing.JLabel(
                "Selecione uma opção no menu lateral",
                javax.swing.SwingConstants.CENTER
        );
        lblSub.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));
        lblSub.setForeground(new java.awt.Color(120, 120, 120));

        boolean conectado = conexao.Conexao.getConexao() != null;

        javax.swing.JLabel lblConexao = new javax.swing.JLabel(
                conectado
                        ? "✅ Conectado ao banco de dados"
                        : "❌ Sem conexão com o banco",
                javax.swing.SwingConstants.CENTER
        );

        lblConexao.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
        lblConexao.setForeground(
                conectado
                        ? new java.awt.Color(45, 122, 45)
                        : new java.awt.Color(192, 57, 43)
        );

        pnlCard.add(lblBemVindo);
        pnlCard.add(lblSub);
        pnlCard.add(lblConexao);

        pnlCentro.add(pnlCard);

        getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);
        getContentPane().add(pnlCentro, java.awt.BorderLayout.CENTER);
    }

    /**
     * Aplica a estilização visual aos componentes do menu lateral.
     *
     * Define cores, fontes, cursores e demais propriedades visuais
     * dos botões de navegação do sistema.
     */
    private void estilizarMenu() {

        pnlMenu.setBackground(new java.awt.Color(45, 53, 97));

        java.awt.Color corBotao = new java.awt.Color(45, 53, 97);
        java.awt.Color corTexto = new java.awt.Color(200, 210, 220);
        java.awt.Font fonteBotao = new java.awt.Font(
                "SansSerif",
                java.awt.Font.BOLD,
                13
        );

        javax.swing.JButton[] botoes = {
            btnCategorias,
            btnProdutos,
            btnMovimentacoes,
            btnRelatorios,
            btnSair
        };

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

    /**
     * Método principal responsável por iniciar a aplicação.
     *
     * Configura o tema visual Nimbus e exibe a tela principal do sistema.
     *
     * @param args argumentos de linha de comando.
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info
                    : javax.swing.UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

        } catch (ReflectiveOperationException
                | javax.swing.UnsupportedLookAndFeelException ex) {

            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(
                () -> new PrincipalView().setVisible(true)
        );
    }
}