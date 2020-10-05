import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    public MainMenu(User user){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MenuBar menu = new MenuBar();
        setJMenuBar(menu.createMenuBar(this, user));

        setLayoutManager();
        addComponentsToContainer();
        setFrameSettings();
    }

    public void setLayoutManager()
    {
        this.setLayout(null);
    }

    public void addComponentsToContainer(){
        //Adding each components to the Container
        //panel.add(menuBar);
    }

    public void setFrameSettings(){
        this.setTitle("Accounting Software");
        this.setBounds(10,10,1250,750);
        setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

}
