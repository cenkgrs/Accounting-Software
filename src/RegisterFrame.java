import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterFrame extends JFrame {
    JPanel panel = new JPanel();
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JLabel firstnameLabel = new JLabel("FIRSTNAME");
    JLabel lastnameLabel = new JLabel("LASTNAME");

    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JTextField firstnameField=new JTextField();
    JTextField lastnameField=new JTextField();

    JCheckBox showPassword=new JCheckBox("Show Password");
    JButton registerButton = new JButton("REGISTER");
    JLabel infoLabel = new JLabel();

    RegisterFrame()
    {
        //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setFrameSettings();
        initListeners();
    }

    public void setLayoutManager(){ panel.setLayout(null); }

    public void setLocationAndSize(){
        //Setting location and Size of each components using setBounds() method.
        userLabel.setBounds(50,50,100,30);
        passwordLabel.setBounds(50,120,100,30);
        firstnameLabel.setBounds(350,50,100,30);
        lastnameLabel.setBounds(350,120,100,30);

        userTextField.setBounds(150,50,150,30);
        passwordField.setBounds(150,120,150,30);
        firstnameField.setBounds(450,50,150,30);
        lastnameField.setBounds(450,120,150,30);

        showPassword.setBounds(150,150,150,30);
        registerButton.setBounds(300,200,100,30);
        infoLabel.setBounds(50,250,300,30);
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        panel.add(userLabel);
        panel.add(passwordLabel);
        panel.add(firstnameLabel);
        panel.add(lastnameLabel);

        panel.add(userTextField);
        panel.add(passwordField);
        panel.add(firstnameField);
        panel.add(lastnameField);

        panel.add(showPassword);
        panel.add(registerButton);
        panel.add(infoLabel);
        this.add(panel);
    }

    public void setFrameSettings(){
        this.setTitle("Register Form");
        this.setVisible(true);
        this.setBounds(10,10,740,350);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public void initListeners(){
        registerButton.addActionListener(e -> {
            String username = String.valueOf(userTextField.getText());
            String password = String.valueOf(passwordField.getPassword());
            String firstname = String.valueOf(firstnameField.getText());
            String lastname = String.valueOf(lastnameField.getText());

            User user = new User();
            try {
                user = user.createUser(username, password, firstname, lastname);

                infoLabel.setText("You successfully registered " + user.getUsername());

                this.setVisible(false);
                this.dispose();

                MainMenu mainMenu = new MainMenu();
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            System.out.println("User created : "+ username + " " + password);
        });

        showPassword.addActionListener(e -> {
            passwordField.setEchoChar('*');

            if(showPassword.isSelected()){
                passwordField.setEchoChar((char)0);
            }

        });
    }

}
