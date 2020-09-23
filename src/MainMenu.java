import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {
    Frame frame = new Frame();
    JPanel panel = new JPanel();

    // Main menu bar
    JMenuBar menuBar = new JMenuBar();

    // Menus
    JMenu settingsMenu = new JMenu("Settings");
    JMenu userMenu = new JMenu("User");

    public MainMenu(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());

        setLayoutManager();
        addComponentsToContainer();
        setFrameSettings();

    }

    public void setLayoutManager()
    {
        panel.setLayout(null);
    }

    public JMenuBar createMenuBar(){
        JMenuItem settingsMenuItem1 = new JMenuItem("Preferences");
        JMenuItem settingsMenuItem2 = new JMenuItem("Settings");
        JMenuItem settingsMenuItem3 = new JMenuItem("User Details");

        settingsMenu.add(settingsMenuItem1);
        settingsMenu.add(settingsMenuItem2);
        settingsMenu.add(settingsMenuItem3);

        JMenuItem userMenuItem1 = new JMenuItem("Details");
        JMenuItem userMenuItem2 = new JMenuItem("My Account Settings");
        JMenuItem userMenuItem3 = new JMenuItem("Request Permission");

        userMenu.add(userMenuItem1);
        userMenu.add(userMenuItem2);
        userMenu.add(userMenuItem3);

        menuBar.add(userMenu);
        menuBar.add(settingsMenu);

        return menuBar;
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        //panel.add(menuBar);


    }

    public void setFrameSettings(){
        frame.setTitle("Accounting Software");
        frame.setBounds(10,10,1250,750);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
