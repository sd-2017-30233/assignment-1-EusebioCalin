package Persistence;

/**
 * Created by Calin on 20.03.2017.
 */
import Database.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class AccountGateway {
    /**
     * CRUD OPERATIONS
     */
    public Connection conn = null;
    public Statement stmt = null;

    public ArrayList<Account> findAll() {
        ArrayList<Account> accounts = new ArrayList<Account>();
        conn = DB.openConnection();
        stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id_number,id_card_number,type,amount,creation_date FROM Account";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String type = rs.getString("type");
                int idCardNumber = rs.getInt("id_card_number");
                int idNumber = rs.getInt("id_number");
                int amount = rs.getInt("amount");
                Date creationDate  = rs.getDate("creation_date");
                accounts.add(new Account(idNumber,idCardNumber,type,amount,creationDate));

                System.out.print("ID: " + idCardNumber);
                System.out.print(", idNumber: " + idNumber);
                System.out.print(", amount: " + amount);
                System.out.println(", type: " +type);
                System.out.println(", creationDate: " +creationDate);
         }

            rs.close();
            return accounts ;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
        return null;
    }

    public Account findById(int id) {
        conn = DB.openConnection();

        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            Account account=new Account();
            sql = "SELECT id_number,id_card_number,type,amount,creation_date FROM Account WHERE id_number='"+id+"'";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String type = rs.getString("type");
                int idCardNumber = rs.getInt("id_card_number");
                int idNumber = rs.getInt("id_number");
                int amount = rs.getInt("amount");
                Date creationDate  = rs.getDate("creation_date");

                account.setIdNumber(idNumber);
                account.setIdCardNumber(idCardNumber);
                account.setType(type);
                account.setAmount(amount);
                account.setCreationDate(creationDate);
            }

            rs.close();
            return account;
        }catch (SQLException se) {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
        return null;
    }

    public void create(int idCardNumber, String type, int amount, Date creationDate) {
        //dbConn=new dbConn();
        conn = DB.openConnection();
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into Account (id_card_number,type,amount,creation_date) values ('"
                    +idCardNumber+"','"+type+"','"+amount+"','"+creationDate+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DB.closeConnection(conn,stmt);
    }

    public void update(int idNumber, int idCardNumber, String type, int amount, Date creationDate){
        conn = DB.openConnection();
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update Account set id_card_number='" + idCardNumber + "', type='" + type + "', amount='"
                    + amount + "', creation_date='" +creationDate+ "' where id_number='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
    }

    public void delete(int idNumber){
        conn = DB.openConnection();
        stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM Account where id_number='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
    }
//    public static void main(String[] args)
//    {
//        AccountGateway cl= new AccountGateway();
//        //cl.create();
//
//        //cl.create(2,"debit",1000,new Date());
//         cl.update(2,3,"muie",4567,new Date());
//        //cl.delete(6);
//        //cl.delete(7);
//        cl.findAll();
//    }

    public void updateSum(int idNumber, int amount){
        conn = DB.openConnection();
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "update Account set amount='" + amount + "' where id_number='" + idNumber + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
    }

}
