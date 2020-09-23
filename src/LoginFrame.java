import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class LoginFrame extends JFrame {
    Frame frame = new Frame();

    JPanel panel = new JPanel();
    JLabel userLabel=new JLabel("USERNAME");
    JLabel passwordLabel=new JLabel("PASSWORD");
    JTextField userTextField=new JTextField();
    JPasswordField passwordField=new JPasswordField();
    JButton loginButton=new JButton("LOGIN");
    //JButton resetButton=new JButton("RESET");
    JCheckBox showPassword=new JCheckBox("Show Password");
    JButton registerButton = new JButton("REGISTER");
    JLabel infoLabel = new JLabel();

    LoginFrame()
    {
        //Calling methods inside constructor.
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        setFrameSettings();
        initListeners();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        infoLabel.setBounds(50,250,250,30);
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
        panel.add(infoLabel);
        frame.add(panel);
    }

    public void setFrameSettings(){
        frame.setTitle("Login Form");
        frame.setVisible(true);
        frame.setBounds(10,10,370,350);
        frame.setResizable(false);
    }

    public void initListeners(){
        loginButton.addActionListener(e -> {
            String username = String.valueOf(userTextField.getText());
            String password = String.valueOf(passwordField.getPassword());

            User user = new User();
            try {
                user = user.checkUser(username, password);

                infoLabel.setText("You successfully logged in : " + username);

                frame.setVisible(false);
                frame.dispose();

                MainMenu mainMenu = new MainMenu();

            } catch (SQLException exception) {
                exception.printStackTrace();
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
