import java.awt.*;
import java.sql.SQLException;

import database.ClienteDAO;
import ui.ClienteView;

public class Main {

    public static void main(String [] args){

        try {
            ClienteDAO.selectAll().forEach(System.out::println);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        new ClienteView("Cadastro Cliente", new Dimension(580, 580));
    }


}