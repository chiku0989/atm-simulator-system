package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.bson.Document;



public class balanceEnq extends JFrame implements ActionListener{

    JButton back;

    balanceEnq(Document document){
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

        int balance = 0;
        conn c = new conn();

        try{
            c.chooseCollection("balance");
            Document user =  c.findUser(document);
            balance = (int)user.get("Balance");
        } catch (Exception e){
            System.out.println(e);
        } finally {
            c.close();
        }




        JLabel label = new JLabel("Your Account Balance : "+balance);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System",Font.BOLD,22));

        back = new JButton("back");



        add(atmImg);
        atmImg.setBounds(0,0,900,800);

        atmImg.add(label);
        label.setBounds(185,260,700,35);

        atmImg.add(back);
        back.setBounds(370,470,130,30);


        back.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
            setVisible(false);
    }

    public static void main(String[] a){
        Document neel = new Document("Card Number",545431136412852l);
        neel.put("Pin",6199);
        new balanceEnq(neel);
    }
}
