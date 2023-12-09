package bank.management.system;

import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.bson.*;

public class transactions extends JFrame implements ActionListener  {

    JButton deposit, withdrawal,fastCash,miniStmt,pinChange,balanceEnq,exit;

    Document loginData;

    transactions(Document loginData) {

        this.loginData = loginData;

        //basic swing configuration
        setUndecorated(true);
        setLayout(null);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setTitle("New Account Application Form");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);


        //atm image
        ImageIcon atm = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg") );
        Image atmResized = atm.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        atm = new ImageIcon(atmResized);
        JLabel atmImg = new JLabel(atm);

        JLabel instruction = new JLabel("Please Select Your Transaction");
        instruction.setFont(new Font("System", Font.BOLD,16));
        instruction.setForeground(Color.WHITE);

        deposit = new JButton("Deposit");

        withdrawal = new JButton("Withdrawal");
        fastCash = new JButton("Fast Cash");
        miniStmt = new JButton("Mini Statement");
        pinChange = new JButton("Pin Change");
        balanceEnq = new JButton("Balance Enquiry");
        exit = new JButton("Exit");

        //adding objects to frame
        add(atmImg);
        atmImg.setBounds(0,0,900,800);

        atmImg.add(instruction);
        instruction.setBounds(225,250,700,35);

        atmImg.add(deposit);
        deposit.setBounds(170,365,130,30);

        atmImg.add(withdrawal);
        withdrawal.setBounds(370,365,130,30);

        atmImg.add(fastCash);
        fastCash.setBounds(170,400,130,30);

        atmImg.add(miniStmt);
        miniStmt.setBounds(370,400,130,30);

        atmImg.add(pinChange);
        pinChange.setBounds(170,435,130,30);

        atmImg.add(balanceEnq);
        balanceEnq.setBounds(370,435,130,30);

        atmImg.add(exit);
        exit.setBounds(370,470,130,30);

        //adding action listener
        deposit.addActionListener(this);
        withdrawal.addActionListener(this);
        fastCash.addActionListener(this);
        miniStmt.addActionListener(this);
        balanceEnq.addActionListener(this);
        pinChange.addActionListener(this);
        exit.addActionListener(this);

    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == exit){ //for exit

            System.exit(0);

        }else if(ae.getSource() == deposit){ //for deposit

            new deposit(loginData);

        }else if(ae.getSource() == withdrawal){ //for withdrawal

            new withdrawal(loginData);

        }else if(ae.getSource() == fastCash){ //for fastCash

            new fastCash(loginData);

        } else if (ae.getSource() == miniStmt) { //for mini statement

            new ministmt(loginData);

        } else if(ae.getSource() == balanceEnq){ // for balance Enquiry

            new balanceEnq(loginData);

        } else if (ae.getSource() == pinChange) { // for pin change

            new pinChange(loginData);

        }
    }

    public static void main(String[] args){

        Document neel = new Document("Card Number",545431136412852l);
        neel.put("Pin",6199);
        new transactions(neel);
    }
}