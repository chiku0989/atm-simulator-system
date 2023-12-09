package bank.management.system;

import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.swing.*;
import java.awt.*;

public class ministmt extends JFrame{

    ministmt(Document loginData){
        //basic swing configuration
        setTitle("Mini Statement");
        setSize(400,600);
        setLocationRelativeTo(null);
        setVisible(true);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bank.png")); //configuring Image
        Image icon = i1.getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT);
        i1 = new ImageIcon(icon);

        JLabel image = new JLabel(i1);

        JLabel bankName = new JLabel("VIRTUAL BANK");
        bankName.setFont(new Font("Raleway",Font.BOLD,16));


        //getting card number to print on frame
        long cardNo = loginData.getLong("Card Number");
        String ScardNo = String.valueOf(cardNo);
        String hiddenCardNo = ScardNo.substring(0,4)+"-XXXX-XXXX-"+ScardNo.substring(11,15);
        System.out.println(hiddenCardNo);
        JLabel cardNoLabel = new JLabel("Card Number : "+hiddenCardNo);
        cardNoLabel.setFont(new Font("Raleway",Font.BOLD,16));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        conn c = new conn();
        c.chooseCollection("transactions");



        try{
            FindIterable<Document> documents = c.collection.find(Filters.eq("Card Number",cardNo));
            for(Document document: documents){
                String time = String.valueOf(document.get("Time Stamp"));
                String type = String.valueOf(document.get("Type"));
                int value = document.getInteger("Value");
                String Svalue;
                if (type == "Withdrawal"){
                    Svalue ="-"+value;
                }else if( type == "Deposit"){
                    Svalue = "+"+value;
                }


                JLabel label = new JLabel(time+" "+type+" "+value);
                panel.add(label);
            }
        }catch(Exception e){
            System.out.println(e);
        }
        finally{
            c.close();
        }

         JScrollPane transactions = new JScrollPane(panel);
         transactions.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        int balance = 0;
        conn c1 = new conn();
        try{
            c1.chooseCollection("balance");
            Document user=  c1.findUser(loginData);
            balance = (int) user.get("Balance");
        }catch(Exception e){
            System.out.println("Unable to generate balance");
        }finally {
            c1.close();
        }

        JLabel balanceLabel = new JLabel(String.valueOf("Total Balance : "+balance));
        balanceLabel.setFont(new Font("Raleway",Font.BOLD,16));
        balanceLabel.setForeground(Color.WHITE);




        //adding objects to frame;
        add(image);
        image.setBounds(50,20,50,50);

        add(bankName);
        bankName.setBounds(150,0,200,100);

        add(cardNoLabel);
        cardNoLabel.setBounds(50,100,300,20);

        add(transactions);
        transactions.setBounds(50,180,300,300);

        add(balanceLabel);
        balanceLabel.setBounds(0,0,00,0);



    }

    public static void main(String[] a){
        Document neel = new Document("Card Number",545431136412852l);
        neel.put("Pin",6199);
        new ministmt(neel);
    }
}
