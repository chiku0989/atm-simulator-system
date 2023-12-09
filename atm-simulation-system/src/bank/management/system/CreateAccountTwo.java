package bank.management.system;


//libs

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.bson.Document;

public class CreateAccountTwo extends JFrame implements ActionListener {

    //instance variables
    //drop down menu options
    String[] casteVal = {"General","OBC","ST","SC"};
    String[] religionVal = {"Hindu","Muslim","Sikh","Jain","Christian","Other"};
    String[] incomeCat = {"Null","1LPA - 5LPA","5LPA-10LPA","10LPA-20LPA",">20LPA"};
    String[] eduCat ={"Non-Graduate","Graduate","Post-Graduate","Doctrate"};

   //input text field
    JTextField occupationText, aadhaarText, panText;

   //drop down menu
    JComboBox religionType, casteType,incomeType,eduType;

    //radio buttons
    JRadioButton srYes, srNo, newAcc, oldAcc;

    //normal button
    JButton next;

    //bson file
    Document data;
    CreateAccountTwo(Document data){
        //basic swing frame configuration
        this.data = data;

        setLayout(null);
        setSize(850,800);
        setLocationRelativeTo(null);
        setTitle("New Account Application Form");
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);

        //Creating Objects for frame

        JLabel AdditionalDetails = new JLabel("Page 2 : Additional Details");
        AdditionalDetails.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel religion = new JLabel("Religion :");
        religion.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel category = new JLabel("Category :");
        category.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel income = new JLabel("Income :");
        income.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel edu = new JLabel("Educational");
        edu.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel qualification = new JLabel("Qualification :");
        qualification.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel occupation = new JLabel("Occupation :");
        occupation.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel pan = new JLabel("PAN number :");
        pan.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel aadhaar = new JLabel("Aadhaar number :");
        aadhaar.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel srCitizen = new JLabel("Senior Citizen :");
        srCitizen.setFont(new Font("Raleway",Font.BOLD,22));

        JLabel existing = new JLabel("Existing Account :");
        existing.setFont(new Font("Raleway",Font.BOLD,22));



        religionType = new JComboBox(religionVal);


        casteType = new JComboBox(casteVal);


        incomeType = new JComboBox(incomeCat);

         eduType = new JComboBox(eduCat);


        occupationText = new JTextField();
        occupationText.setFont(new Font("Raleway",Font.BOLD,22));

        panText = new JTextField();
        panText.setFont(new Font("Raleway",Font.BOLD,22));

        aadhaarText = new JTextField();
        aadhaarText.setFont(new Font("Raleway",Font.BOLD,22));


        srYes = new JRadioButton("Yes");
        srYes.setBackground(Color.WHITE);

        srNo = new JRadioButton("No");
        srNo.setBackground(Color.WHITE);



        oldAcc = new JRadioButton("Yes");
        oldAcc.setBackground(Color.WHITE);

        newAcc = new JRadioButton("No");
        newAcc.setBackground(Color.WHITE);

        next = new JButton("Next");
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Raleway",Font.BOLD,17));


        //adding and positioning objects in frame

        add(AdditionalDetails);
        AdditionalDetails.setBounds(290,80,400,30);

        add(religion);
        religion.setBounds(100,140,100,30);
        add(religionType);
        religionType.setBounds(300,140,400,30);

        add(category);
        category.setBounds(100,190,200,30);
        add(casteType);
        casteType.setBounds(300,190,400,30);


        add(income);
        income.setBounds(100,240,200,30);
        add(incomeType);
        incomeType.setBounds(300,240,400,30);


        add(edu);
        edu.setBounds(100,290,200,30);

        add(qualification);
        qualification.setBounds(100,320,200,30);
        add(eduType);
        eduType.setBounds(300,320,400,30);

        add(occupation);
        occupation.setBounds(100,390,200,30);
        add(occupationText);
        occupationText.setBounds(300,390,400,30);

        add(pan);
        pan.setBounds(100,440,200,30);
        add(panText);
        panText.setBounds(300,440,400,30);


        add(aadhaar);
        aadhaar.setBounds(100,490,200,30);
        add(aadhaarText);
        aadhaarText.setBounds(300,490,400,30);

        add(srCitizen);
        srCitizen.setBounds(100,540,200,30);
        add(srYes);
        srYes.setBounds(300,540,60,30);
        add(srNo);
        srNo.setBounds(450,540,120,30);

        add(existing);
        existing.setBounds(100,590,200,30);
        add(oldAcc);
        oldAcc.setBounds(300,590,100,30);
        add(newAcc);
        newAcc.setBounds(450,590,100,30);

        add(next);
        next.setBounds(620,660,80,30);

        //adding action listener

        next.addActionListener(this);





        //ButtonGroups
        ButtonGroup srCitizenGroup = new ButtonGroup();
        srCitizenGroup.add(srYes);
        srCitizenGroup.add(srNo);

        ButtonGroup accGroup = new ButtonGroup();
        accGroup.add(oldAcc);
        accGroup.add(newAcc);


    }

    public void actionPerformed(ActionEvent ae){

        //Storing user input into string variables
        String religion = (String)religionType.getSelectedItem();
        String category = (String)casteType.getSelectedItem();
        String income = (String)incomeType.getSelectedItem();
        String edu = (String)eduType.getSelectedItem();
        String occupation = occupationText.getText();
        String aadhaar = aadhaarText.getText();
        String pan = panText.getText();
        String srCitizen = null;

        if(srYes.isSelected()){
            srCitizen = "Yes";
        }else if(srNo.isSelected()){
            srCitizen = "No";
        }
        String existingAcc = null;
        if(oldAcc.isSelected()){
            existingAcc = "Yes";
        }else if(newAcc.isSelected()){
            existingAcc = "No";
        }
        try{
            if(religion.equals("")|| category.equals("") || income.equals("") || edu.equals("") || occupation.equals("") || aadhaar.equals("") || pan.equals("") || srCitizen.equals("")){
                System.out.println("All Fields are required");
            }
            else {
                data.put("Religion", religion);
                data.put("Category",category);
                data.put("Income",income);
                data.put("Education",edu);
                data.put("Aadhaar no", aadhaar);
                data.put("Pan no",pan);
                data.put("Senior citizen", srCitizen);
                data.put("Existing account", existingAcc);

                setVisible(false);
                new CreateAccountThree(data);
            }
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public static void main(String[] a){
        Document doc = null;
        new CreateAccountTwo(doc);
    }

}
