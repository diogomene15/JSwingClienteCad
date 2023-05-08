package ui;
import model.Cliente;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class ClientCadEval {

    public static Cliente gerarPreCliente(String cpfField, String nomeField, String telefoneField, String emailField, String dtNascimentoField,
                                          String ruaField, String numeroField, String bairroField, String cidadeField, String ufField) {
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        boolean camposPreenchidos = false;
        if (cpfField.replaceAll("[^0-9]", "").length() == 0){
            Mensagem.showError("O cpf deve ser informado");
        }
        else if (nomeField.length() == 0){
            Mensagem.showError("O nome deve ser informado");
        }
        else if (telefoneField.length() == 0){
            Mensagem.showError("O telefone deve ser informado");
        }
        else if (emailField.length() == 0){
            Mensagem.showError("O email deve ser informado");
        }
        else if (dtNascimentoField.replaceAll("[^0-9]", "").length() == 0){
            Mensagem.showError("A data de nascimento deve ser informada");
        }
        else if (ruaField.length() == 0){
            Mensagem.showError("A rua deve ser informada");
        }
        else if (numeroField.replaceAll("[^0-9]", "").length() == 0){
            Mensagem.showError("O numero deve ser informado de maneira válida [caracteres não numéricos não serão considerados!]");
        }
        else if (bairroField.length() == 0){
            Mensagem.showError("O bairro deve ser informado");
        }
        else if (cidadeField.length() == 0){
            Mensagem.showError("A cidade deve ser informada");
        }
        else if (ufField.length() == 0){
            Mensagem.showError("A uf deve ser informada");
        }else{
            camposPreenchidos = true;
        }

        Cliente novoCLiente = null;
        if(camposPreenchidos){
            dtNascimentoField = dtNascimentoField.replaceAll("^[0-9]","");
            cpfField = cpfField.replaceAll("[^0-9]", "");
            numeroField = numeroField.replaceAll("[^0-9]", "");
            try {
                novoCLiente = new Cliente(cpfField, nomeField, telefoneField, emailField, new Date(formatData.parse(dtNascimentoField).getTime()), ruaField, Integer.parseInt(numeroField), bairroField, cidadeField, ufField);
            } catch (Exception err) {
                Mensagem.showError("Erro ao tentar montar objeto de 'Cliente'");
            }
            if (novoCLiente!= null && !Cliente.validarCPF(novoCLiente.getCpf())) {
                Mensagem.showError(String.format("CPF inválido!",cpfField));
                novoCLiente = null;
            }
        }
        return novoCLiente;
    }
}
