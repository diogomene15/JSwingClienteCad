package database;

import java.sql.*;

public class Conexao {
	private final String url;
	private final String user;
	private final String password;

	public Conexao(String url, String database, String user, String password) {
		String args = "?useTimezone=true&serverTimezone=UTC";
		this.url = url + database + args;
		this.user = user;
		this.password = password;

	}

	public Conexao() {
		this("jdbc:mysql://sql10.freemysqlhosting.net/","sql10616670", "sql10616670", "pu7KvT5WaI");

	}

	public Connection getConexao() throws SQLException {
		return  DriverManager.getConnection(this.url, this.user, this.password);
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
