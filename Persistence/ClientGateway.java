package Persistence;

/**
 * Created by Calin on 20.03.2017.
 */
import java.sql.*;
import java.util.ArrayList;

import Database.DB;
public class ClientGateway {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/assignment1";

    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "a";
    public Connection conn = null;
    public Statement stmt = null;
    /**
     * CRUD OPERATIONS
     */

    public ArrayList<Client> findAll() {

       conn = DB.openConnection();
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            ArrayList<Client> clients = new ArrayList<Client>();
            sql = "SELECT id_card_number, name, cnp, address FROM Client";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name

                int id_card_number = rs.getInt("id_card_number");
                String name = rs.getString("name");
                int CNP = rs.getInt("cnp");
                String address = rs.getString("address");
                clients.add(new Client(name,id_card_number,CNP,address));
                //Display values

                System.out.print(" ID: " + id_card_number);
                System.out.print(" ,name: " + name);
                System.out.print(" , cnp: " + CNP);
                System.out.println(" , address: " + address);
            }
            //STEP 6: Clean-up environment
             rs.close();
            return clients;
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();

        }
        DB.closeConnection(conn,stmt);
    return null;
    }

    public ResultSet findById(int id) {
        conn = DB.openConnection();
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT id_card_number,name,cnp,address FROM Client WHERE id_card_number='"+id+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                //Retrieve by column name
                String name = rs.getString("name");
                int idCardNumber = rs.getInt("idCardNumber");
                int CNP = rs.getInt("cnp");
                String address = rs.getString("address");

                //Display values
                System.out.print("ID: " + idCardNumber);
                System.out.print(", Name: " + name);
                System.out.print(", CNP: " + CNP);
                System.out.println(", Address: " +address);
            }
            return rs;
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
    DB.closeConnection(conn,stmt);
    return null;
    }

    public void create (String name, int cnp, String address){
        conn = DB.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into Client (name, cnp, address) values ('"+name+"','"+cnp+"','"+address+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DB.closeConnection(conn,stmt);
    }

    public void update(int idCardNumber, String name, int cnp, String address)
    {
        conn  =DB.openConnection();
        try{
            System.out.println("Creating statement...");
            stmt=conn.createStatement();
            String sql;
            sql="update Client set name='" + name + "', cnp='" + cnp + "', address='" + address + "' where id_card_number='" + idCardNumber + "'";
            stmt.execute(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
    }
    public void delete(int idCardNumber){
        conn = DB.openConnection();
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM Client where id_card_number='" + idCardNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
    }
//    public static void main(String[] args)
//    {
//        ClientGateway cl= new ClientGateway();
//        cl.create("Ionci",233333,"pizda");
//        cl.update(1,"adaa",112,"add");
//        cl.findAll();
//        cl.create("boss",13,"adresa1");
//        cl.findAll();
//        cl.delete(1);
//
//        //cl.delete(7);
//        cl.findAll();
//    }
}
