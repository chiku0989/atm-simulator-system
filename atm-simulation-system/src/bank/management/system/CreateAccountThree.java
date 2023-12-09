package bank.management.system;


//libs
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import com.mongodb.Mongo;
import com.mongodb.client.*;
import org.bson.Document;

public class CreateAccountThree extends JFrame implements ActionListener {

    //instance variables
    //account type radio button
    JRadioButton sa,ca,fd,ra;

    //normal button
    JButton submit,cancel;

    //services checkbox
    JCheckBox c1,c2,c3,c4,c5,c6,c7;

    //bson object file
    Document data;
    CreateAccountThree(Document data){
        //basic swing frame configuration
        this.data = data;
        setLayout(null);
        setSize(850,800);
        setLocationRelativeTo(null);
        setTitle("New Account Application Form");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);


        //creating objects for frame
        JLabel accountDetails = new JLabel("Page 3 : Account details");
        accountDetails.setFont(new Font("Raleway",Font.BOLD, 22));

        JLabel accType = new JLabel("Account Type : ");
        accType.setFont(new Font("Raleway",Font.BOLD, 22));

        JLabel cardNo = new JLabel("Card Number : ");
        cardNo.setFont(new Font("Raleway",Font.BOLD, 22));

        JLabel cardNoVal = new JLabel("XXXX-XXXX-XXXX-XXXX");
        cardNoVal.setFont(new Font("Raleway",Font.BOLD, 22));

        JLabel cardInfo = new JLabel("Your 16 Digit Card No");
        cardInfo.setFont(new Font("Raleway",Font.BOLD, 12));

        JLabel cardPin = new JLabel("PIN : ");
        cardPin.setFont(new Font("Raleway",Font.BOLD, 22));

        JLabel cardPinVal = new JLabel("XXXX");
        cardPinVal.setFont(new Font("Raleway",Font.BOLD, 22));

        JLabel pinInfo = new JLabel("Your 4 digit pin");
        pinInfo.setFont(new Font("Raleway",Font.BOLD, 12));

        JLabel servicesReq = new JLabel("Services Required : ");
        servicesReq.setFont(new Font("Raleway",Font.BOLD, 22));

        sa = new JRadioButton("Savings Account");
        sa.setFont(new Font("Raleway",Font.BOLD, 16));
        sa.setBackground(Color.WHITE);

        ca = new JRadioButton("Current Account");
        ca.setFont(new Font("Raleway",Font.BOLD, 16));
        ca.setBackground(Color.WHITE);

        fd = new JRadioButton("Fixed Deposit Account");
        fd.setFont(new Font("Rale way",Font.BOLD, 16));
        fd.setBackground(Color.WHITE);

        ra = new JRadioButton("Recurring Deposit Account");
        ra.setFont(new Font("Raleway",Font.BOLD, 16));
        ra.setBackground(Color.WHITE);

        c1 = new JCheckBox("ATM Card");
        c1.setFont(new Font("Raleway",Font.BOLD, 16));
        c1.setBackground(Color.WHITE);
        c1.setSelected(true);

        c2 = new JCheckBox("Internet Banking");
        c2.setFont(new Font("Raleway",Font.BOLD, 16));
        c2.setBackground(Color.WHITE);

        c3 = new JCheckBox("Mobile Banking");
        c3.setFont(new Font("Raleway",Font.BOLD, 16));
        c3.setBackground(Color.WHITE);

        c4 = new JCheckBox("E-Mail & SMS Alert");
        c4.setFont(new Font("Raleway",Font.BOLD, 16));
        c4.setBackground(Color.WHITE);

        c5 = new JCheckBox("Cheque Book");
        c5.setFont(new Font("Raleway",Font.BOLD, 16));
        c5.setBackground(Color.WHITE);

        c6 = new JCheckBox("E-Statement");
        c6.setFont(new Font("Raleway",Font.BOLD, 16));
        c6.setBackground(Color.WHITE);

        c7 = new JCheckBox("I hereby declare that the above entered details are correct to the best of my knowledge");
        c7.setFont(new Font("Raleway",Font.BOLD, 12));
        c7.setBackground(Color.WHITE);

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setFont(new Font("Raleway",Font.BOLD, 14));

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setFont(new Font("Raleway",Font.BOLD, 14));

        //adding objects to frame
        add(accountDetails);
        accountDetails.setBounds(280,40,400,40);

        add(accType);
        accType.setBounds(100,140,200,30);

        add(sa);
        sa.setBounds(100,180,250,20);

        add(ca);
        ca.setBounds(350,180,250,20);

        add(fd);
        fd.setBounds(100,220,250,20);

        add(ra);
        ra.setBounds(350,220,250,20);

        add(cardNo);
        cardNo.setBounds(100,300,200,30);

