package model;

import java.sql.Date;

public class Cliente {
    private final String cpf;
    private String nome;
    private String telefone;
    private String email;
    private Date dataNascimento;
    private String rua;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;

    public Cliente(String cpf, String nome, String telefone, String email, Date dataNascimento, String rua, int numero, String bairro, String cidade, String uf) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    public static boolean validarCPF(String cpf) {

        // Removendo caracteres inválidos
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificando se o CPF tem 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verificando se todos os dígitos são iguais
        boolean todosIguais = true;
        for (int i = 1; i < 11; i++) {
            if (cpf.charAt(i) != cpf.charAt(0)) {
                todosIguais = false;
                break;
            }
        }
        if (todosIguais) {
            return false;
        }

        // Verificando o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (10 - i);
        }
        int resto = soma % 11;
        int primeiroDigitoVerificador = resto < 2 ? 0 : 11 - resto;
        if (primeiroDigitoVerificador != Character.getNumericValue(cpf.charAt(9))) {
            return false;
        }

        // Verificando o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(cpf.charAt(i));
            soma += digito * (11 - i);
        }
        resto = soma % 11;
        int segundoDigitoVerificador = resto < 2 ? 0 : 11 - resto;
        if (segundoDigitoVerificador != Character.getNumericValue(cpf.charAt(10))) {
            return false;
        }

        return true;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return telefone;
    }

    public void setFone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return String.format("Cpf: %s\nNome: %s\nFone: %s\nEmail: %s\nDataNascimento: %s\n"
                            + "Rua: %s\nNumero: %s\nBairro: %s\n",
                this.getCpf().toString()
                , this.getNome().toString()
                , this.getFone().toString()
                , this.getEmail().toString()
                , this.getDataNascimento().toString()
                , this.getRua().toString()
                , this.getNumero()
                , this.getBairro().toString());
    }
}
