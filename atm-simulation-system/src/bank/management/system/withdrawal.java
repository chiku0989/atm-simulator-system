package bank.management.system;


import org.bson.Document;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class withdrawal extends JFrame implements ActionListener {
    JTextField amount;
    JButton back,withdraw;

    Document loginData;
    withdrawal(Document loginData){
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


        JLabel text = new JLabel("Enter the amount you want to withdraw");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));

        amount = new JTextField();
        amount.setFont(new Font("System",Font.BOLD,22));

        withdraw = new JButton("Withdraw");
        withdraw.setFont(new Font("System",Font.BOLD,16));

        back = new JButton("Back");
        back.setFont(new Font("System",Font.BOLD,16));


        //adding objects to frame
        add(atmImg);
        atmImg.setBounds(0,0,900,800);

        atmImg.add(text);
        text.setBounds(185,250,700,35);

        atmImg.add(amount);
        amount.setBounds(185,310,300,30);

        atmImg.add(withdraw);
        withdraw.setBounds(370,435,130,30);

        atmImg.add(back);
        back.setBounds(370,470,130,30);

        withdraw.addActionListener(this);
        back.addActionListener(this);
    }


    public void actionPerformed(ActionEvent ae){

        if(ae.getSource() == withdraw){

            int verifyPin =  Integer.parseInt(JOptionPane.showInputDialog("Enter your Pin"));


            if(verifyPin == (int)loginData.get("Pin")){
               int wdlValue;
               long  cardNo = loginData.getLong("Card Number");

               conn c = new conn();

               try{
                   wdlValue = Integer.parseInt(amount.getText());
                   c.chooseCollection("balance");
                   Document currentUser = c.findUser(loginData);


                   int oldBalance = (int)currentUser.get("Balance");
                   System.out.println(oldBalance);
                   if(oldBalance < wdlValue){
                       JOptionPane.showMessageDialog(null,"Insufficient Balance");
                   }else{
                       int finalVal = oldBalance-wdlValue;
                       c.updateBalance(loginData,finalVal);
                       JOptionPane.showMessageDialog(null,"Rs "+wdlValue+" withdrawal successfully");
                       c.updateTransaction(cardNo,wdlValue,"Withdrawal");
                       setVisible(false);
                   }
               }catch(Exception e){
                   JOptionPane.showMessageDialog(null,"Please Enter Valid Amount");
               }finally {

                   c.close();
               }
            }else{
               JOptionPane.showMessageDialog(null,"Invalid Pin");
            }





        }else if(ae.getSource() == back){
            setVisible(false);
        }

    }

    public static void main(String[] a){
        Document neel = new Document("Card Number",545431136412852l);
        neel.put("Pin",6199);
        new withdrawal(neel);
    }

}
