import javax.swing.*;

public class MenuBar extends JFrame{

    public JMenuBar createMenuBar(JFrame frame){
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
        JMenuItem userMenuItem4;

        JMenuItem productsMenuItem1;
        JMenuItem productsMenuItem2;
        JMenuItem productsMenuItem3;
        JMenuItem productsMenuItem4;

        settingsMenuItem1 = new JMenuItem("Preferences");
        settingsMenuItem2 = new JMenuItem("Settings");
        settingsMenuItem3 = new JMenuItem("User Details");

        settingsMenu.add(settingsMenuItem1);
        settingsMenu.add(settingsMenuItem2);
        settingsMenu.add(settingsMenuItem3);

        userMenuItem1 = new JMenuItem("Details");
        userMenuItem2 = new JMenuItem("My Account Settings");
        userMenuItem3 = new JMenuItem("Request Permission");
        userMenuItem4 = new JMenuItem("Log out");

        userMenu.add(userMenuItem1);
        userMenu.add(userMenuItem2);
        userMenu.add(userMenuItem3);
        userMenu.add(userMenuItem4);

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

        productsMenuItem1.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();

            ProductsFrame productsFrame = new ProductsFrame();
        });

        productsMenuItem2.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();

            Prices pricesFrame = new Prices();
        });

        userMenuItem1.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();

            UserDetailsFrame userDetailsFrame = new UserDetailsFrame();
        });
        userMenuItem4.addActionListener(e -> {
            frame.setVisible(false);
            frame.dispose();

            LoginFrame loginFrame = new LoginFrame();
        });

        return menuBar;
    }
}
