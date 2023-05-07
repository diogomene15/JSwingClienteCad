package ui;
import javax.swing.JOptionPane;
public class Mensagem {
    public static void showError(String mensagemErr){
        JOptionPane.showMessageDialog(null, mensagemErr, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    public static void showSucces(String mensagemErr){
        JOptionPane.showMessageDialog(null, mensagemErr, "Erro", JOptionPane.INFORMATION_MESSAGE);
    }
}
