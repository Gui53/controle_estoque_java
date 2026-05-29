/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package visao;

/**
 *
 * @author Gabriel Conci
 */
public class ProdutoView extends javax.swing.JFrame {

    private dao.ProdutoDAO dao = new dao.ProdutoDAO();
    private dao.CategoriaDAO categoriaDAO = new dao.CategoriaDAO();

    private void carregarTabela() {
        String[] colunas = {"ID", "Nome", "Preço", "Unidade", "Estoque", "Mínimo", "Máximo", "Categoria"};
        javax.swing.table.DefaultTableModel model = new javax.swing.table.DefaultTableModel(colunas, 0);

        for (modelo.Produto p : dao.visualizar()) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getUnidade(),
                p.getQuantidade(),
                p.getMinimo(),
                p.getMaximo(),
                p.getCategoria().getNome()
            });
        }
        tblProdutos.setModel(model);
    }

    private void carregarCategorias() {
        cbCategoria.removeAllItems();
        for (modelo.Categoria c : categoriaDAO.visualizar()) {
            cbCategoria.addItem(c.getNome() + "|" + c.getId());
        }
        cbCategoria.setSelectedIndex(-1);

        // Renderizador para esconder o ID
        cbCategoria.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list,
                    Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value != null) {
                    String[] partes = value.toString().split("\\|");
                    setText(partes[0]);
                }
                return this;
            }
        });
    }

    private void limparCampos() {
        txtId.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        txtMinimo.setText("");
        txtMaximo.setText("");
        cbUnidade.setSelectedIndex(-1);
        cbCategoria.setSelectedIndex(-1);
    }

    private void estilizarTela() {
        getContentPane().setBackground(new java.awt.Color(240, 236, 228));

        btnSalvar.setBackground(new java.awt.Color(45, 158, 95));
        btnSalvar.setForeground(java.awt.Color.WHITE);
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBorderPainted(false);
        btnSalvar.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        btnSalvar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnAtualizar.setBackground(new java.awt.Color(45, 107, 191));
        btnAtualizar.setForeground(java.awt.Color.WHITE);
        btnAtualizar.setFocusPainted(false);
        btnAtualizar.setBorderPainted(false);
        btnAtualizar.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        btnAtualizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnExcluir.setBackground(new java.awt.Color(192, 57, 43));
        btnExcluir.setForeground(java.awt.Color.WHITE);
        btnExcluir.setFocusPainted(false);
        btnExcluir.setBorderPainted(false);
        btnExcluir.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        btnExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnLimpar.setBackground(new java.awt.Color(130, 130, 130));
        btnLimpar.setForeground(java.awt.Color.WHITE);
        btnLimpar.setFocusPainted(false);
        btnLimpar.setBorderPainted(false);
        btnLimpar.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 13));
        btnLimpar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tblProdutos.getTableHeader().setBackground(new java.awt.Color(45, 53, 97));
        tblProdutos.getTableHeader().setForeground(java.awt.Color.WHITE);
        tblProdutos.getTableHeader().setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 12));
        tblProdutos.setRowHeight(28);
        tblProdutos.setFont(new java.awt.Font("SansSerif", java.awt.Font.PLAIN, 13));
        tblProdutos.setGridColor(new java.awt.Color(220, 215, 205));
        tblProdutos.setSelectionBackground(new java.awt.Color(208, 232, 255));
        tblProdutos.setSelectionForeground(new java.awt.Color(30, 30, 30));

        setTitle("Gerenciar Produtos");

        javax.swing.JPanel pnlHeader = new javax.swing.JPanel();
        pnlHeader.setBackground(new java.awt.Color(45, 53, 97));
        pnlHeader.setPreferredSize(new java.awt.Dimension(getWidth(), 50));
        pnlHeader.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 12));

        javax.swing.JLabel lblTitulo = new javax.swing.JLabel("📦 Sistema de Controle de Estoque — Produtos");
        lblTitulo.setForeground(java.awt.Color.WHITE);
        lblTitulo.setFont(new java.awt.Font("SansSerif", java.awt.Font.BOLD, 16));
        pnlHeader.add(lblTitulo);
        getContentPane().add(pnlHeader, java.awt.BorderLayout.NORTH);
    }

    private modelo.Categoria getCategoriaSelected() {
        if (cbCategoria.getSelectedItem() == null) {
            return null;
        }
        String[] partes = cbCategoria.getSelectedItem().toString().split("\\|");
        int id = Integer.parseInt(partes[1]);
        return categoriaDAO.selecionarPorId(id);
    }

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ProdutoView.class.getName());

    /**
     * Creates new form ProdutoView
     */
    public ProdutoView() {
        initComponents();
        txtId.setText("");
        txtNome.setText("");
        txtPreco.setText("");
        txtQuantidade.setText("");
        txtMinimo.setText("");
        txtMaximo.setText("");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.BorderLayout());
        txtId.setEditable(false);
        carregarCategorias();
        carregarTabela();
        estilizarTela();
        setSize(750, 600);
        setLocationRelativeTo(null);
        cbUnidade.setSelectedIndex(-1);

        tblProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tblProdutos.getSelectedRow();
                if (linha >= 0) {
                    txtId.setText(tblProdutos.getValueAt(linha, 0).toString());
                    txtNome.setText(tblProdutos.getValueAt(linha, 1).toString());
                    txtPreco.setText(tblProdutos.getValueAt(linha, 2).toString());
                    cbUnidade.setSelectedItem(tblProdutos.getValueAt(linha, 3).toString());
                    txtQuantidade.setText(tblProdutos.getValueAt(linha, 4).toString());
                    txtMinimo.setText(tblProdutos.getValueAt(linha, 5).toString());
                    txtMaximo.setText(tblProdutos.getValueAt(linha, 6).toString());
                    String nomeCategoria = tblProdutos.getValueAt(linha, 7).toString();
                    for (int i = 0; i < cbCategoria.getItemCount(); i++) {
                        if (cbCategoria.getItemAt(i).toString().startsWith(nomeCategoria + "|")) {
                            cbCategoria.setSelectedIndex(i);
                            break;
                        }
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label1 = new java.awt.Label();
        txtId = new javax.swing.JTextField();
        label2 = new java.awt.Label();
        txtNome = new javax.swing.JTextField();
        label3 = new java.awt.Label();
        txtPreco = new javax.swing.JTextField();
        label4 = new java.awt.Label();
        cbUnidade = new javax.swing.JComboBox<>();
        label5 = new java.awt.Label();
        txtQuantidade = new javax.swing.JTextField();
        label6 = new java.awt.Label();
        txtMinimo = new javax.swing.JTextField();
        label7 = new java.awt.Label();
        txtMaximo = new javax.swing.JTextField();
        label8 = new java.awt.Label();
        cbCategoria = new javax.swing.JComboBox<>();
        btnSalvar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProdutos = new javax.swing.JTable();
        txtPercentual = new javax.swing.JTextField();
        btnReajustar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        label1.setText("ID");

        txtId.setText("jTextField1");

        label2.setText("NOME");

        txtNome.setText("jTextField1");

        label3.setText("PREÇO UNITÁRIO (R$)");

        txtPreco.setText("jTextField1");

        label4.setText("UNIDADE");

        cbUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KG", "LITRO", "UNIDADE", "PACOTE" }));

        label5.setText("QTD. EM ESTOQUE");

        txtQuantidade.setText("jTextField1");

        label6.setText("QTD. MÍNIMA");

        txtMinimo.setText("jTextField1");

        label7.setText("QTD. MÁXIMA");

        txtMaximo.setText("jTextField1");

        label8.setText("CATEGORIA");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(this::btnSalvarActionPerformed);

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(this::btnAtualizarActionPerformed);

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(this::btnExcluirActionPerformed);

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(this::btnLimparActionPerformed);

        tblProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblProdutos);

        btnReajustar.setText("Reajustar");
        btnReajustar.addActionListener(this::btnReajustarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPreco)
                            .addComponent(txtQuantidade)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(50, 50, 50)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtMaximo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnReajustar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPercentual))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtId)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPreco)
                    .addComponent(cbUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaximo)
                    .addComponent(txtMinimo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnLimpar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSalvar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExcluir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAtualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtPercentual, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnReajustar)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Informe o nome do produto.");
            return;
        }
        if (cbUnidade.getSelectedItem() == null || cbCategoria.getSelectedItem() == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione a unidade e a categoria.");
            return;
        }
        modelo.Produto p = new modelo.Produto();
        p.setNome(nome);
        p.setPreco(Double.parseDouble(txtPreco.getText().replace(",", ".")));
        p.setUnidade(enums.TipoUnidade.valueOf(cbUnidade.getSelectedItem().toString()));
        p.setQuantidade(Double.parseDouble(txtQuantidade.getText().replace(",", ".")));
        p.setMinimo(Double.parseDouble(txtMinimo.getText().replace(",", ".")));
        p.setMaximo(Double.parseDouble(txtMaximo.getText().replace(",", ".")));
        p.setCategoria(getCategoriaSelected());
        dao.inserir(p);
        javax.swing.JOptionPane.showMessageDialog(this, "Produto salvo com sucesso!");
        limparCampos();
        carregarTabela();
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        if (txtId.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um produto na tabela.");
            return;
        }
        modelo.Produto p = new modelo.Produto();
        p.setId(Integer.parseInt(txtId.getText()));
        p.setNome(txtNome.getText().trim());
        p.setPreco(Double.parseDouble(txtPreco.getText()));
        p.setUnidade(enums.TipoUnidade.valueOf(cbUnidade.getSelectedItem().toString()));
        p.setQuantidade(Double.parseDouble(txtQuantidade.getText()));
        p.setMinimo(Double.parseDouble(txtMinimo.getText()));
        p.setMaximo(Double.parseDouble(txtMaximo.getText()));
        p.setCategoria(getCategoriaSelected());
        dao.atualizar(p);
        javax.swing.JOptionPane.showMessageDialog(this, "Produto atualizado!");
        limparCampos();
        carregarTabela();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (txtId.getText().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um produto na tabela.");
            return;
        }
        int id = Integer.parseInt(txtId.getText());
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "Deseja excluir este produto?");
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            dao.apagar(id);
            javax.swing.JOptionPane.showMessageDialog(this, "Produto excluído!");
            limparCampos();
            carregarTabela();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void btnReajustarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReajustarActionPerformed
        String valor = txtPercentual.getText().replace(",", ".").trim();
        if (valor.isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(this, "Informe o percentual de reajuste.");
            return;
        }
        double percentual = Double.parseDouble(valor);
        int confirm = javax.swing.JOptionPane.showConfirmDialog(this,
                "Reajustar todos os preços em " + percentual + "%?",
                "Confirmar reajuste",
                javax.swing.JOptionPane.YES_NO_OPTION);
        if (confirm == javax.swing.JOptionPane.YES_OPTION) {
            dao.reajustarPrecos(percentual);
            javax.swing.JOptionPane.showMessageDialog(this, "Preços reajustados com sucesso!");
            carregarTabela();
        }
    }//GEN-LAST:event_btnReajustarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ProdutoView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnReajustar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbUnidade;
    private javax.swing.JScrollPane jScrollPane1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private java.awt.Label label5;
    private java.awt.Label label6;
    private java.awt.Label label7;
    private java.awt.Label label8;
    private javax.swing.JTable tblProdutos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtMaximo;
    private javax.swing.JTextField txtMinimo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPercentual;
    private javax.swing.JTextField txtPreco;
    private javax.swing.JTextField txtQuantidade;
    // End of variables declaration//GEN-END:variables
}
