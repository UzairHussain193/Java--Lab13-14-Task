
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.json.simple.JsonObject;
import java.io.*;

class Reg_Form extends JFrame implements ActionListener {
    Container c;
    JLabel title, name, roll, sec, batch, gender, qualification, address, country;
    static String str1, str2, str3, str4, str5, str6, str7, str8;
    JTextField tname, troll, tsec, tbatch;
    JTextArea taddress;
    JComboBox tcountry;
    JCheckBox matric, intermediate, graduate, postgraduate;
    JRadioButton male, female;
    ButtonGroup gen;
    ImageIcon icon, a;
    JButton save, print, database, clear;
    JOptionPane msgpanel;
    JsonObject Object1;
    String count[] = { "Pakistan", "China", "India", "Afghanistan" };

    public Reg_Form() {

        setTitle("Registration Form");
        setBackground(Color.BLUE);
        setBounds(300, 90, 650, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setForeground(Color.YELLOW);
        icon = new ImageIcon("1.png");
        setIconImage(icon.getImage());
        setLayout(new FlowLayout(FlowLayout.CENTER));
        setResizable(false);

        c = getContentPane();
        getContentPane().setBackground(Color.GREEN);
        c.setLayout(null);
        title = new JLabel("Registration Form");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        title.setForeground(Color.DARK_GRAY);
        title.setBackground(Color.GRAY);
        title.setBounds(150, 30, 300, 35);
        c.add(title);

        name = new JLabel("Name");
        name.setSize(190, 20);
        name.setLocation(100, 100);
        c.add(name);

        tname = new JTextField();
        tname.setSize(190, 20);
        tname.setLocation(200, 100);
        tname.setForeground(Color.BLUE);
        c.add(tname);

        roll = new JLabel("Roll No: ");
        roll.setSize(100, 20);
        roll.setLocation(100, 130);
        c.add(roll);

        troll = new JTextField();
        troll.setSize(190, 20);
        troll.setLocation(200, 130);
        troll.setForeground(Color.RED);
        c.add(troll);

        batch = new JLabel("Batch: ");
        batch.setSize(190, 20);
        batch.setLocation(100, 160);
        c.add(batch);

        tbatch = new JTextField();
        tbatch.setSize(190, 20);
        tbatch.setLocation(200, 160);
        tbatch.setForeground(Color.BLUE);
        c.add(tbatch);

        sec = new JLabel("Section: ");
        sec.setSize(190, 20);
        sec.setLocation(100, 200);
        c.add(sec);

        tsec = new JTextField();
        tsec.setSize(190, 20);
        tsec.setLocation(200, 200);
        tsec.setForeground(Color.BLUE);
        c.add(tsec);

        gender = new JLabel("Gender:");
        gender.setSize(100, 20);
        gender.setLocation(100, 230);
        c.add(gender);

        male = new JRadioButton("Male: ");
        male.setSelected(true);
        male.setSize(75, 20);
        male.setLocation(200, 230);
        c.add(male);

        female = new JRadioButton("Female: ");
        female.setSelected(false);
        female.setSize(80, 20);
        female.setLocation(275, 230);
        c.add(female);

        gen = new ButtonGroup();
        gen.add(male);
        gen.add(female);

        qualification = new JLabel("Qualification: ");
        qualification.setSize(100, 20);
        qualification.setLocation(100, 260);
        c.add(qualification);

        matric = new JCheckBox("Matric");
        matric.setSize(100, 50);
        matric.setLocation(200, 260);
        c.add(matric);

        intermediate = new JCheckBox("Intermediate");
        intermediate.setSize(100, 50);
        intermediate.setLocation(300, 260);
        c.add(intermediate);

        graduate = new JCheckBox("Graduate");
        graduate.setSize(100, 50);
        graduate.setLocation(200, 300);
        c.add(graduate);

        postgraduate = new JCheckBox("Postgraduate");
        postgraduate.setSize(120, 50);
        postgraduate.setLocation(300, 300);
        c.add(postgraduate);

        address = new JLabel("Address: ");
        address.setSize(100, 20);
        address.setLocation(100, 360);
        c.add(address);

        taddress = new JTextArea();
        taddress.setSize(200, 50);
        taddress.setLocation(200, 360);
        c.add(taddress);

        country = new JLabel("Country: ");
        country.setSize(100, 20);
        country.setLocation(100, 420);
        c.add(country);

        tcountry = new JComboBox<>(count);
        tcountry.setSize(190, 20);
        tcountry.setLocation(200, 420);
        c.add(tcountry);

        save = new JButton("SAVE");
        save.setSize(100, 30);
        save.setLocation(100, 480);
        save.setBackground(Color.BLACK);
        save.setForeground(Color.WHITE);
        save.addActionListener(this);
        c.add(save);

        print = new JButton("Print");
        print.setSize(100, 30);
        print.setLocation(230, 480);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        c.add(print);

        database = new JButton("DATABASE");
        database.setSize(100, 30);
        database.setLocation(360, 480);
        database.setBackground(Color.BLACK);
        database.setForeground(Color.WHITE);
        database.addActionListener(this);
        c.add(database);

        clear = new JButton("CLEAR");
        clear.setSize(100, 30);
        clear.setLocation(490, 480);
        clear.setBackground(Color.BLACK);
        clear.setForeground(Color.WHITE);
        clear.addActionListener(this);
        c.add(clear);

        save.addActionListener(this);
        print.addActionListener(this);
        database.addActionListener(this);
        clear.addActionListener(this);

        setVisible(true);

    }

    public void save() { // method for when save button is selected
        Writer write;
        try {
            write = new FileWriter("Task.json");
            write.write(Object1.toString());
            write.close();
        } catch (Exception a) {
            a.printStackTrace();
        }
    }

    public void print() {
        JFrame f2 = new JFrame(); // will open new frame to display details

        f2.setTitle("Showing Details");
        f2.setBackground(Color.BLACK);
        f2.setBounds(300, 90, 600, 500);
        f2.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f2.setForeground(Color.ORANGE);
        a = new ImageIcon("2.png");
        f2.setIconImage(a.getImage());
        f2.setLayout(new FlowLayout(FlowLayout.CENTER));
        f2.setResizable(false);

        f2.getContentPane().setBackground(Color.GRAY);
        f2.setLayout(null);
        JLabel title = new JLabel("Registration Form");
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setBackground(Color.GRAY);
        title.setBounds(150, 30, 300, 40);
        f2.add(title);

        JLabel name = new JLabel("Name");
        name.setBackground(Color.WHITE);
        name.setForeground(Color.BLUE);
        name.setSize(190, 20);
        name.setLocation(100, 100);
        f2.add(name);

        JTextField tnam = new JTextField();
        tnam.setSize(190, 20);
        tnam.setLocation(200, 100);
        tnam.setForeground(Color.BLUE);
        tnam.setEditable(false);
        tnam.setText(tname.getText());
        f2.add(tnam);

        JLabel roll = new JLabel("Roll No: ");
        roll.setBackground(Color.WHITE);
        roll.setForeground(Color.BLUE);
        roll.setSize(100, 20);
        roll.setLocation(100, 130);
        f2.add(roll);

        JTextField trol = new JTextField();
        trol.setSize(190, 20);
        trol.setLocation(200, 130);
        trol.setForeground(Color.BLUE);
        trol.setEditable(false);
        trol.setText(troll.getText());
        f2.add(trol);

        JLabel batch = new JLabel("Batch: ");
        batch.setBackground(Color.WHITE);
        batch.setForeground(Color.BLUE);
        batch.setSize(190, 20);
        batch.setLocation(100, 160);
        f2.add(batch);

        JTextField tbat = new JTextField();
        tbat.setSize(190, 20);
        tbat.setLocation(200, 160);
        tbat.setForeground(Color.BLUE);
        tbat.setEditable(false);
        tbat.setText(tbatch.getText());
        f2.add(tbat);

        JLabel sec = new JLabel("Section: ");
        sec.setBackground(Color.WHITE);
        sec.setForeground(Color.BLUE);
        sec.setSize(190, 20);
        sec.setLocation(100, 200);
        f2.add(sec);

        JTextField ts = new JTextField();
        ts.setSize(190, 20);
        ts.setLocation(200, 200);
        ts.setForeground(Color.BLUE);
        ts.setEditable(false);
        ts.setText(tsec.getText());
        f2.add(ts);

        JLabel gender = new JLabel("Gender:");
        gender.setBackground(Color.WHITE);
        gender.setForeground(Color.BLUE);
        gender.setSize(100, 20);
        gender.setLocation(100, 230);
        f2.add(gender);

        JLabel gend = new JLabel();

        if (male.isSelected()) {
            gend = new JLabel("Male");
        } else {
            gend = new JLabel("Female");
        }

        JTextField tgen = new JTextField();
        tgen.setSize(190, 20);
        tgen.setLocation(200, 230);
        tgen.setEditable(false);
        tgen.setText(gend.getText());
        f2.add(tgen);

        JLabel qualification = new JLabel("Qualification: ");
        qualification.setBackground(Color.WHITE);
        qualification.setForeground(Color.BLUE);
        qualification.setSize(100, 20);
        qualification.setLocation(100, 260);
        f2.add(qualification);

        JLabel qual = new JLabel();
        //// Conditions for check boxes of qualification
        if (postgraduate.isSelected()) { // When Male Button's Selected
            qual = new JLabel("Post Graduate");
        } else if (graduate.isSelected()) {
            qual = new JLabel("Under Graduate");
        } else if (intermediate.isSelected()) {
            qual = new JLabel("Intermediate");
        } else if (matric.isSelected()) {
            qual = new JLabel("Matric");
        }

        JTextField tqualif = new JTextField();
        tqualif.setSize(190, 20);
        tqualif.setLocation(200, 260);
        tqualif.setEditable(false);
        tqualif.setText(qual.getText());
        f2.add(tqualif);

        JLabel address = new JLabel("Address: ");
        address.setBackground(Color.WHITE);
        address.setForeground(Color.BLUE);
        address.setSize(100, 20);
        address.setLocation(100, 300);
        f2.add(address);

        JTextArea tadd = new JTextArea();
        tadd.setSize(200, 50);
        tadd.setLocation(200, 300);
        tadd.setEditable(false);
        tadd.setText(taddress.getText());
        f2.add(tadd);

        JLabel country = new JLabel("Country: ");
        country.setBackground(Color.WHITE);
        country.setForeground(Color.WHITE);
        country.setSize(100, 20);
        country.setLocation(100, 360);
        f2.add(country);

        JLabel c = new JLabel(count[tcountry.getSelectedIndex()]);

        JTextField tcoun = new JTextField();
        tcoun.setSize(190, 20);
        tcoun.setLocation(200, 360);
        tcoun.setEditable(false);
        tcoun.setText(c.getText());
        f2.add(tcoun);

        f2.setVisible(true);
        f2.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void get() {
        str1 = tname.getText();
        str2 = troll.getText();
        str3 = tbatch.getText();
        str4 = "";
        str5 = tsec.getText();
        str6 = "";

        if (male.isSelected())
            str4 = "Male";
        else
            str4 = "Female";
        if (matric.isSelected() && intermediate.isSelected() && graduate.isSelected() && postgraduate.isSelected()) {
            str6 = matric.getText() + ", " + intermediate.getText() + ", " + graduate.getText() + " and "
                    + postgraduate.getText();
        } else if (matric.isSelected() && intermediate.isSelected() && graduate.isSelected()) {
            str6 = matric.getText() + ", " + intermediate.getText() + ", " + graduate.getText();
        } else if (matric.isSelected() && intermediate.isSelected()) {
            str6 = matric.getText() + ", " + intermediate.getText();
        } else if (matric.isSelected()) {
            str6 = matric.getText();
        } else if (intermediate.isSelected()) {
            str6 = intermediate.getText();
        } else if (graduate.isSelected()) {
            str6 = graduate.getText();
        } else if (postgraduate.isSelected()) {
            str6 = postgraduate.getText();
        } // end of if else conditional
        str7 = address.getText();
        str8 = (String) tcountry.getSelectedItem();
    } // end of get

    public void insertDatabase() throws ClassNotFoundException, SQLException {

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/Demo_db";
        String uname = "root";
        String pass = "pakistan92";
        String Query = "SELECT * FROM Std_details";

        try {

            Class.forName(driver);
            Connection con = DriverManager.getConnection(url, uname, pass);
            System.out.println("Connected");

            Statement s = con.createStatement();
            ResultSet rs = s.executeQuery(Query);
            // iterate through the java resultset
            get();
            int count = s.executeUpdate("INSERT INTO jdbc.`Std_details` VALUES('" + str1 + "', '" + str2 + "', '" + str3
                    + "', '" + str4 + "', '" + str5 + "', '" + str6 + "', '" + str7 + "', '" + str8 + "')");
            System.out.println(count + " Rows affected");

            s.close();
            rs.close();

        } catch (Exception e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    } //

    public void fetchData() throws ClassNotFoundException, SQLException {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/Demo_db";
        String uname = "root";
        String pass = "pakistan92";
        String Query = "SELECT * FROM Std_details";

        Class.forName(driver);
        Connection com = DriverManager.getConnection(url, uname, pass); // In XAMPP we have not to store password in
                                                                        // getConnection,but rather we use empy string
                                                                        // at place of password parameter
        Statement st = com.createStatement();
        ResultSet rs = st.executeQuery(Query);
        get();

        while (rs.next()) {
            String name = rs.getString(1);
            String roll = rs.getString(2);
            String batch = rs.getString(3);
            String gender = rs.getString(4);
            String sec = rs.getString(5);
            String qualification = rs.getString(6);
            String address = rs.getString(7);
            String country = rs.getString(8);

            System.out.print(name + "\t || " + roll + "\t || " + batch + "\t || " + gender + "\t || " + sec
                    + "\t || " + qualification + "\t || " + address + "\t || ");
            System.out.println(country);
        }
        st.close();
        rs.close();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Writer writer; // For JSON
        Object1 = new JsonObject();

        Object1.put("Name: ", tname.getText());
        Object1.put("RollNo: ", troll.getText());
        Object1.put("Batch: ", tbatch.getText());
        Object1.put("Section: ", tsec.getText());
        Object1.put("Address: ", address.getText());
        Object1.put("Country: ", count[tcountry.getSelectedIndex()]);

        //// Conditions for radio buttons of gender
        {
            if (male.isSelected()) { // When Male Button's Selected
                Object1.put("Gender: ", male.getText());
            } else { // else Female Button's Selected
                Object1.put("Gender: ", female.getText());
            }
        }
        //// Conditions for check boxes of qualification
        {
            if (matric.isSelected()) { // When Matric is Selected
                Object1.put("Qualification ", "Matric");
            }
            if (intermediate.isSelected()) { // When Intermediate is Selected
                Object1.put("Qualification ", "Intermediate");
            }
            if (graduate.isSelected()) { // When Graduate is Selected
                Object1.put("Qualification ", "Graduate");
            }
            if (postgraduate.isSelected()) { // When PostGraduate is Selected
                Object1.put("Qualification ", "Post Graduate");
            }
        }

        if (e.getSource() == save) { // when save button is selected
            try {
                save();
                JOptionPane.showMessageDialog(null, "Details Saved Successfully.");
            } catch (Exception a) {
                a.printStackTrace();
            }
        }
        if (e.getSource() == print)

        { // when Print button is selected
            save();
            this.dispose();
            print(); // call the prints method to print those save data from json file
        }
        if (e.getSource() == database)

        { // when Print button is selected
            try {
                insertDatabase();
                JOptionPane.showMessageDialog(null, "Databse updated successfully.");
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            } // call the prints method to print those save data from json file
        }
        if (e.getSource() == clear)

        { // when Print button is selected
            try {
                fetchData();
                JOptionPane.showMessageDialog(null, "Databse fetching started.");
            } catch (ClassNotFoundException | SQLException ex) {
                throw new RuntimeException(ex);
            } // call the prints method to print those save data from json file
        }

    }
}

public class SqlTask {
    public static void main(String[] args) {
        Reg_Form rf = new Reg_Form();
    }

}