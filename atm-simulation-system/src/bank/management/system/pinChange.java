package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import org.bson.Document;
import org.bson.Document.*;


public class pinChange extends JFrame implements ActionListener {


    Document loginData;

    JPasswordField passText, re_enterPassText;
    JButton change,back;


    pinChange(Document loginData){
        this.loginData = loginData;


        //basic swing configuration
        setUndecorated(true);
        setLayout(null);
        setSize(900, 800);
        setLocationRelativeTo(null);
        setTitle("New Account Application Form");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

        //creating objects for the frame


        //atm image
        ImageIcon atm = new ImageIcon(ClassLoader.getSystemResource("icon/atm.jpg") );
        Image atmResized = atm.getImage().getScaledInstance(900, 900, Image.SCALE_DEFAULT);
        atm = new ImageIcon(atmResized);
        JLabel atmImg = new JLabel(atm);

        JLabel passChange = new JLabel("Change Pin");
        passChange.setFont(new Font("System",Font.BOLD,22));
        passChange.setForeground(Color.WHITE);

        JLabel enterPwd = new JLabel("New Password :");
        enterPwd.setFont(new Font("System",Font.BOLD,16));
        enterPwd.setForeground(Color.WHITE);


        JLabel reenterPwd = new JLabel("Re-Enter Password :");
        reenterPwd.setFont(new Font("System",Font.BOLD,16));
        reenterPwd.setForeground(Color.WHITE);

        passText = new JPasswordField();
        re_enterPassText = new JPasswordField();

        change = new JButton("Change");
        change.setFont(new Font("System",Font.BOLD,16));

        back = new JButton("Back");
        back.setFont(new Font("System",Font.BOLD,16));



        //adding objects to frame


        add(atmImg);
        atmImg.setBounds(0,0,900,800);

        atmImg.add(passChange);
        passChange.setBounds(280,230,700,35);

        atmImg.add(enterPwd);
        enterPwd.setBounds(170,285,180,25);

        atmImg.add(passText);
        passText.setBounds(340,285,160,25);

        atmImg.add(reenterPwd);
        reenterPwd.setBounds(170,330,180,25);

        atmImg.add(re_enterPassText);
        re_enterPassText.setBounds(340,330,160,25);

        atmImg.add(back);
        back.setBounds(370,435,130,30);

        atmImg.add(change);
        change.setBounds(370,470,130,30);


        //adding action listener
        back.addActionListener(this);
        change.addActionListener(this);

    }



    //function which will be called on the button click
    public void actionPerformed(ActionEvent ae){


        if(ae.getSource() == change){

            //if change button is pressed
            int oldPass;

            try{
                //getting current password from user and verifying it
                oldPass = Integer.parseInt( JOptionPane.showInputDialog("Enter Current Pin"));
                if(oldPass == (int)loginData.get("Pin")){

                    //if verification is success
                    int pass;
                    int reEnterPass;
                    try{

                        //getting user input of new password
                        pass = Integer.parseInt(passText.getText());
                        reEnterPass = Integer.parseInt(re_enterPassText.getText());

                        if(pass == reEnterPass){
                            //if password and re-enter password matches
                            conn c = new conn();
                            c.changePin(loginData,pass);
                            c.close();
                            JOptionPane.showMessageDialog(null,"Password Changed");
                            setVisible(false);
                            new login();


                        }else{

                            //if password and re-enter password don't match
                            JOptionPane.showMessageDialog(null,"Pin Mismatch");
                        }

                    }catch(Exception e1){
                        //if user puts anything but number in new password field
                        JOptionPane.showMessageDialog(null,"Pin should be number");
                    }

                }else{
                    //if password verification is not success

                    JOptionPane.showMessageDialog(null,"Wrong Pin");
                }
            }catch (Exception e){

                //if user enters wrong pin in current pin verification
                JOptionPane.showMessageDialog(null,"Not a Valid Pin");
            }
        }else if(ae.getSource() == back){

            //if back button is pressed
            setVisible(false);
        }

    }

    public static void main(String[] args){
        Document neel = new Document("Card Number",545431136412852l);
        neel.put("Pin",6199);
        new pinChange(neel);

    }

}
