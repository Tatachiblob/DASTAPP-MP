package GUI;
/**
 *
 * @author Yuta 11512709
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import LOGIC.*;
import java.io.File;
public class MapEditor extends JPanel implements MouseListener, ActionListener, KeyListener, WindowListener{
    
    private Map map;
    private File f;
    private Cell[][] rects;
    private JFrame frame;
    public int totalX, totalY;
    public Dimension d;
    
    public MapEditor(File f) {
        this.map = Map.loadMap(f);
        this.f = f;
        this.rects = new Cell[map.getColums()][map.getRows()];
        this.initScreen();
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        Timer t = new Timer(1, this);
        t.start();
    }
    
    public MapEditor(int row, int col, String name){
        this.map = new Map(row, col);
        this.f = new File("../DASTAPPMP/Save File/" + name + ".txt");
        this.rects = new Cell[map.getColums()][map.getRows()];
        this.initScreen();
        addMouseListener(this);
        addKeyListener(this);
        setFocusable(true);
        Timer t = new Timer(1, this);
        t.start();
    }
    
    private void setDimension(Dimension d){this.d = d;}
    
    public void setFrame(JFrame f){this.frame = f;}
    public Map getMap(){return map;}
    
    public void initScreen(){
        int width = 0, height = 0;
        if(map.getColums() >= 15 && map.getColums() < 20 && map.getRows() >= 15 && map.getRows() < 20){
            width = 40;
            height = 40;
        }   
        else if(map.getColums() >= 20 && map.getColums() < 26 && map.getRows() >= 20 && map.getRows() < 26){
            width = 35;
            height = 35;
        }
        else if(map.getColums() >= 26 && map.getColums() <= 30 && map.getRows() >= 26 && map.getRows() <= 30){
            width = 25;
            height = 25;
        }
        else{
            width = 25;
            height = 25;
        }
        
        int x = 0, y = 0;
        for(int i = 0; i < map.getColums(); i++){
            for(int j = 0; j < map.getRows(); j++){
                if(map.getMap()[i][j] == Map.PATH){
                    Tile t = new Tile(x, y, j, i, width, height);
                    t.setWall(Cell.PATH_STAT);
                    t.setColor(Tile.PATH_COLOR);
                    rects[i][j] = t;
                }
                if(map.getMap()[i][j] == Map.WALL){
                    Tile t = new Tile(x, y, j, i, width, height);
                    t.setWall(Cell.WALL_STAT);
                    t.setColor(Tile.WALL_COLOR);
                    rects[i][j] = t;
                }
                if(map.getMap()[i][j] == Map.RAT){
                    Rat r = new Rat(x, y, j, i, width, height);
                    r.setColor(Rat.RAT_COLOR);
                    rects[i][j] = r;
                }
                if(map.getMap()[i][j] == Map.CHEESE){
                    Cheese c = new Cheese(x, y, j, i, width, height);
                    c.setColor(Cheese.CHEESE_COLOR);
                    rects[i][j] = c;
                }
                x += width + 2;
            }
            y += height + 2;
            x = 0;
            totalX += width + 2;
            totalY += height + 2;
        }
        totalX += width;
        totalY += height;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        for (int i = 0; i < map.getColums(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                if (rects[i][j] instanceof Tile) {
                    if (((Tile) rects[i][j]).getWall()) {
                        g2d.setColor(rects[i][j].getColor());
                    } else {
                        g2d.setColor(rects[i][j].getColor());
                    }
                } 
                else if (rects[i][j] instanceof Rat) {
                    g2d.setColor(rects[i][j].getColor());
                } 
                else if (rects[i][j] instanceof Cheese) {
                    g2d.setColor(rects[i][j].getColor());
                }
                g2d.draw(rects[i][j]);
                g2d.fill(rects[i][j]);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        for(int i = 0; i < map.getColums(); i++){
            for(int j = 0; j < map.getRows(); j++){
                if(rects[i][j].contains(e.getPoint())){
                    if(rects[i][j].getColor().equals(Tile.WALL_COLOR)){
                        rects[i][j].setColor(Tile.PATH_COLOR);
                        map.setMap(j, i, Map.PATH);
                    }
                    else if(rects[i][j].getColor().equals(Tile.PATH_COLOR)){
                        rects[i][j].setColor(Cheese.CHEESE_COLOR);
                        map.setMap(j, i, Map.CHEESE);
                    }
                    else if(rects[i][j].getColor().equals(Cheese.CHEESE_COLOR)){
                        rects[i][j].setColor(Rat.RAT_COLOR);
                        map.setMap(j, i, Map.RAT);
                    }
                    else if(rects[i][j].getColor().equals(Rat.RAT_COLOR)){
                        rects[i][j].setColor(Tile.WALL_COLOR);
                        map.setMap(j, i, Map.WALL);
                    }
                }
            }
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_ESCAPE:
                if(map.isLegitMap()){
                    map.saveMap(f);
                    JOptionPane.showMessageDialog(null, "Successful Save", "Machine Project", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Could not save file", "Machine Project", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        map.saveMap(f);
        frame.dispose();
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}
}
