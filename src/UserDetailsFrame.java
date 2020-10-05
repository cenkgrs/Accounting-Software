import javax.swing.*;

public class UserDetailsFrame extends JFrame{
    private javax.swing.JPanel JPanel;
    private JLabel usernameLabel;
    private JLabel usernameShowLabel;
    private JLabel firstnameLabel;
    private JLabel firstnameShowLabel;
    private JLabel lastnameLabel;
    private JLabel lastnameShowLabel;
    private User user;

    public UserDetailsFrame(User user){
        this.user = user;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar(this, user));

        fillLabels();
        addComponentsToContainer();
        setFrameSettings();
    }

    public void fillLabels(){
        // Cenk: Get all label values from the logged in user and set to labels
        if(user != null){
            usernameShowLabel.setText(user.getUsername());
            firstnameShowLabel.setText(user.getFirstname());
            lastnameShowLabel.setText(user.getLastname());
        }
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        this.add(JPanel);
    }

    public void setFrameSettings(){
        this.setTitle("User Details");
        this.setBounds(10,10,625,375);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);

    }
}
