import javax.swing.*;

public class Prices extends JFrame {
    private JTable pricesTable;
    private JPanel panel1;

    public Prices(){
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar());
        setTitle("Prices List");
        setBounds(10,10,1250,750);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
