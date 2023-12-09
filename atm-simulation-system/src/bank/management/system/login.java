package bank.management.system;
//importing packages;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.bson.Document;



public class login extends JFrame implements ActionListener {

     JButton login,clear,createAccount;
     JTextField cardText;
     JPasswordField pinText;
    login(){
        //basic swing frame configuration
        setLayout(null);
        setSize(800,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setTitle("Automated Teller Machine ");
        getContentPane().setBackground(Color.WHITE);


        //Creating Objects to add in the Frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png")); //configuring Image
        Image icon = i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
        i1 = new ImageIcon(icon);

        JLabel label = new JLabel(i1); //img

        JLabel text = new JLabel("Welcome to Virtual ATM");
        text.setFont(new Font("Osward",Font.BOLD,40));
        JLabel card = new JLabel("Card No:");
        card.setFont(new Font("Raleway",Font.BOLD,30));
        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Raleway",Font.BOLD,30));

        cardText = new JTextField();
        cardText.setFont(new Font("Arial",Font.BOLD,17));
        pinText = new JPasswordField();
        pinText.setFont(new Font("Arial",Font.BOLD,17));

        login = new JButton("LOGIN");
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);

        clear = new JButton("CLEAR");
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);

        createAccount = new JButton("CREATE ACCOUNT");
        createAccount.setBackground(Color.BLACK);
        createAccount.setForeground(Color.WHITE);





        //adding and positioning objecting in frame
        add(label);
        label.setBounds(70,10,100,100);

        add(text);
        text.setBounds(200,40,500,40);

        add(card);
        card.setBounds(120,150,400,40);

        add(pin);
        pin.setBounds(120,220,400,40);

        add(cardText);
        cardText.setBounds(300,150,230,40);

        add(pinText);
        pinText.setBounds(300,220,230,40);

        add(login);
        login.setBounds(300,300,100,30);

        add(clear);
        clear.setBounds(430,300,100,30);

        add(createAccount);
        createAccount.setBounds(300,350,230,40);

        //adding Action Listener
        login.addActionListener(this);
        clear.addActionListener(this);
        createAccount.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == clear){
            cardText.setText("");
            pinText.setText("");
        }
        else if(ae.getSource() == login){

            long cardNo;
            int pin;
            try{
                 cardNo= Long.parseLong(cardText.getText());
                pin = Integer.parseInt(pinText.getText());
            } catch(Exception e){
                System.out.println(e);
                cardNo = 0;
                pin = 0;
            }


            Document loginData = new Document();
            loginData.put("Card Number",cardNo);
            loginData.put("Pin", pin);

            conn c = new conn();
            c.chooseCollection("accounts");

            //login mechanism
            Document userData = null;
            userData =  c.findUser(loginData);
            c.close();

            if(userData == null){ //if user not found
                JOptionPane.showMessageDialog(null,"Please enter valid details");

            } else{//if user found
                new transactions(loginData);
                setVisible(false);
            }



        }
        else if(ae.getSource() == createAccount){
            setVisible(false);
            new CreateAccountOne();
        }
    }
    public static void main(String[] a){
        new login();

    }

}
