package GUI;
/**
 *
 * @author Yuta
 */
import LOGIC.*;
import javax.swing.*;
import java.awt.event.*;
public class MainMenu extends JFrame implements ActionListener {
    
    private JButton bCreateMap, bLoadMap;
    
    public MainMenu(){
        super("Machine Project");
        this.bCreateMap = new JButton("Create Map");
        this.bLoadMap = new JButton("Load Map");
    }
    
    public void initScreen(){
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
