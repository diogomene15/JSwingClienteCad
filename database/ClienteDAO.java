package database;
import java.sql.*;
import java.util.ArrayList;

import model.Cliente;

public class ClienteDAO {
    //TODO: Lidar com erros de forma mais apropriada

    public static int insert(Cliente cliente) {
        int qtdLinhasAfetadas = 0;
        Connection conexaoPadrao = null;
        try {
            conexaoPadrao = new Conexao().getConexao();
            PreparedStatement statementInsercao = conexaoPadrao.prepareStatement(
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
        } finally {
            try {
                if(conexaoPadrao != null)
                    conexaoPadrao.close();
            } catch (SQLException e) {
                System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
            }
        }

        return qtdLinhasAfetadas;
    }


    public static void update(Cliente cliente) {
        Connection conexaoPadrao = null;
        try {
            conexaoPadrao = new Conexao().getConexao();
            PreparedStatement statementInsercao = conexaoPadrao.prepareStatement(
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
        } finally {
            try {
                if(conexaoPadrao != null)
                    conexaoPadrao.close();
            } catch (SQLException e) {
                System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
            }
        }
    }




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Cliente> selectAll(){
		ArrayList<Cliente> arrayRes = new ArrayList<>();
		Connection conexaoPadrao = null;
		try {
            conexaoPadrao = new Conexao().getConexao();
            PreparedStatement prepSt = conexaoPadrao.prepareStatement("SELECT * FROM Cliente");
			ResultSet tuplasRes = prepSt.executeQuery(); 
			while (tuplasRes.next()) {
                arrayRes.add(new Cliente(   tuplasRes.getString("Cpf"),
                                            tuplasRes.getString("Nome"),
                                            tuplasRes.getString("Fone"),
                                            tuplasRes.getString("Email"),
                                            tuplasRes.getDate("DataNascimento"),
                                            tuplasRes.getString("Rua"),
                                            tuplasRes.getInt("Numero"),
                                            tuplasRes.getString("Bairro"),
                                            tuplasRes.getString("Cidade"),
                                            tuplasRes.getString("Uf")));
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na execução da query de consulta: " + e.getMessage());
		} finally {			
			try {
                if(conexaoPadrao != null)
				    conexaoPadrao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
			}
		}
		return arrayRes;
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Cliente> searchQuery(String cpf){
		ArrayList<Cliente> arrayRes = new ArrayList<>();
		Connection conexaoPadrao = null;
		try {
            conexaoPadrao = new Conexao().getConexao();
			PreparedStatement prepSt = conexaoPadrao.prepareStatement("SELECT * FROM Cliente WHERE cpf LIKE ?");
			prepSt.setString(1, "%" + cpf + "%");
			ResultSet tuplasRes = prepSt.executeQuery(); 
			while (tuplasRes.next()) {
                arrayRes.add(new Cliente(   tuplasRes.getString("Cpf"),
                                            tuplasRes.getString("Nome"),
                                            tuplasRes.getString("Fone"),
                                            tuplasRes.getString("Email"),
                                            tuplasRes.getDate("DataNascimento"),
                                            tuplasRes.getString("Rua"),
                                            tuplasRes.getInt("Numero"),
                                            tuplasRes.getString("Bairro"),
                                            tuplasRes.getString("Cidade"),
                                            tuplasRes.getString("Uf")));
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na execução da query de consulta: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Houve uma exceção: " + e.getMessage());
		} finally {
			try {
                if(conexaoPadrao != null)
				    conexaoPadrao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
			}
		}
        return arrayRes;
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
	public static boolean delete(String cpf) {
		Connection conexaoPadrao = null;
		try {
            conexaoPadrao = new Conexao().getConexao();
			PreparedStatement prepSt = conexaoPadrao.prepareStatement("DELETE FROM Cliente WHERE cpf = ?");
			prepSt.setString(1, cpf);
			return prepSt.execute();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na conexao com o banco de dados MySQL: " + e.getMessage() + "\n Exclusão não concluída.");
			return false;
		}  finally {
			try {
                if(conexaoPadrao != null)
				    conexaoPadrao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
			}
		}
	}
    //

}
