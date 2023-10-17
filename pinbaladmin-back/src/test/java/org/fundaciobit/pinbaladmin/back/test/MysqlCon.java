package org.fundaciobit.pinbaladmin.back.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

public class MysqlCon {
    public static void main(String[] args) {

        String user = "ptrias";
        String msg = "Text de prova al QueEsticFent";
        String dateStr = "2023-06-28";

        afegeixEntrada(user, dateStr, msg);

        //        String query = "SELECT * FROM modificacionsqueesticfent";
        //        query = "SELECT * FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'modificacionsqueesticfent'";
        //        ferConsulta(query);

    }

    public static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

    protected static void ferConsulta(String query) {
        try {
            //            Class.forName("com.mysql.cj.jdbc.Driver");

            String bd = "queesticfent";
            String login = "queesticfent";
            String password = "queesticfent";
            String url = "jdbc:mysql://192.168.35.20:3306/" + bd;

            Connection con = DriverManager.getConnection(url, login, password);

            //here sonoo is database name, root is username and password  
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String out;
                out = rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + " "
                        + rs.getString(5) + " " + rs.getString(6) + "  " + rs.getString(7) + "  " + rs.getString(8);
                //                  out = rs.getString(4);
                System.out.println(out);
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void afegeixEntrada(String usuari, String dateStr, String missatge) {

        try {
            String bd = "queesticfent";
            String login = "queesticfent";
            String password = "queesticfent";
            String url = "jdbc:mysql://192.168.35.20:3306/" + bd;

            Connection con = DriverManager.getConnection(url, login, password);

            /*
             * 1 -> modificacioID
             * 2 -> accioID
             * 3 -> usuariID
             * 4 -> projecteID
             * 5 -> data (fehca)
             * 6 -> questicfentID
             * 7 -> dada1 (ni idea)
             * 8 -> dada2 - texto
             */

            String SQL_INSERT = "INSERT INTO modificacionsqueesticfent (accioID, usuariID, projecteID, data, dada1) VALUES (?,?,?,?,?)";

            PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, -3);
            preparedStatement.setString(2, usuari);
            preparedStatement.setInt(3, 28);

            Date date;
            try {
                date = SDF.parse(dateStr);
            } catch (Throwable e) {
                date = new Date();
                dateStr = SDF.format(date);
            }
            Timestamp data = new Timestamp(date.getTime());
            preparedStatement.setTimestamp(4, data);
            
            preparedStatement.setString(5, missatge);

            int row = preparedStatement.executeUpdate();

            // rows affected
            System.out.println(row); //1

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
