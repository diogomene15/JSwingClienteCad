package database;

import java.sql.*;

public class Conexao {
	private String database;
	private String url;
	private String user;
	private String password;
	private Connection conexao;
	private String args = "?useTimezone=true&serverTimezone=UTC";

	public Conexao(String database, String url, String user, String password) {
		this.database = database;
		this.url = url + this.database + this.args;
		this.user = user;
		this.password = password;
		
		try {
			this.conexao = DriverManager.getConnection(this.url, this.user, this.password);
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro na conexao com o banco de dados MySQL: " + e.getMessage());
		}
	}

	public Conexao() {
		this("sgamet", "jdbc:mysql://localhost:3306/", "root", "123456789");
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
