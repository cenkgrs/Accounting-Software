import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginFrame extends MainFrame {
    Frame frame = new Frame();

    JPanel panel = new JPanel();
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("LOGIN");
    JButton resetButton=new JButton("RESET");
    JCheckBox showPassword=new JCheckBox("Show Password");
    JButton registerButton = new JButton("REGISTER");

    LoginFrame()
    {
        //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setFrameSettings();
        initListeners();
    }

    public void setLayoutManager()
    {
        panel.setLayout(null);
    }
    public void setLocationAndSize(){
        //Setting location and Size of each components using setBounds() method.
        userLabel.setBounds(50,50,100,30);
        passwordLabel.setBounds(50,120,100,30);
        userTextField.setBounds(150,50,150,30);
        passwordField.setBounds(150,120,150,30);
        showPassword.setBounds(150,150,150,30);
        loginButton.setBounds(50,200,100,30);
        registerButton.setBounds(200,200,100,30);
        //resetButton.setBounds(50,250,100,30);


    }
    public void addComponentsToContainer(){
        //Adding each components to the Container
        panel.add(userLabel);
        panel.add(passwordLabel);
        panel.add(userTextField);
        panel.add(passwordField);
        panel.add(showPassword);
        panel.add(loginButton);
        //panel.add(resetButton);
        panel.add(registerButton);
        frame.add(panel);
    }

    public void setFrameSettings(){
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,350);
        frame.setResizable(false);
    }

    public void initListeners(){
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = String.valueOf(userTextField.getText());
                String password = String.valueOf(passwordField.getPassword());

                User user = new User();
                try {
                    user = user.checkUser(username, password);

                    frame.setVisible(false);

                    System.out.println("Welcome " );
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        });

        registerButton.addActionListener(e -> {
           RegisterFrame registerFrame = new RegisterFrame();
           frame.setVisible(false);
           frame.dispose();
           registerFrame.setVisible(true);
        });

        showPassword.addActionListener(e -> {
            passwordField.setEchoChar('*');

            if(showPassword.isSelected()){
                passwordField.setEchoChar((char)0);
            }

        });
    }

}
