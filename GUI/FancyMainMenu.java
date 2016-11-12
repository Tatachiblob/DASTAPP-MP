package GUI;


/**
 *
 * @author Yuta 11512709
 */
import GUI.*;
import LOGIC.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.plaf.LayerUI;
import org.apache.commons.lang3.math.NumberUtils;

public class FancyMainMenu {
    
    private static JButton bLoadMap, bEditMap;
    private static ActionListener al = new ActionListener() {
        JFileChooser file = new JFileChooser("../DASTAPPMP/Save File");

        @Override
        public void actionPerformed(ActionEvent e) {
            File selected = null;
            if (e.getSource().equals(bLoadMap)) {
                MazeApp app = null;
                int returnval = file.showOpenDialog(null);
                if (returnval == JFileChooser.APPROVE_OPTION) {
                    selected = file.getSelectedFile();
                    if (selected != null) {
                        app = new MazeApp(selected);
                        app.setVisible(true);
                    }//if
                }//if
            }//if

            if (e.getSource().equals(bEditMap)) {
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
                    } catch (Exception ex) {System.out.println("EXCEPTION");}
                    
                    
                }//if
                else if (option == JOptionPane.NO_OPTION) {
                    MapEditor m = null;
                    MazeApp app = null;
                    int returnVal = file.showOpenDialog(null);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        selected = file.getSelectedFile();
                        if (selected != null) {
                            m = new MapEditor(selected);
                            app = new MazeApp(m);
                            m.setFrame(app);
                            app.setVisible(true);
                        }
                }
                }//else if
            }//if

        }//actionPerformed

    };//ActionListener

    public static void createUI() {
        JFrame frame = new JFrame("Machine Project");
        LayerUI<JPanel> layerUI = new SpotlightLayerUI();
        JPanel panel = createPanel();
        JLayer<JPanel> jlayer = new JLayer<JPanel>(panel, layerUI);

        frame.add(jlayer);

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static JPanel createPanel() {
        bLoadMap = new JButton("Load Map");
        bLoadMap.addActionListener(al);
        bEditMap = new JButton("Edit Map");
        bEditMap.addActionListener(al);
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();
        con.insets = new Insets(10, 10, 10, 10);
        con.anchor = GridBagConstraints.EAST;

        con.gridx = 0;
        con.gridy = 0;
        p.add(bLoadMap, con);

        con.gridx = 1;
        p.add(bEditMap, con);

        p.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Option Panel"));
        p.setBackground(new Color(127, 191, 127));
        return p;
    }

}

class SpotlightLayerUI extends LayerUI<JPanel> {

    private boolean mActive;
    private int mX, mY;

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JLayer jlayer = (JLayer) c;
        jlayer.setLayerEventMask(
                AWTEvent.MOUSE_EVENT_MASK
                | AWTEvent.MOUSE_MOTION_EVENT_MASK
        );
    }

    @Override
    public void uninstallUI(JComponent c) {
        JLayer jlayer = (JLayer) c;
        jlayer.setLayerEventMask(0);
        super.uninstallUI(c);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        Graphics2D g2 = (Graphics2D) g.create();

        // Paint the view.
        super.paint(g2, c);

        if (mActive) {
            // Create a radial gradient, transparent in the middle.
            java.awt.geom.Point2D center = new java.awt.geom.Point2D.Float(mX, mY);
            float radius = 72;
            float[] dist = {0.0f, 1.0f};
            Color[] colors = {new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.BLACK};
            RadialGradientPaint p
                    = new RadialGradientPaint(center, radius, dist, colors);
            g2.setPaint(p);
            g2.setComposite(AlphaComposite.getInstance(
                    AlphaComposite.SRC_OVER, .6f));
            g2.fillRect(0, 0, c.getWidth(), c.getHeight());
        }

        g2.dispose();
    }

    @Override
    protected void processMouseEvent(MouseEvent e, JLayer l) {
        if (e.getID() == MouseEvent.MOUSE_ENTERED) {
            mActive = true;
        }
        if (e.getID() == MouseEvent.MOUSE_EXITED) {
            mActive = false;
        }
        l.repaint();
    }

    @Override
    protected void processMouseMotionEvent(MouseEvent e, JLayer l) {
        Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
        mX = p.x;
        mY = p.y;
        l.repaint();
    }
}
