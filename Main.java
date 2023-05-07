import java.awt.*;
import java.sql.SQLException;

import database.ClienteDAO;
import ui.ClienteView;

public class Main {

    public static void main(String [] args){

        try {
            ClienteDAO.selectAll().forEach(e->{System.out.println(e);});
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ClienteView fr = new ClienteView("Cadastro Cliente", new Dimension(580, 580));
//        Cliente cliente = new Cliente("12312312312", "Diogo", "67991897666", "diogo@diogo.com",
//                (new Date(2002, 12, 31)), "Ra", 12, "asd", "CG", "MS");
//
//
//        ClienteDAO cDao = new ClienteDAO();
//
//        cDao.criar(cliente);


    }


}