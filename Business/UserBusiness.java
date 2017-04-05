package Business;

import Persistence.Account;
import Persistence.AccountGateway;
import Persistence.Client;
import Persistence.ClientGateway;


import javax.swing.table.DefaultTableModel;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

/**
 * Created by Calin on 23.03.2017.
 */
public class UserBusiness {
    /*
        CRUD OPERATIONS
     */
    public void createClient(String name, int cnp, String address)
    {
        ClientGateway cg= new ClientGateway();
        cg.create(name,cnp,address);
        try {
            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            user.println("La data de "+new Date()+" user-ul "+ name+" s-a logat.");
            user.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public void updateClient(int idCardNumber, String name, int cnp, String address)
    {
        ClientGateway cg= new ClientGateway();
        cg.update(idCardNumber,name,cnp,address);
    }
    public DefaultTableModel listAllClientInfo()
    {
       Client c=new Client();
        ResultSet rs = c.getGateway().findAll();
        ArrayList<Client> clients = new ArrayList<Client>();
        try
        {
            while(rs.next())
            {
                int id_card_number = rs.getInt("id_card_number");
                String name = rs.getString("name");
                int CNP = rs.getInt("cnp");
                String address = rs.getString("address");
                clients.add(new Client(name,id_card_number,CNP,address));
            }
        }
        catch(SQLException se)
        {
            se.printStackTrace();
        }
        try {
            return buildClientTableModel(clients);
        }
        catch (SQLException se)
        {se.printStackTrace();}
        return null;
    }
    public void createAccount(int idCardNumber, String type, int amount, Date creationDate)
    {
        AccountGateway ag= new AccountGateway();
        ag.create(idCardNumber,type, amount,creationDate);
    }
    public void updateAccount(int idNumber, int idCardNumber, String type, int amount, Date creationDate)
    {
        AccountGateway ag= new AccountGateway();
        ag.update(idNumber,idCardNumber,type, amount,creationDate);
    }
    public void deleteAccount(int idNumber)
    {
        AccountGateway ag= new AccountGateway();
        ag.delete(idNumber);
    }

    public DefaultTableModel listAllAccountInfo()
    {
        Account ac= new Account();
        ArrayList<Account> accounts = new ArrayList<Account>();
        ResultSet rs = ac.getGateway().findAll();
        try {
            while (rs.next()) {
                String type = rs.getString("type");
                int idCardNumber = rs.getInt("id_card_number");
                int idNumber = rs.getInt("id_number");
                int amount = rs.getInt("amount");
                Date creationDate = rs.getDate("creation_date");
                accounts.add(new Account(idNumber, idCardNumber, type, amount, creationDate));
            }
            rs.close();
        }
        catch (SQLException se)
        {
            se.printStackTrace();
        }
        try {
            return buildAccountTableModel(accounts);
        }
        catch (SQLException se)
        {se.printStackTrace();}
        return null;
    }
    public static DefaultTableModel buildClientTableModel(ArrayList<Client> list)
            throws SQLException {

        String[] columnNames = {"ID",
                "Name",
                "CNP",
                "Address"
                };

        Object[][] array = new Object[list.size()][];
        int i = 0;
        for (Client c : list)
        {
            array[i] = new Object[4];
            array[i][0] = c.getIdCardName();
            array[i][1] = c.getName();
            array[i][2] = c.getCNP();
            array[i][3] = c.getAddress();

            i++;
        }
        return new DefaultTableModel(array, columnNames);
    }
    public static DefaultTableModel buildAccountTableModel(ArrayList<Account> list)
            throws SQLException {

        String[] columnNames = {"ID Number",
                "ID Card Number",
                "Type",
                "Amount",
                "Creation Date"};

        Object[][] array = new Object[list.size()][];
        int i = 0;
        for (Account c : list)
        {
            array[i] = new Object[5];
            array[i][0] = c.getIdNumber();
            array[i][1] = c.getIdCardNumber();
            array[i][2] = c.getType();
            array[i][3] = c.getAmount();
            array[i][4] = c.getCreationDate();
            i++;
        }
        return new DefaultTableModel(array, columnNames);
    }

    /**
     * performs the money transfer from one account to another, but also processes the utilities bills.
     * @param acc1
     * @param acc2
     * @param amount
     * @return
     */
    public int transferMoney(int acc1, int acc2, int amount)
    {
        try {
            PrintWriter sender = new PrintWriter( "Account"+acc1+".txt");
            PrintWriter receiver = new PrintWriter("Account"+acc2+".txt");

            AccountGateway ag = new AccountGateway();
            Account senderAccount= new Account();
            Account receiverAccount = new Account();
            //senderAccount.
            ResultSet rsSender = senderAccount.getGateway().findById(acc1);
            ResultSet rsReceiver = receiverAccount.getGateway().findById(acc2);
            try {
                while (rsSender.next()) {
                    String type = rsSender.getString("type");
                    int idCardNumber = rsSender.getInt("id_card_number");
                    int idNumber = rsSender.getInt("id_number");
                    int money = rsSender.getInt("amount");
                    Date creationDate  = rsSender.getDate("creation_date");
                    senderAccount.setIdNumber(idNumber);
                    senderAccount.setIdCardNumber(idCardNumber);
                    senderAccount.setType(type);
                    senderAccount.setAmount(money);
                    senderAccount.setCreationDate(creationDate);
                }
                while (rsReceiver.next()) {
                    String type = rsReceiver.getString("type");
                    int idCardNumber = rsReceiver.getInt("id_card_number");
                    int idNumber = rsReceiver.getInt("id_number");
                    int money = rsReceiver.getInt("amount");
                    Date creationDate  = rsReceiver.getDate("creation_date");
                    receiverAccount.setIdNumber(idNumber);
                    receiverAccount.setIdCardNumber(idCardNumber);
                    receiverAccount.setType(type);
                    receiverAccount.setAmount(money);
                    receiverAccount.setCreationDate(creationDate);
                }
                rsReceiver.close();
                rsSender.close();
            }
            catch (SQLException e)
                {
                 e.printStackTrace();
                }
            //Account senderAccount = ag.findById(acc1);
            //Account receiverAccount = ag.findById(acc2);
            if (senderAccount.getAmount() <= amount) {
                return -1;
            } else {
                ag.updateSum(acc1, senderAccount.getAmount() - amount);
                ag.updateSum(acc2, receiverAccount.getAmount() + amount);
                sender.println("Soldul contului cu ID = "+acc1+" a fost debitat cu suma de "+amount+" lei. ");
                receiver.println("Soldul contului cu ID = "+acc2+" a crescut cu suma de "+amount+" lei. ");
                sender.close();
                receiver.close();
                return 1;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
    public static void openUserReport(String name)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            user.println("La data de "+new Date()+" user-ul "+ name+" s-a logat.");
            user.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public static void closeUserReport(String name)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            user.println("La data de "+new Date()+" user-ul "+ name+" s-a delogat.");
            user.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public static void addUserOperationToReport(String name,String operation,boolean client,String identification)
    {
        try {

            PrintWriter user = new PrintWriter(new FileOutputStream(name+".txt", true));
            //1 for client
            if (operation.equals("create"))
            {
                if (client==true)
                {
                    user.println("La data de " + new Date() + " user-ul " + name + " a creat un client nou pe numele"+
                            " = " +identification);
                    user.close();
                }
                if (client==false)
                {
                    user.println("La data de " + new Date() + " user-ul " + name + " a creat un cont nou pentru clientul"+
                            "cu id = " +identification);
                    user.close();
                }
          }
          //true for client
            if (client==true) {
                user.println("La data de " + new Date() + " user-ul " + name + " a facut operatia de "+operation+ "asupra clientului cu " +
                        "id = " +identification);
                user.close();
            }
            //false for account
            else if (client==false)
            {
                user.println("La data de " + new Date() + " user-ul " + name + " a facut operatia de "+operation+ "asupra account cu" +
                        "id = " +identification);
                user.close();
            }

            }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
