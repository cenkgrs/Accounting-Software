import javax.swing.*;

public class UserDetailsFrame extends JFrame{
    private javax.swing.JPanel JPanel;
    private JLabel usernameLabel;
    private JLabel usernameShowLabel;
    private JLabel firstnameLabel;
    private JLabel firstnameShowLabel;
    private JLabel lastnameLabel;
    private JLabel lastnameShowLabel;

    public UserDetailsFrame(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar(this));

        addComponentsToContainer();
        setFrameSettings();
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
