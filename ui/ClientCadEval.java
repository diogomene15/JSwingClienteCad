package ui;
import model.Cliente;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class ClientCadEval {

    public static Cliente gerarPreCliente(String cpfField, String nomeField, String telefoneField, String emailField, String dtNascimentoField,
                                          String ruaField, String numeroField, String bairroField, String cidadeField, String ufField) {
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        boolean camposPreenchidos = false;
        if (cpfField.length() == 0){
            MensagemErro.show("O cpf deve ser informado");
        }
        else if (nomeField.length() == 0){
            MensagemErro.show("O nome deve ser informado");
        }
        else if (telefoneField.length() == 0){
            MensagemErro.show("O telefone deve ser informado");
        }
        else if (emailField.length() == 0){
            MensagemErro.show("O email deve ser informado");
        }
        else if (dtNascimentoField.length() == 0){
            MensagemErro.show("A data de nascimento deve ser informada");
        }
        else if (ruaField.length() == 0){
            MensagemErro.show("A rua deve ser informada");
        }
        else if (numeroField.length() == 0){
            MensagemErro.show("O numero deve ser informado");
        }
        else if (bairroField.length() == 0){
            MensagemErro.show("O bairro deve ser informado");
        }
        else if (cidadeField.length() == 0){
            MensagemErro.show("A cidade deve ser informada");
        }
        else if (ufField.length() == 0){
            MensagemErro.show("A uf deve ser informada");
        }else{
            camposPreenchidos = true;
        }

        Cliente novoCLiente = null;
        if(camposPreenchidos){
            try {
                novoCLiente = new Cliente(cpfField, nomeField, telefoneField, emailField, new Date(formatData.parse(dtNascimentoField).getTime()), ruaField, Integer.parseInt(numeroField), bairroField, cidadeField, ufField);
            } catch (Exception err) {

            }
        }
        if (!Cliente.validarCPF(novoCLiente.getCpf())) {
            MensagemErro.show(String.format("O CPF %s é inválido!!",cpfField));
            novoCLiente = null;
        }

        return novoCLiente;
    }
}
