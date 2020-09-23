import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class LoginFrame extends JFrame {

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayoutManager();
        setStyles();
        setLocationAndSize();
        addComponentsToContainer();
        setFrameSettings();
        initListeners();
    }

    public void setLayoutManager()
    {
        panel.setLayout(null);
    }
    public void setStyles() {
        //userLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
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
        this.add(panel);
    }

    public void setFrameSettings(){
        this.setTitle("Login Form");
        this.setBounds(10,10,370,350);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initListeners(){
        loginButton.addActionListener(e -> {
            String username = String.valueOf(userTextField.getText());
            String password = String.valueOf(passwordField.getPassword());

            User user = new User();
            try {
                user = user.checkUser(username, password);

                infoLabel.setText("You successfully logged in : " + username);

                this.setVisible(false);
                this.dispose();

                MainMenu mainMenu = new MainMenu();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }

        });

        registerButton.addActionListener(e -> {
           RegisterFrame registerFrame = new RegisterFrame();
           this.setVisible(false);
           this.dispose();
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
