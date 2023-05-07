package database;

import model.Cliente;

import java.sql.*;
import java.util.ArrayList;
public class ClienteDAO {
    //TODO: Lidar com erros de forma mais apropriada
	
	/*
   	use sgamet;
	
	create table cliente (
		cpf VARCHAR(12) PRIMARY KEY,
	    nome VARCHAR(100),
	    telefone VARCHAR(14),
	    email VARCHAR(100),
	    datanascimento DATE,
	    rua VARCHAR(100),
	    numero INTEGER,
	    bairro VARCHAR(100),
	    cidade VARCHAR(100),
	    uf VARCHAR(3)
	); 
	 */

    public static int insert(Cliente cliente) throws SQLException {
        int qtdLinhasAfetadas = 0;
        Connection conexaoPadrao = new Conexao().getConexao();
        try {
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
                conexaoPadrao.close();
            } catch (SQLException e) {
                System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
            }
        }

        return qtdLinhasAfetadas;
    }


    public static void update(Cliente cliente) throws SQLException {
        Connection conexaoPadrao = new Conexao().getConexao();
        try {
            PreparedStatement statementInsercao = conexaoPadrao.prepareStatement(
                    "UPDATE Cliente SET cpf = ?, nome = ?, telefone = ?, email = ?, " +
                    "dataNascimento = ?, rua = ?, numero = ?, bairro = ?, cidade = ?, uf = ? " +
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
                conexaoPadrao.close();
            } catch (SQLException e) {
                System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
            }
        }
    }




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<Cliente> selectAll() throws SQLException{
		ArrayList<Cliente> arrayRes = new ArrayList<Cliente>();
		Connection conexaoPadrao = new Conexao().getConexao(); 
		try {
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
				conexaoPadrao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
			}
		}
		return arrayRes;
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static ArrayList<Cliente> searchQuery(String cpf) throws SQLException{
		ArrayList<Cliente> arrayRes = new ArrayList<Cliente>();
		Connection conexaoPadrao = new Conexao().getConexao();
		try {
			PreparedStatement prepSt = conexaoPadrao.prepareStatement("SELECT * FROM Cliente WHERE cpf LIKE ?");
			prepSt.setString(1, "%" + cpf + "%"); 
			ResultSet tuplasRes = prepSt.executeQuery(); 
			while (tuplasRes.next()) {
                arrayRes.add(new Cliente(   tuplasRes.getString("Cpf"),
                                            tuplasRes.getString("Nome"),
                                            tuplasRes.getString("Telefone"),
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
				conexaoPadrao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
			}
		}
		return arrayRes;
	}
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  
	public static boolean delete(String cpf) throws SQLException {
		Connection conexaoPadrao = new Conexao().getConexao();
		try {
			PreparedStatement prepSt = conexaoPadrao.prepareStatement("DELETE FROM Cliente WHERE cpf = ?");
			prepSt.setString(1, cpf);
			return prepSt.execute();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na conexao com o banco de dados MySQL: " + e.getMessage() + "\n Exclusão não concluída.");
			return false;
		}  finally {
			try {
				conexaoPadrao.close();
			} catch (SQLException e) {
				System.out.println("Ocorreu uma exceção ao fechar a conexão: " + e.getMessage());
			}
		}
	}

}
