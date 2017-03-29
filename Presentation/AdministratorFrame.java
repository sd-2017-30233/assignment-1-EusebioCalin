package Presentation;

import Business.AdminBusiness;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by Calin on 25.03.2017.
 */
public class AdministratorFrame{
    private JFrame frame;
    private JTable table;
    private JTextField id;
    private JTextField username;
    private JTextField password;
    private JTextField role;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnCreate;
    private JButton btnListEmployees;
    private JButton btnLogout;
    AdminBusiness admin;
    public AdministratorFrame(){
        admin = new AdminBusiness();
        frame = new JFrame();
        frame.setBounds(100, 100, 359, 328);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        panel.setLayout(null);
        panel.setBackground(new Color(1,0.5f,0.15f));
        //JScrollPane scrollPane = new JScrollPane();
        //scrollPane.setBounds(31, 33, 186, 139);
       // panel.add(scrollPane);

       // table = new JTable();
       // scrollPane.setViewportView(table);

        JLabel lblId = new JLabel("ID Employee");
        lblId.setBounds(30, 34, 82, 14);
        panel.add(lblId);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(30, 71, 82, 14);
        panel.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(30, 112, 82, 14);
        panel.add(lblPassword);

        id = new JTextField();
        id.setBounds(122, 31, 86, 20);
        panel.add(id);
        id.setColumns(10);

        username = new JTextField();
        username.setBounds(122, 68, 86, 20);
        panel.add(username);
        username.setColumns(10);

        password = new JTextField();
        password.setBounds(122, 109, 86, 20);
        panel.add(password);
        password.setColumns(10);

        role = new JTextField();
        role.setBounds(122, 154, 86, 20);
        panel.add(role);
        role.setColumns(10);


        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String id1=id.getText();
                String user=username.getText();
                String pass=password.getText();
                if (!user.equals("")&& !pass.equals("")&& !id1.equals("")){
                    int id= Integer.parseInt(id1);
                    admin.update(id,user,pass);
                    JOptionPane.showMessageDialog(frame,
                            "Update efectuat cu succes!");
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,
                            "Completati campurile corespunzator!","Eroare",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        btnUpdate.setBounds(218, 67, 89, 23);
        panel.add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String id1=id.getText();
                if (!id1.equals("")) {
                    try {
                        int id= Integer.parseInt(id1);
                        admin.delete(id);
                        JOptionPane.showMessageDialog(frame,
                                "Delete efectuat cu succes!");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(frame,
                                "Completati campurile corespunzator!","Eroare",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
                else
                JOptionPane.showMessageDialog(frame,
                        "Completati campurile corespunzator!","Eroare",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        btnDelete.setBounds(218, 108, 89, 23);
        panel.add(btnDelete);

        btnCreate = new JButton("Create");
        btnCreate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                System.out.println("create");
                String user=username.getText();
                String pass=password.getText();
                String r=role.getText();
                if (!user.equals("") && !pass.equals("") && !r.equals("")) {
                    admin.create(user, pass, r);
                    JOptionPane.showMessageDialog(frame,
                            "Create efectuat cu succes!");
                }
                else
                {
                    JOptionPane.showMessageDialog(frame,
                            "Completati campurile corespunzator!","Eroare",
                            JOptionPane.WARNING_MESSAGE);
                }
                    //id.setText("create");
            }
        });
        btnCreate.setBounds(218, 30, 89, 23);
        panel.add(btnCreate);

        btnListEmployees = new JButton("List");
        btnListEmployees.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                table =new JTable(admin.listAll());
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
                System.out.println("listEmployees");
            }
        });
        btnListEmployees.setBounds(218, 153,89, 23);
        panel.add(btnListEmployees);

        JLabel lblRole = new JLabel("Role");
        lblRole.setBounds(30, 157, 46, 14);
        panel.add(lblRole);

        JButton btnReport = new JButton("Report");
        btnReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String user=username.getText();
                if (!user.equals(""))
                    JOptionPane.showMessageDialog(null,admin.showReport(user));
                else
                {
                    JOptionPane.showMessageDialog(frame,
                            "Cautati dupa un Username valid!","Eroare",
                            JOptionPane.WARNING_MESSAGE);
                }
              //  id.setText("create");
            }
        });
        btnReport.setBounds(218, 201,89, 23);
        panel.add(btnReport);

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
                new Login();
            }
        });
        btnLogout.setBounds(218, 253,89, 23);
        panel.add(btnLogout);

        frame.setVisible(true);
    }


}
