import com.mysql.cj.protocol.Resultset;
import com.sun.tools.jconsole.JConsoleContext;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.TimerTask;
import java.util.Timer;

class Swing extends JFrame implements ActionListener{
    ImageIcon icon;
    Container window;
    JPanel Form, dataSheet,imageContainer;
    JTable dataTable;
    DefaultTableModel tableModel;
    JLabel formTitle, Image, FName, LName, Mobile ,Gender,result,roll,sqlCon , sqlConState;
    JTextField FNameText, LNameText, tMobile,troll;
    JRadioButton male,female;
    ButtonGroup gender;
    JButton submit,reset,dataSheets;
    String SqlState;

    Connection con;
    Statement statement;

    Swing() {
        jdbc();
        initFrame();
        datasheet();
        initFetch();
        App();
    }

    public void jdbc() {
        String url = "jdbc:mysql://localhost:3306/Student-DataEntry-Form";
        String userName = "root";
        String userPass = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try {
            con = DriverManager.getConnection(url,userName,userPass);
            System.out.println("SQL Connected...");
            SqlState = "Connected...";
            statement = con.createStatement();
            statement.execute("create table if not exists Student(name varchar(255),roll varchar(100) primary key,phone varchar(100),gender varchar(100));");
        }catch (SQLException e){
            System.out.println("Sql error");
            SqlState = "Disconnected...";
        }
    }



