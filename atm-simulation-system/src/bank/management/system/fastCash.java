package bank.management.system;


import org.bson.Document;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class fastCash extends JFrame implements ActionListener {

    JButton rs100,rs500,rs1000,rs2000,rs5000,rs10000,exit;
    Document loginData;

    fastCash(Document loginData){



        this.loginData = loginData;

        //basic swing configuration
        setUndecorated(true);
        setLayout(null);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setTitle("New Account Application Form");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);


        //creating objects for frame
        //atm image
        ImageIcon atm = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg") );
        Image atmResized = atm.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        atm = new ImageIcon(atmResized);
        JLabel atmImg = new JLabel(atm);

        rs100 = new JButton("Rs 100");
        rs100.setFont(new Font("System",Font.BOLD,16));

        rs500 = new JButton("Rs 500");
        rs500.setFont(new Font("System",Font.BOLD,16));

        rs1000 = new JButton("Rs 1000");
        rs1000.setFont(new Font("System",Font.BOLD,16));

        rs2000 = new JButton("Rs 2000");
        rs2000.setFont(new Font("System",Font.BOLD,16));

        rs5000 = new JButton("Rs 5000");
        rs5000.setFont(new Font("System",Font.BOLD,16));

        rs10000 = new JButton("Rs 10000");
        rs10000.setFont(new Font("System",Font.BOLD,16));

        exit = new JButton("Exit");
        exit.setFont(new Font("System",Font.BOLD,16));


        //adding objects to the frame

        add(atmImg);
        atmImg.setBounds(0,0,900,800);

        atmImg.add(rs100);
        rs100.setBounds(170,365,130,30);

        atmImg.add(rs500);
        rs500.setBounds(370,365,130,30);

        atmImg.add(rs1000);
        rs1000.setBounds(170,400,130,30);

        atmImg.add(rs2000);
        rs2000.setBounds(370,400,130,30);

        atmImg.add(rs5000);
        rs5000.setBounds(170,435,130,30);

        atmImg.add(rs10000);
        rs10000.setBounds(370,435,130,30);

        atmImg.add(exit);
        exit.setBounds(370,470,130,30);

        //adding action listeners to the buttons

        rs100.addActionListener(this);
        rs500.addActionListener(this);
        rs1000.addActionListener(this);
        rs2000.addActionListener(this);
        rs5000.addActionListener(this);
        rs10000.addActionListener(this);
        exit.addActionListener(this);



    }

    //function which will be called on the button click

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){
            setVisible(false);
        }else{

            int wdlValue = 0;
            long cardNo = loginData.getLong("Card Number");

            if(ae.getSource() == rs100){
                wdlValue = 100;
            }else if(ae.getSource() == rs500){
                wdlValue = 500;
            }else if(ae.getSource() == rs1000){
                wdlValue = 1000;
            }else if(ae.getSource() == rs2000){
                wdlValue = 2000;
            }else if(ae.getSource() == rs5000){
                wdlValue = 5000;
            }else if(ae.getSource() == rs10000) {
                wdlValue = 10000;
            }

           //imported code

            //pin verification

            int verifyPin =  Integer.parseInt(JOptionPane.showInputDialog("Enter your Pin"));


            if(verifyPin == (int)loginData.get("Pin")){

                //new conn class
                conn c = new conn();

                try{
                    //choosing collection balance for cash withdrawal

                    c.chooseCollection("balance");
                    Document currentUser = c.findUser(loginData);

                    //getting current balance from database
                    int oldBalance = (int)currentUser.get("Balance");
                    System.out.println(oldBalance);
                    if(oldBalance < wdlValue){
                        //if withdrawal value is greater than balance
                        JOptionPane.showMessageDialog(null,"Insufficient Balance");
                    }else{

                        //if withdrawal value is lessthan or equal to balance

                        int finalVal = oldBalance-wdlValue;
                        c.updateBalance(loginData,finalVal);
                        JOptionPane.showMessageDialog(null,"Rs "+wdlValue+" withdrawal successfully");
                        c.updateTransaction(cardNo,wdlValue,"Withdrawal");
                        setVisible(false);
                    }
                }catch(Exception e){

                    //if user entered anything except numbers then exception will be called
                    JOptionPane.showMessageDialog(null,"Please Enter Valid Amount");

                }finally {

                    //closing database connection
                    c.close();
                }
            }else{

                //if pin verification is false
                JOptionPane.showMessageDialog(null,"Invalid Pin");
            }

            //imported code


        }

    }

    public static void main(String[] args){
        Document neel = new Document("Card Number",545431136412852l);
        neel.put("Pin",6199);
        new fastCash(neel);
    }

}
