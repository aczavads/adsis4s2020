package aula20201117.gui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class EditarProdutoDialog extends JDialog {
    @Autowired
    private ProdutoRestClient restClient;

    public EditarProdutoDialog() {
        super();
        this.setSize(500, 350);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.getRootPane().setBorder(BorderFactory.createRaisedBevelBorder());
        this.add(createPanel());
    }

    private JPanel createPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        JPanel firstRow = new JPanel();
        firstRow.setLayout(new BoxLayout(firstRow, BoxLayout.X_AXIS));
        JLabel labelNome = new JLabel("Nome...:");
        JTextField fieldNome = new JTextField(50);
        fieldNome.setMaximumSize(fieldNome.getPreferredSize());
        firstRow.add(labelNome);
        firstRow.add(fieldNome);

        JPanel secondRow = new JPanel();
        secondRow.setLayout(new BoxLayout(secondRow, BoxLayout.X_AXIS));
        JLabel labelPreço = new JLabel("Preço...:");
        JTextField fieldPreço = new JTextField(50);
        fieldPreço.setMaximumSize(fieldPreço.getPreferredSize());
        secondRow.add(labelPreço);
        secondRow.add(fieldPreço);

        JPanel thirdRow = new JPanel();
        thirdRow.setLayout(new BoxLayout(thirdRow, BoxLayout.X_AXIS));
        thirdRow.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
        JButton buttonSalvar = new JButton("Salvar");
        buttonSalvar.addActionListener( event -> {
            salvarProduto(fieldNome.getText(), fieldPreço.getText());
        });
        thirdRow.add(buttonSalvar);



        mainPanel.add(firstRow);
        mainPanel.add(secondRow);
        mainPanel.add(thirdRow);

        return mainPanel;
    }

    private void salvarProduto(String nome, String preçoAtualAsString) {
        double preçoAtual = Double.parseDouble(preçoAtualAsString);
        JOptionPane.showMessageDialog(this, "Você digitou o nome [" + nome + "] e preço [" + preçoAtual + "]" );
        try {
            ResponseEntity<String> response = restClient.postProduto(nome, preçoAtual);            
            JOptionPane.showMessageDialog(this, "Resultado da operação: status " + response.getStatusCode() + ".");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exceção: " + e.getMessage());
        }
    }

}