        add(cardNoVal);
        cardNoVal.setBounds(330,300,300,30);

        add(cardInfo);
        cardInfo.setBounds(100,330,300,20);

        add(cardPin);
        cardPin.setBounds(100,370,200,30);

        add(cardPinVal);
        cardPinVal.setBounds(330,370,200,30);

        add(pinInfo);
        pinInfo.setBounds(100,400,300,20);

        add(servicesReq);
        servicesReq.setBounds(100,450,250,30);

        add(c1);
        c1.setBounds(100,500,200,30);

        add(c2);
        c2.setBounds(350,500,200,30);

        add(c3);
        c3.setBounds(100,550,200,30);

        add(c4);
        c4.setBounds(350,550,200,30);

        add(c5);
        c5.setBounds(100,600,200,30);

        add(c6);
        c6.setBounds(350,600,200,30);

        add(c7);
        c7.setBounds(100,650,600,30);

        add(submit);
        submit.setBounds(220,700,100,30);


        add(cancel);
        cancel.setBounds(420,700,100,30);



        //adding action listener
        submit.addActionListener(this);
        cancel.addActionListener(this);

        //button group

        ButtonGroup accGroup =  new ButtonGroup();
        accGroup.add(sa);
        accGroup.add(ca);
        accGroup.add(fd);
        accGroup.add(ra);


    }

    public void actionPerformed(ActionEvent ae){

        //submit button functionality
        if(ae.getSource() == submit){

            //Storing user input into variables
            String accountType = null;

            if(sa.isSelected()){
                accountType = "Savings Account";
            }
            else if(ca.isSelected()){
                accountType = "Current Account";
            }
            else if(fd.isSelected()){
                accountType = "Fixed Deposit Account";
            }
            else if(ra.isSelected()){
                accountType = "Recurring Deposit Account";
            }

            //generating a random card number

            long cardNo;
             Random random  = new Random();
             cardNo = (random.nextInt(90)+10)+5454311364128000L;

            //generating a random pin

            int pin;
            pin = random.nextInt(9000)+1000;

            //getting selected services

            Boolean atm = null;
            if(c1.isSelected()){
                atm = true;
            }
            Boolean intBanking = null;
            if(c2.isSelected()){
                intBanking = true;
            }
            Boolean mobBanking = null;
            if(c3.isSelected()){
                mobBanking = true;
            }
            Boolean eMail_sms = null;
            if(c4.isSelected()){
                eMail_sms = true;
            }
            Boolean cheque_book = null;
            if(c5.isSelected()){
                cheque_book = true;
            }
            Boolean eStatement = null;
            if(c6.isSelected()){
                eStatement = true;
            }


            try{

                //if account type not selcted then pop-up will show
                if(accountType == null){
                    JOptionPane.showMessageDialog(null,"Select Account Type");
                }

                //if terms and conditions not selected then pop-up will show up
                else if (!c7.isSelected()) {
                    JOptionPane.showMessageDialog(null,"Agree to the terms of the bank");
                }

                //if required things selected then this will work
                //putting data in BSON format
                else{
                    data.put("Account Type", accountType);
                    data.put("Card Number", cardNo);
                    data.put("Pin",pin);
                    data.put("ATM card", atm);
                    data.put("Internet Banking", intBanking);
                    data.put("Mobile Banking", mobBanking);
                    data.put("E-Mail & SMS alert", eMail_sms);
                    data.put("Cheque Book", cheque_book);
                    data.put("E-Statement", eStatement);


                    Document balance = new Document();
                    balance.put("Card Number", cardNo);
                    balance.put("Pin",pin);
                    balance.put("Balance",0);

                    //calling conn class to enter data into database
                    conn c = new conn();

                    try{

                        c.chooseCollection("accounts");
                        c.insertData(data);

                        c.chooseCollection("balance");
                        c.insertData(balance);

                    }catch(Exception e){
                        System.out.println(e);
                    }finally {
                        c.close();
                    }

                    //converting card num to string for user display
                    String cardNum = Long.toString(cardNo);

                    //pop-up to show new card number and pin to user
                    JOptionPane.showMessageDialog(null,"Card Number : "+cardNum.substring(0,4)+"-"+cardNum.substring(4,8)+"-"+cardNum.substring(8,12)+"-"+cardNum.substring(12,16)+"\n PIN : "+pin);

                    setVisible(false);
                    new login();
                    new deposit(balance);


                }

            }catch(Exception e){
                System.out.println(e);
            }





        }
        //cancel button functionality
        else if(ae.getSource() == cancel){


            setVisible(false);
            new login();
        }
    }
    public static void main(String[] a){
        Document Doc = new Document(" ", " ");
        new CreateAccountThree(Doc);
    }
}
