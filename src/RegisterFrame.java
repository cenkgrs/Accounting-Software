import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterFrame extends JFrame {
    JPanel panel = new JPanel();
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
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
        userTextField.setBounds(150,50,150,30);
        passwordField.setBounds(150,120,150,30);
        showPassword.setBounds(150,150,150,30);
        registerButton.setBounds(150,200,100,30);
        infoLabel.setBounds(50,250,300,30);
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        panel.add(userLabel);
        panel.add(passwordLabel);
        panel.add(userTextField);
        panel.add(passwordField);
        panel.add(showPassword);
        panel.add(registerButton);
        panel.add(infoLabel);
        this.add(panel);
    }

    public void setFrameSettings(){
        this.setTitle("Register Form");
        this.setVisible(true);
        this.setBounds(10,10,370,350);
        this.setResizable(false);
    }

    public void initListeners(){
        registerButton.addActionListener(e -> {
            String username = String.valueOf(userTextField.getText());
            String password = String.valueOf(passwordField.getPassword());

            User user = new User();
            try {
                user = user.createUser(username, password);

                infoLabel.setText("You successfully registered " + user.getUsername());
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
