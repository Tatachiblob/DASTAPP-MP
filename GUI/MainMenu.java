package GUI;
/**
 *
 * @author Yuta
 */
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import org.apache.commons.lang3.math.NumberUtils;
public class MainMenu extends JFrame implements ActionListener {
    
    private JButton bEditMap, bLoadMap;
    private JFileChooser file;
    
    public MainMenu(){
        super("Machine Project");
        this.bEditMap = new JButton("Edit Map");
        this.bLoadMap = new JButton("Load Map");
        this.file = new JFileChooser("C:\\Users\\Yuta\\Documents\\NetBeansProjects\\DASTAPPMP\\Save File");
        this.bEditMap.addActionListener(this);
        this.bLoadMap.addActionListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocation(550, 400);
        this.initScreen();
        this.setSize(350, 200);
        this.setVisible(true);
    }
    
    public void initScreen(){
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.insets = new Insets(10, 10, 10, 10);
        con.anchor = GridBagConstraints.EAST;
        
        con.gridx = 0;
        con.gridy = 0;
        p.add(bLoadMap, con);
        
        con.gridx = 1;
        p.add(bEditMap, con);
        
        
        add(p);
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Option Panel"));
        p.setBackground(new Color(127,191,127));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        File selected = null;
        if (e.getSource() == bLoadMap) {
            MazeApp app = null;
            int returnVal = file.showOpenDialog(MainMenu.this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selected = file.getSelectedFile();
                if(selected != null){
                    app = new MazeApp(selected);
                    app.setVisible(true);
                }
            }
        }
        if (e.getSource() == bEditMap) {
            int option = JOptionPane.showConfirmDialog(null, "Create new Map?", "Machine Project", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                try {
                    boolean check = true;
                    JFrame inputName = new JFrame("Machine Project");
                    String name = null;
                    while (check) {
                        name = JOptionPane.showInputDialog(inputName, "Enter Name");
                        if (!name.isEmpty()) {
                            check = false;
                        }
                    }
                    check = true;
                    JTextField xField = new JTextField(5);
                    JTextField yField = new JTextField(5);
                    int rows = 0, colums = 0;
                    while (check) {
                        JPanel myPanel = new JPanel();
                        myPanel.add(new JLabel("Colums:"));
                        myPanel.add(xField);
                        myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                        myPanel.add(new JLabel("Rows:"));
                        myPanel.add(yField);

                        int result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter Colums and Rows Values", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            if (NumberUtils.isCreatable(xField.getText()) && NumberUtils.isCreatable(yField.getText())) {
                                rows = Integer.parseUnsignedInt(xField.getText());
                                colums = Integer.parseUnsignedInt(yField.getText());
                                if (rows >= 15 && colums >= 15 && rows <= 30 && colums <= 30) {
                                    check = false;
                                }
                            }
                        }
                        if (result == JOptionPane.CANCEL_OPTION) {
                            check = false;
                        }
                    }
                    if (rows != 0 || colums != 0) {
                        MapEditor m = new MapEditor(rows, colums, name);
                        MazeApp app = new MazeApp(m);
                        m.setFrame(app);
                        app.setVisible(true);
                    }
                } catch (Exception ex) {}
            } 
            else if (option == JOptionPane.NO_OPTION) {
                MapEditor m = null;
                MazeApp app = null;
                int returnVal = file.showOpenDialog(MainMenu.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    selected = file.getSelectedFile();
                    if(selected != null){
                        m = new MapEditor(selected);
                        app = new MazeApp(m);
                        m.setFrame(app);
                        app.setVisible(true);
                    }
                }
            }
        }
    }
}
