package database;

import java.sql.*;

public class Conexao {
	private final String url;
	private final String user;
	private final String password;
	private Connection conexao;

	public Conexao(String database, String url, String user, String password) {
		this.url = url + database;
		this.user = user;
		this.password = password;
		
		try {
			this.conexao = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na conexao com o banco de dados MySQL: " + e.getMessage());
		}
	}

	public Conexao() {
		this("estoque", "jdbc:mysql://localhost:3000/", "root", "123456789");
	}

	public Connection getConexao() throws SQLException {
        if(this.conexao == null){
            this.conexao =  DriverManager.getConnection(this.url, this.user, this.password);
        }
		return this.conexao;
	}

	public void closeConnection() throws SQLException{
		this.conexao.close();
		this.conexao = null;
	}

//    private void migrate(){
//        this.runQuery("")
//    }
    // public ResultSet runQuery(String _sql) throws SQLException{
    //     Statement st = this.conn.createStatement();
    //     ResultSet rs = st.executeQuery(_sql);
    //     st.close();
    //     return rs;
    // }
    // public int runUpdate(String _sql) throws SQLException{
    //     Statement st = this.conn.createStatement();
    //     final int linhasAfetadas = st.executeUpdate(_sql);
    //     st.close();
    //     return linhasAfetadas;
    // }
    // public PreparedStatement createStatement(String sqlStatement) throws SQLException{
    //     return this.conn.prepareStatement(sqlStatement);
    // }
}
