package ui;

import database.ClienteDAO;
import model.Cliente;

import javax.swing.*;

import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.sql.SQLException;

public class ClienteView extends JFrame {

    public static JPanel createFormItemPanel(String title, Component child, Dimension tam) {
        JPanel formItemPanel = new JPanel();
        formItemPanel.setLayout(new GridLayout(1, 2));
        formItemPanel.setPreferredSize(tam);
        formItemPanel.add(new JLabel(title));
        formItemPanel.add(child);
        return formItemPanel;
    }

    public ClienteView(String titulo, Dimension tamanho) {
        String[] ufOptions = {"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"};
        Dimension defaultFieldDimension = new Dimension(400, 40);
        Dimension halfFieldDimension = new Dimension(200, 40);

        MaskFormatter mascaraCPF = new MaskFormatter();
        MaskFormatter mascaraData = new MaskFormatter();
        try {
            mascaraCPF.setMask("###.###.###-##");
            mascaraCPF.setPlaceholderCharacter('X');

            mascaraData.setMask("##/##/####");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        JFormattedTextField cpfField = new JFormattedTextField(mascaraCPF);
        cpfField.setPreferredSize(new Dimension(100, 40));

        JTextField nomeField = new JTextField();
        JTextField ruaField = new JTextField();
        JTextField numeroField = new JTextField();
        JTextField bairroField = new JTextField();
        JTextField cidadeField = new JTextField();
        JComboBox<String> ufField = new JComboBox<String>(ufOptions);
        JTextField telefoneField = new JTextField();
        JTextField emailField = new JTextField();
        JFormattedTextField dtNascimentoField = new JFormattedTextField(mascaraData);

        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnCancelar = new JButton("Cancelar");
        btnCadastrar.addActionListener(e -> {
            Cliente novoCLiente = ClientCadEval.gerarPreCliente(
                    cpfField.getText(),
                    nomeField.getText(),
                    telefoneField.getText(),
                    emailField.getText(),
                    dtNascimentoField.getText(),
                    ruaField.getText(),
                    numeroField.getText(),
                    bairroField.getText(),
                    cidadeField.getText(),
                    ufField.getItemAt(ufField.getSelectedIndex())
            );
            System.out.println("CHECK 1");
            if(novoCLiente != null){
                System.out.println("CHECK 2");
                try{
                    if(ClienteDAO.insert(novoCLiente)==1){
                        Mensagem.showSucces("Cliente cadastrado com sucesso!");
                    };
                }catch (SQLException err){
                    Mensagem.showError("Erro ao cadastrar cliente no banco de dados!.");
                }
            }
        });
        JPanel cpfPanel = createFormItemPanel("CPF*:", cpfField, defaultFieldDimension);
        JPanel nomePanel = createFormItemPanel("Nome*:", nomeField, defaultFieldDimension);
        JPanel ruaPanel = createFormItemPanel("Rua:", ruaField, defaultFieldDimension);
        JPanel numeroPanel = createFormItemPanel("Nº:", numeroField, halfFieldDimension);
        JPanel bairroPanel = createFormItemPanel("Bairro:", bairroField, halfFieldDimension);
        JPanel cidadePanel = createFormItemPanel("Cidade:", cidadeField, halfFieldDimension);
        JPanel ufPanel = createFormItemPanel("UF:", ufField, halfFieldDimension);
        JPanel telefonePanel = createFormItemPanel("Fone:", telefoneField, halfFieldDimension);
        JPanel emailPanel = createFormItemPanel("E-mail:", emailField, halfFieldDimension);
        JPanel dataNascPanel = createFormItemPanel("Data nascimento:", dtNascimentoField, defaultFieldDimension);
        JPanel contentPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 10));

        this.setTitle(titulo);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(tamanho);

        contentPanel.add(cpfPanel);
        contentPanel.add(nomePanel);
        contentPanel.add(ruaPanel);
        contentPanel.add(numeroPanel);
        contentPanel.add(bairroPanel);
        contentPanel.add(cidadePanel);
        contentPanel.add(ufPanel);
        contentPanel.add(telefonePanel);
        contentPanel.add(emailPanel);
        contentPanel.add(dataNascPanel);
        contentPanel.add(new JSeparator());
        contentPanel.add(btnCadastrar);
        contentPanel.add(btnCancelar);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        this.add(contentPanel);
        this.setVisible(true);

    }

}
