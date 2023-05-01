package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Cliente;

public class ClienteDAO {
    //TODO: Lidar com erros de forma mais apropriada

    private Conexao con;

    public ClienteDAO() {
        createConexao();
    }

    private void createConexao() {
        try {
            this.con = new Conexao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int criar(Cliente cliente) {
        int qtdLinhasAfetadas = 0;
        try {

            PreparedStatement statementInsercao = con.createStatement(
                    "INSERT INTO Cliente (cpf, nome, telefone, email," +
                            " dataNascimento, rua, numero, bairro, cidade, uf) VALUES (?,?,?,?,?,?,?,?,?,?)"
            );

            statementInsercao.setString(1, cliente.getCpf());
            statementInsercao.setString(2, cliente.getNome());
            statementInsercao.setString(3, cliente.getFone());
            statementInsercao.setString(4, cliente.getEmail());
            statementInsercao.setDate(5, cliente.getDataNascimento());
            statementInsercao.setString(6, cliente.getRua());
            statementInsercao.setInt(7, cliente.getNumero());
            statementInsercao.setString(8, cliente.getBairro());
            statementInsercao.setString(9,cliente.getCidade());
            statementInsercao.setString(10, cliente.getUf());

            qtdLinhasAfetadas = statementInsercao.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao tentar criar usuário!");
            e.printStackTrace();
        }

        return qtdLinhasAfetadas;
    }


    public void atualizar(Cliente cliente) {
        try {

            PreparedStatement statementInsercao = con.createStatement(
                    "UPDATE Cliente SET cpf = ?, nome = ?, telefone = ?, email = ?, " +
                    "dataNascimento = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, uf = ?, " +
                    "WHERE cpf = ?"
            );

            statementInsercao.setString(1, cliente.getCpf());
            statementInsercao.setString(2, cliente.getNome());
            statementInsercao.setString(3, cliente.getFone());
            statementInsercao.setString(4, cliente.getEmail());
            statementInsercao.setDate(5, cliente.getDataNascimento());
            statementInsercao.setString(6, cliente.getRua());
            statementInsercao.setInt(7, cliente.getNumero());
            statementInsercao.setString(8, cliente.getBairro());
            statementInsercao.setString(9,cliente.getCidade());
            statementInsercao.setString(10, cliente.getUf());
            statementInsercao.setString(11, cliente.getCpf());

            statementInsercao.execute();

        } catch (SQLException e) {
            System.out.println("Erro ao tentar atualizar usuário!");
            e.printStackTrace();
        }
    }




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    	public static ArrayList<Produto> selectAll(){
		ArrayList<Produto> arrayRes = new ArrayList<>();
		Connection conexaoPadrao = new ConnectionFactory().getConexao(); 
		try {
			PreparedStatement prepSt = conexaoPadrao.prepareStatement("SELECT * FROM produtos");
			ResultSet tuplasRes = prepSt.executeQuery(); 
			while (tuplasRes.next()) {
				arrayRes.add(new Produto(tuplasRes.getString("nome"), tuplasRes.getDouble("preco")));
			}
			ConnectionFactory.checkSearch(arrayRes);
			return (arrayRes);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na execução da query de consulta: " + e.getMessage());
		} catch (EmptyQueryException e) {
			System.out.println("Houve uma exceção: " + e.getMessage());
		} finally {			
			try {
				conexaoPadrao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
			}
		}
		return arrayRes;
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
