package Persistence;

/**
 * Created by Calin on 20.03.2017.
 */
import Database.DB;
import java.sql.*;

public class Users {
    private int id;
    private String username;
    private String password;

    public Users() {
    }

    ;

    public Users(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ResultSet findAll() {

        Connection conn = DB.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, username, password FROM Users";
            ResultSet rs = stmt.executeQuery(sql);

//            while (rs.next()) {
//                //Retrieve by column name
//
//                int id = rs.getInt("id");
//                String username = rs.getString("username");
//                String password = rs.getString("password");
//
//                //Display values
//
//                System.out.print(" ID: " + id);
//                System.out.print(" ,username: " + username);
//                System.out.println(" , password: " + password);
//            }
            //STEP 6: Clean-up environment

            return rs;
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();

        }
        DB.closeConnection(conn, stmt);
        return null;
    }

    public int findIdByUserAndPass(String username, String password)

    {
        Connection conn = DB.openConnection();
        Statement stmt =null;
        int result=0;
        String type="";
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT d.id\n" +
                    "    FROM users d\n" +
                    "    WHERE d.username='"+username+"' and d.password='"+password+"';";
            ResultSet rs=stmt.executeQuery(sql);
            int id=0;
            while(rs.next())
            {
                //Retrieve by column name
                id = rs.getInt("id");
            }
           return id;
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
        return 0;
    }

    public int findByUserAndPass(String username, String password) {
        Connection conn = DB.openConnection();
        Statement stmt =null;
        int result=0;
        String type="";
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql = "SELECT d.id, d.username, d.password, f.user_type\n" +
                    "    FROM users d, usersrole f\n" +
                    "    WHERE d.id = f.user_id and d.username='"+username+"' and d.password='"+password+"';";
            ResultSet rs=stmt.executeQuery(sql);

            while(rs.next())
            {
                //Retrieve by column name
                int id = rs.getInt("id");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                type = rs.getString("user_type");
                //Display values
                System.out.print("ID: " + id);
                System.out.print(", Username: " + user);
                System.out.println(", Password: " +pass);
                System.out.println(", User Type: "+ type);
            }
            if (type.equals(""))
            {//nothing to do
            }
            else
           if (type.equals("admin") )
               result=1;
            else if (type.equals("user"))
                result=2;
            return result;
        } catch (SQLException se)
        {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
        return 0;
    }

    public void create (String username, String password){
        Connection conn = DB.openConnection();
        Statement stmt = null;
        try {
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            String sql;
            sql = "insert into Users (username, password) values ('"+username+"','"+password+"')";
            stmt.execute(sql);
        }catch (SQLException se) {
            se.printStackTrace();

        }
        DB.closeConnection(conn,stmt);
    }

    public void update(int id, String username, String password)
    {
        Connection conn  =DB.openConnection();
        Statement stmt=null;
        try{
            System.out.println("Creating statement...");
            stmt=conn.createStatement();
            String sql;
            sql="update Users set username='" + username + "', password='" + password + "' where id='" + id + "'";
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
            sql = "delete FROM Users where id='" + id + "'";
            stmt.execute(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        }
        DB.closeConnection(conn,stmt);
    }
//  public static void main(String[] args)
//   {
//        Users cl= new Users();
//        cl.findAll();
//        cl.findByUserAndPass("andrei","parola");
//    }

}
