package bank.management.system;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.bson.Document;


public class deposit extends JFrame implements ActionListener {

    JTextField amount;
    JButton back,deposit;

    Document loginData;

    deposit(Document loginData){

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


        JLabel text = new JLabel("Enter the amount you want to deposit");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));

        amount = new JTextField();
        amount.setFont(new Font("System",Font.BOLD,22));

        deposit = new JButton("Deposit");
        deposit.setFont(new Font("System",Font.BOLD,16));

        back = new JButton("Back");
        back.setFont(new Font("System",Font.BOLD,16));


        //adding objects to frame
        add(atmImg);
        atmImg.setBounds(0,0,900,800);

        atmImg.add(text);
        text.setBounds(185,250,700,35);

        atmImg.add(amount);
        amount.setBounds(185,310,300,30);

        atmImg.add(deposit);
        deposit.setBounds(370,435,130,30);

        atmImg.add(back);
        back.setBounds(370,470,130,30);

        deposit.addActionListener(this);
        back.addActionListener(this);

    }


    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == deposit){

            int depositVal;
            conn c = new conn();
            long cardNo = (long) loginData.get("Card Number");

            try{
                depositVal = Integer.parseInt(amount.getText());
                c.chooseCollection("balance");
                Document currentUser = c.findUser(loginData);

                int oldBalance = (int)currentUser.get("Balance");
                int finalBalance = oldBalance + depositVal;
                System.out.println(finalBalance);
                c.updateBalance(loginData,finalBalance);


                JOptionPane.showMessageDialog(null,"rupees "+depositVal+" added successfully to your account");
                c.updateTransaction(cardNo,depositVal,"Deposit");
                setVisible(false);

            }catch(Exception e){

                JOptionPane.showMessageDialog(null,"Please Enter Valid Amount");
            }
            finally {
                c.close();
            }

        }else if(ae.getSource() == back){
            setVisible(false);
            new transactions(loginData);
        }

    }


public static void main(String[] args){

        Document neel = new Document("Card Number",545431136412852l);
        neel.put("Pin",6199);
        new deposit(neel);
}
}
