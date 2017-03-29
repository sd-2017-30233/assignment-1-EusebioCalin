package Business;


import Persistence.Users;
import Persistence.UsersRole;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Calin on 23.03.2017.
 */
public class AdminBusiness {

    /**
     *
     * @return DefaultTableModel, care va fi luat ca si parametru de catre constructorul JTable-ului.
     */
    public DefaultTableModel listAll()
    {
        Users user= new Users();
        ResultSet rs = user.findAll();
        try {
            return buildTableModel(rs);
        }
        catch (SQLException se)
        {se.printStackTrace();}
        return null;
    }
    public static DefaultTableModel buildTableModel(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    public void create(String username, String password, String role)
    {
        Users user= new Users();
        UsersRole usersRole = new UsersRole();
        user.create(username, password);
        int id = user.findIdByUserAndPass(username,password);
        usersRole.create(id,role);
    }
    public void update(int id, String username, String password)
    {
        Users user = new Users();
        user.update(id,username,password);
    }
    public void delete(int id)
    {
        Users user = new Users();
        user.delete(id);
    }
        public String showReport(String username) {
            try {
                String text = new Scanner( new File(username+".txt") ).useDelimiter("\\A").next();
                return text;
            } catch (FileNotFoundException e) {
                //e.printStackTrace();
                return  "Nu exista inregistrari pentru username-ul furnizat!";
            }
        }
}
