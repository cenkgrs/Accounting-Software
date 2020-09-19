import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterFrame extends Frame {
    Frame frame = new Frame();

    JPanel panel = new JPanel();
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JCheckBox showPassword=new JCheckBox("Show Password");
    JButton registerButton = new JButton("REGISTER");

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
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        panel.add(userLabel);
        panel.add(passwordLabel);
        panel.add(userTextField);
        panel.add(passwordField);
        panel.add(showPassword);
        panel.add(registerButton);
        frame.add(panel);
    }

    public void setFrameSettings(){
        frame.setTitle("Register Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,350);
        frame.setResizable(false);
    }

    public void initListeners(){
        registerButton.addActionListener(e -> {
            String username = String.valueOf(userTextField.getText());
            String password = String.valueOf(passwordField.getPassword());

            User user = new User();
            try {
                user = user.createUser(username, password);


            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            System.out.println("User created : "+ username + " " + password);
        });
    }

}
