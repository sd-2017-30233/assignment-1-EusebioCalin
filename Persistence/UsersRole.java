package Persistence;

/**
 * Created by Calin on 20.03.2017.
 */

import Database.DB;
import java.sql.*;

public class UsersRole {
    private int id;
    private int userId;
    private String userType;

    public UsersRole()
    {};

    public UsersRole(int id, int userId, String userType) {
        this.id = id;
        this.userId = userId;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    public ResultSet findAll() {

        Connection conn = DB.openConnection();
        Statement stmt =null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, user_id, user_type FROM usersrole";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                //Retrieve by column name

                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                String userType = rs.getString("user_type");

                //Display values

                System.out.print(" ID: " + id);
                System.out.print(" ,User ID: " + userId);
                System.out.println(" , User Type: " + userType);
            }
            //STEP 6: Clean-up environment
            rs.close();
            return rs;
        }catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();

        }
        DB.closeConnection(conn,stmt);
        return null;
    }

    public ResultSet findById(int id) {
        Connection conn = DB.openConnection();
        Statement stmt =null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT user_id,user_type FROM usersrole WHERE id='"+id+"'";
            ResultSet rs=stmt.executeQuery(sql);
            while(rs.next())
            {
                //Retrieve by column name
                int userId = rs.getInt("user_id");
                String userType = rs.getString("user_type");

                //Display values

                System.out.print(" ID: " + id);
                System.out.print(" ,User ID: " + userId);
                System.out.println(" , User Type: " + userType);
            }
            return rs;
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
        return null;
    }

    public void create (int userId, String userType){
        Connection conn = DB.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into usersrole (user_id, user_type) values ('"+userId+"','"+userType+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DB.closeConnection(conn,stmt);
    }

    public void update(int id, int userId, String userType)
    {
        Connection conn  =DB.openConnection();
        Statement stmt=null;
        try{
            System.out.println("Creating statement...");
            stmt=conn.createStatement();
            String sql;
            sql="update usersrole set user_id='" + userId + "', userType='" + userType + "' where id='" + id + "'";
            stmt.execute(sql);
        }
        catch(SQLException se){
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
    }
    public void delete(int id){
        Connection conn = DB.openConnection();
        Statement stmt=null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "delete FROM usersrole where id='" + id + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
    }

}
