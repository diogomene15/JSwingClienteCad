package database;

import java.sql.*;
import java.util.Properties;

public class Conexao {
    private Connection conn;

    public Conexao(String url, String user, String password) throws SQLException {
        Properties propriedadesBD = new Properties();
        propriedadesBD.setProperty("user",user);
        propriedadesBD.setProperty("password",password);

        this.conn = DriverManager.getConnection(url, propriedadesBD);
    }
    public Conexao() throws SQLException {
        this("jdbc:postgresql://localhost:5432/facom", "backend", "senha123");
    }

//    private void migrate(){
//        this.runQuery("")
//    }
    public ResultSet runQuery(String _sql) throws SQLException{
        Statement st = this.conn.createStatement();
        ResultSet rs = st.executeQuery(_sql);
        st.close();
        return rs;
    }
    public int runUpdate(String _sql) throws SQLException{
        Statement st = this.conn.createStatement();
        final int linhasAfetadas = st.executeUpdate(_sql);
        st.close();
        return linhasAfetadas;
    }
    public PreparedStatement createStatement(String sqlStatement) throws SQLException{
        return this.conn.prepareStatement(sqlStatement);
    }
}
