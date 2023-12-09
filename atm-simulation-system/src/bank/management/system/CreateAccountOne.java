package bank.management.system;


import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
// external libs
import com.toedter.calendar.JDateChooser;
import org.bson.Document;





public class CreateAccountOne extends JFrame implements ActionListener {

    //instance variables

    //form no
    Random random;
    long num;

    //input text field
    JTextField nameText, fnameText, emailText, addressText, cityText, stateText, pincodeText;

    //calender(external lib)
    JDateChooser date;

    //radio buttons
    JRadioButton male, female, married, unmarried;

    //normal button
    JButton next;

    //bson document
    Document data = new Document();
    CreateAccountOne(){
        //basic swing frame configuration

        setLayout(null);
        setSize(850,800);
        setLocationRelativeTo(null);
        setTitle("New Account Application Form");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

        //Creating Objects for frame
        random  = new Random();
        num = Math.abs((random.nextLong()%9000L)+1000L);
        JLabel formNo = new JLabel("Application Form No. : " + num);
        formNo.setFont(new Font("Raleway",Font.BOLD,38));

        JLabel personalDetails = new JLabel("Page 1 : Personal Details");
        personalDetails.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel name = new JLabel("Name :");
        name.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel fName = new JLabel("Father's Name :");
        fName.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel dob = new JLabel("Date of Birth :");
        dob.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel gender = new JLabel("Gender :");
        gender.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel email = new JLabel("Email Address :");
        email.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel marital = new JLabel("Marital Status :");
        marital.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel address = new JLabel("Address :");
        address.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel city = new JLabel("City :");
        city.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel state = new JLabel("State :");
        state.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel pincode = new JLabel("Pincode :");
        pincode.setFont(new Font("Raleway",Font.BOLD,22));

        nameText = new JTextField();
        nameText.setFont(new Font("Raleway",Font.BOLD,22));

        fnameText = new JTextField();
        fnameText.setFont(new Font("Raleway",Font.BOLD,22));

        emailText = new JTextField();
        emailText.setFont(new Font("Raleway",Font.BOLD,22));

        addressText = new JTextField();
        addressText.setFont(new Font("Raleway",Font.BOLD,22));

        cityText = new JTextField();
        cityText.setFont(new Font("Raleway",Font.BOLD,22));

        stateText = new JTextField();
        stateText.setFont(new Font("Raleway",Font.BOLD,22));

        pincodeText = new JTextField();
        pincodeText.setFont(new Font("Raleway",Font.BOLD,22));

        date = new JDateChooser();
        date.setForeground(Color.BLACK);

        male = new JRadioButton("Male");
        male.setBackground(Color.WHITE);

        female = new JRadioButton("Female");
        female.setBackground(Color.WHITE);

        married = new JRadioButton("Married");
        married.setBackground(Color.WHITE);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBackground(Color.WHITE);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,17));


        //adding and positioning objects in frame
        add(formNo);
        formNo.setBounds(140,20,600,40);

        add(personalDetails);
        personalDetails.setBounds(290,80,400,30);

        add(name);
        name.setBounds(100,140,100,30);
        add(nameText);
        nameText.setBounds(300,140,400,30);

        add(fName);
        fName.setBounds(100,190,200,30);
        add(fnameText);
        fnameText.setBounds(300,190,400,30);


        add(dob);
        dob.setBounds(100,240,200,30);
        add(date);
        date.setBounds(300,240,400,30);


        add(gender);
        gender.setBounds(100,290,200,30);
        add(male);
        male.setBounds(300,290,60,30);
        add(female);
        female.setBounds(450,290,120,30);
        add(email);
        email.setBounds(100,340,200,30);
        add(emailText);
        emailText.setBounds(300,340,400,30);

        add(marital);
        marital.setBounds(100,390,200,30);
        add(married);
        married.setBounds(300,390,100,30);
        add(unmarried);
        unmarried.setBounds(450,390,100,30);


        add(address);
        address.setBounds(100,440,200,30);
        add(addressText);
        addressText.setBounds(300,440,400,30);

        add(city);
        city.setBounds(100,490,200,30);
        add(cityText);
        cityText.setBounds(300,490,400,30);

        add(state);
        state.setBounds(100,540,200,30);
        add(stateText);
        stateText.setBounds(300,540,400,30);

        add(pincode);
        pincode.setBounds(100,590,200,30);
        add(pincodeText);
        pincodeText.setBounds(300,590,400,30);

        add(next);
        next.setBounds(620,660,80,30);
        next.addActionListener(this);





        //ButtonGroups
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        ButtonGroup mrgGroup = new ButtonGroup();
        mrgGroup.add(married);
        mrgGroup.add(unmarried);


    }

    //this function will be called when NEXT button will be clicked
    public void actionPerformed(ActionEvent ae){
        //storing user input in string variables
        String formNo = ""+num;
        String name = nameText.getText();
        String fName = fnameText.getText();
        String dob = ((JTextField)date.getDateEditor().getUiComponent()).getText();
        String gender = null;
        if (male.isSelected()){
            gender = "Male";
        } else if (female.isSelected()){
            gender = "Female";
        }

        String marital = null;
        if(married.isSelected()){
            marital = "Married";
        } else if(unmarried.isSelected()){
            marital = "Unmarried";
        }

        String address = addressText.getText();
        String city = cityText.getText();
        String state = stateText.getText();
        String pin = pincodeText.getText();

        //Storing user input into database
        try{
            //form validation
            //if any of the flied is empty then it will alert the user

            if(name.equals("") || fName.equals("") || dob.equals("") || gender.equals("") || marital.equals("") || address.equals("") || city.equals("") || state.equals("") || pin.equals("")){
                JOptionPane.showMessageDialog(null,"All Fields are required");
            }
            else{
                //creating a bson format of user input

                data.put("FormNo", formNo);
                data.put("Name",name);
                data.put("Father's Name",fName);
                data.put("DOB",dob);
                data.put("Gender",gender);
                data.put("Marital Status",marital);
                data.put("Address",address);
                data.put("City",city);
                data.put("State",state);
                data.put("Pincode",pin);

                setVisible(false);
                new CreateAccountTwo(data);
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] a){

        new CreateAccountOne();
    }
}
