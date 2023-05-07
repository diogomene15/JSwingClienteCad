package ui;
import javax.swing.JOptionPane;
public class MensagemErro {
    public static void show(String mensagemErr){
        JOptionPane.showMessageDialog(null, mensagemErr, "Erro", JOptionPane.ERROR_MESSAGE);
    }
}