    public void initFetch(){
        try{
            ResultSet result = statement.executeQuery("Select * from student");
            while (result.next()){
                String name = result.getString("name").toUpperCase();
                String roll = result.getString("roll");
                String phone = result.getString("phone");
                tableModel.addRow(new Object[]{name,roll,phone});
            }
        }catch (Exception Query){
            System.out.println(Query);
            System.out.println("query error");
        }
    }
    public void initFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(900,250,850,600); //        frame.setSize(500,600); frame.setLocation(750,250);
        setTitle("Register Form");
        setResizable(false);
//        getContentPane().setBackground(new Color(75, 84, 209));
        this.icon = new ImageIcon(getClass().getResource("gai.png"));
        this.setIconImage(icon.getImage());
    }

    public void datasheet (){


        dataSheet = new JPanel(new BorderLayout());
        dataSheet.setBounds(400,0,450,600);
        dataSheet.setBackground(Color.WHITE);
        dataSheet.setVisible(false);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Roll");
        tableModel.addColumn("Phone");
        dataTable = new JTable(tableModel);
        dataTable.getTableHeader().setLayout(null);
        dataTable.getTableHeader().setBounds(0,60,450,50);
        dataTable.getTableHeader().setBackground(new Color(15, 56, 102));
        dataTable.getTableHeader().setForeground(Color.white);
        dataTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        dataTable.setFont(new Font("Arial", Font.PLAIN, 15));
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        dataTable.getColumnModel().getColumn(2).setPreferredWidth(250);
        dataTable.setBounds(0,40,450,600);
        dataTable.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(dataTable);

        dataSheet.add(scrollPane);

    }




    public void App(){
        window = this.getContentPane();
        window.setLayout(null);

        Form = new JPanel(new BorderLayout());
        Form.setBounds(0,0,400,600);
        Form.setBackground(new Color(75, 84, 209));
        Form.setLayout(null);

        imageContainer = new JPanel(new BorderLayout());
        imageContainer.setBounds(400,0,450,600);
        imageContainer.setBackground(Color.WHITE);
        imageContainer.setLayout(null);
        ImageIcon img = new ImageIcon(getClass().getResource("icons/reg.jpg"));
        Image = new JLabel(img);
        Image.setBounds(0,50,400,400);
        imageContainer.add(Image , BorderLayout.CENTER);


        formTitle = new JLabel("Student Data Entry Form");
        formTitle.setBounds(50,30,300,50);
        formTitle.setForeground(Color.white);
        formTitle.setFont(new Font("Arial" ,Font.BOLD, 23));
        Form.add(formTitle);

        //First Name;
        FName = new JLabel("First Name:");
        FName.setForeground(Color.white);
        FName.setFont(new Font("Arial", Font.PLAIN, 20));
        FName.setSize(200, 20);
        FName.setLocation(50, 100);
        FName.setFont(new Font("Arial",Font.BOLD,14));
        Form.add(FName);

        FNameText = new JTextField();
        FNameText.setFont(new Font("Arial", Font.PLAIN, 15));
        FNameText.setSize(200, 20);
        FNameText.setLocation(155, 100);
        Form.add(FNameText);

        LName = new JLabel("Last Name");
        LName.setForeground(Color.white);
        LName.setFont(new Font("Arial", Font.PLAIN, 20));
        LName.setSize(100, 20);
        LName.setLocation(50, 150);
        LName.setFont(new Font("Arial",Font.BOLD,14));
        Form.add(LName);

        LNameText = new JTextField();
        LNameText.setFont(new Font("Arial", Font.PLAIN, 15));
        LNameText.setSize(200, 20);
        LNameText.setLocation(155, 150);
        Form.add(LNameText);

        roll = new JLabel("Roll :");
        roll.setForeground(Color.white);
        roll.setFont(new Font("Arial", Font.PLAIN, 20));
        roll.setSize(100, 20);
        roll.setLocation(50, 200);
        roll.setFont(new Font("Arial",Font.BOLD,14));
        Form.add(roll);

        troll = new JTextField();
        troll.setFont(new Font("Arial", Font.PLAIN, 15));
        troll.setSize(200, 20);
        troll.setLocation(155, 200);
        Form.add(troll);

        Mobile = new JLabel("Mobile:");
        Mobile.setForeground(Color.white);
        Mobile.setFont(new Font("Arial", Font.PLAIN, 20));
        Mobile.setSize(100, 20);
        Mobile.setLocation(50, 250);
        Mobile.setFont(new Font("Arial",Font.BOLD,14));
        Form.add(Mobile);

        tMobile = new JTextField();
        tMobile.setFont(new Font("Arial", Font.PLAIN, 15));
        tMobile.setSize(200, 20);
        tMobile.setLocation(155, 250);
        Form.add(tMobile);

        Gender = new JLabel("Gender:");
        Gender.setForeground(Color.white);
        Gender.setFont(new Font("Arial", Font.PLAIN, 20));
        Gender.setSize(100, 20);
        Gender.setLocation(50, 300);
        Gender.setFont(new Font("Arial",Font.BOLD,14));
        Form.add(Gender);

        male = new JRadioButton("Male");
        male.setFont(new Font("Arial", Font.PLAIN, 15));
        male.setSize(75, 20);
        male.setBackground(new Color(75, 84, 209));
        male.setForeground(Color.white);
        male.setBorder(null);
        male.setLocation(155, 300);
        Form.add(male);

        female = new JRadioButton("Female");
        female.setFont(new Font("Arial", Font.PLAIN, 15));
        female.setSelected(false);
        female.setSize(80, 20);
        female.setBackground(new Color(75, 84, 209));
        female.setForeground(Color.white);
        female.setBorder(null);
        female.setLocation(240, 300);
        Form.add(female);

        gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);

        submit = new JButton("Submit");
        submit.setBounds(50,350, 100,20);
        submit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submit.addActionListener(this);
        Form.add(submit);submit = new JButton("Submit");



        reset = new JButton("Reset");
        reset.setBounds(160,350, 100,20);
        reset.setCursor(new Cursor(Cursor.HAND_CURSOR));
        reset.addActionListener(this);
        Form.add(reset);


        dataSheets = new JButton("Data Sheet");
        dataSheets.setCursor(new Cursor(Cursor.HAND_CURSOR));
        dataSheets.setBounds(270,350, 100,20);
        dataSheets.addActionListener(this);
        Form.add(dataSheets);

        result = new JLabel("");
        result.setBounds(50,400, 400,20);
        result.setFont(new Font("Arial", Font.BOLD, 20));
        result.setForeground(Color.white);
        Form.add(result);

        sqlCon = new JLabel("SQL:");
        sqlCon.setBounds(50,500, 400,20);
        sqlCon.setFont(new Font("Arial", Font.BOLD, 20));
        sqlCon.setForeground(Color.white);
        Form.add(sqlCon);

        sqlConState = new JLabel(SqlState);
        sqlConState.setBounds(100,500, 400,20);
        sqlConState.setFont(new Font("Arial", Font.BOLD, 20));
        sqlConState.setForeground(Color.white);
        Form.add(sqlConState);
        window.add(Form);
        window.add(dataSheet);
        window.add(imageContainer);
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == reset){
            FNameText.setText("");
            LNameText.setText("");
            tMobile.setText("");
            result.setText("Clear Successfully...");
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    result.setText("");
                }
            };

            timer.schedule(task,3000);
        } else if (e.getSource() == dataSheets) {
            if (dataSheet.isVisible()==false) {
                dataSheet.setVisible(true);
                dataSheets.setText("Hide");
            }else {
                dataSheet.setVisible(false);
                dataSheets.setText("Data Sheet");

            }
        } else {
            try{
                String FullName = FNameText.getText() + " " + LNameText.getText();
                statement.execute("insert into student(name,roll,phone) values('"+FullName + "','"+troll.getText()+"','"+tMobile.getText()+"'); ");
            tableModel.addRow(new Object[]{FNameText.getText(),troll.getText(),tMobile.getText()});
                result.setText("Added Successfully...");
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        result.setText("");
                    }
                };
                timer.schedule(task,3000);
                FNameText.setText(null);
                LNameText.setText(null);
                troll.setText(null);
                tMobile.setText(null);
            }catch (Exception insert){
                if(insert.getMessage() == "Duplicate entry 'f' for key 'PRIMARY'"){
                System.out.println("insert error");
                result.setText("This roll already exists...");
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        result.setText("");
                    }
                };
                timer.schedule(task,3000);
                }else{
                result.setText("This roll already exists...");
                Timer timer = new Timer();
                TimerTask task = new TimerTask() {
                    @Override
                    public void run() {
                        result.setText("");
                    }
                };
                timer.schedule(task, 3000);
            }
            }

        }
    }

    public void runApp (){
        setVisible(true);
    }
}

public class Main  {
    public static void main(String[] args) {
        Swing Form = new Swing();
        Form.runApp();

    }
}
