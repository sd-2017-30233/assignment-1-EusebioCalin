package Database;

import java.sql.*;
public class DB {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost:5432/assignment1";
    //  Database credentials
    static final String USER = "postgres";
    static final String PASS = "a";
    public static Connection openConnection() {
        try{
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException cnfe) {
                System.out.println("Could not find the JDBC driver!");
                System.exit(1);
            }
            Connection conn = null;
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            return conn;
        }
        catch (SQLException se)
        {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        return null;
    }
    public static void closeConnection(Connection conn, Statement stmt)
    {
        try{
            if(stmt!=null)
                stmt.close();
        }catch(SQLException se2){
        }// nothing we can do
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }//end finally try
    }

//    public static void main(String[] args) {
//        Connection conn = null;
//        Statement stmt = null;
//
//        try {
//            try{
//                Class.forName("org.postgresql.Driver");
//            } catch (ClassNotFoundException cnfe){
//                System.out.println("Could not find the JDBC driver!");
//                System.exit(1);
//            }
//            System.out.println("Connecting to database...");
//            conn = DriverManager.getConnection(DB_URL, USER, PASS);
//
//            System.out.println("Creating statement...");
//            stmt = conn.createStatement();
//            String sql;
//            sql= "SELECT id,name,birthdate,address FROM Student";
//            ResultSet rs =stmt.executeQuery(sql);
//            while(rs.next()){
//                //Retrieve by column name
//                int id  = rs.getInt("id");
//                String age = rs.getString("name");
//                String first = rs.getString("birthdate");
//                String last = rs.getString("address");
//
//                //Display values
//                System.out.print("ID: " + id);
//                System.out.print(", Age: " + age);
//                System.out.print(", First: " + first);
//                System.out.println(", Last: " + last);
//            }
//            //STEP 6: Clean-up environment
//            rs.close();
//            stmt.close();
//            conn.close();
//        } catch (SQLException se) {
//            //Handle errors for JDBC
//            se.printStackTrace();
//
//        }finally{
//            //finally block used to close resources
//            try{
//                if(stmt!=null)
//                    stmt.close();
//            }catch(SQLException se2){
//            }// nothing we can do
//            try{
//                if(conn!=null)
//                    conn.close();
//            }catch(SQLException se){
//                se.printStackTrace();
//            }//end finally try
//        }//end try
//        System.out.println("Goodbye!");
//    }
}
