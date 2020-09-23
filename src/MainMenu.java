import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {
    // Main menu bar
    JMenuBar menuBar = new JMenuBar();

    // Menus
    JMenu settingsMenu = new JMenu("Settings");
    JMenu userMenu = new JMenu("User");
    JMenu productsMenu = new JMenu("Products");

    // Menu Items
    JMenuItem settingsMenuItem1;
    JMenuItem settingsMenuItem2;
    JMenuItem settingsMenuItem3;

    JMenuItem userMenuItem1;
    JMenuItem userMenuItem2;
    JMenuItem userMenuItem3;

    JMenuItem productsMenuItem1;
    JMenuItem productsMenuItem2;
    JMenuItem productsMenuItem3;
    JMenuItem productsMenuItem4;

    public MainMenu(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(createMenuBar());

        setLayoutManager();
        addComponentsToContainer();
        setFrameSettings();
        initListeners();
    }

    public void setLayoutManager()
    {
        this.setLayout(null);
    }

    public JMenuBar createMenuBar(){
        settingsMenuItem1 = new JMenuItem("Preferences");
        settingsMenuItem2 = new JMenuItem("Settings");
        settingsMenuItem3 = new JMenuItem("User Details");

        settingsMenu.add(settingsMenuItem1);
        settingsMenu.add(settingsMenuItem2);
        settingsMenu.add(settingsMenuItem3);

        userMenuItem1 = new JMenuItem("Details");
        userMenuItem2 = new JMenuItem("My Account Settings");
        userMenuItem3 = new JMenuItem("Request Permission");

        userMenu.add(userMenuItem1);
        userMenu.add(userMenuItem2);
        userMenu.add(userMenuItem3);

        productsMenuItem1 = new JMenuItem("Product List");
        productsMenuItem2 = new JMenuItem("Prices List");
        productsMenuItem3 = new JMenuItem("Add Product");
        productsMenuItem4 = new JMenuItem("Add Price");

        productsMenu.add(productsMenuItem1);
        productsMenu.add(productsMenuItem2);
        productsMenu.add(productsMenuItem3);
        productsMenu.add(productsMenuItem4);

        menuBar.add(userMenu);
        menuBar.add(settingsMenu);
        menuBar.add(productsMenu);

        return menuBar;
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        //panel.add(menuBar);
    }

    public void setFrameSettings(){
        this.setTitle("Accounting Software");
        this.setBounds(10,10,1250,750);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void initListeners(){
        productsMenuItem2.addActionListener(e -> {
           this.setVisible(false);
           this.dispose();

           Prices pricesFrame = new Prices();
        });
    }
}
