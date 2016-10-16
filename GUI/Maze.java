package GUI;
/**
 *
 * @author Yuta
 */
import LOGIC.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
public class Maze extends JPanel implements ActionListener, KeyListener {
    
    private ArrayList<Cell> tiles;
    private Map map;
    
    public Maze(File f){
        this.tiles = new ArrayList<>();
        this.map = Map.loadMap(f);
        this.generateMaze(map);
        this.addKeyListener(this);
        this.setFocusable(true);
        Timer t = new Timer(1, this);
        t.start();
    }
    
    public ArrayList<Cell> getTiles(){return this.tiles;}
    
    public void generateMaze(Map map){
        int x = 0, y = 0;
        for(int i = 0; i < map.getColums(); i++){
            for(int j = 0; j < map.getRows(); j++){
                if(map.getMap()[i][j] == Map.PATH){
                    Tile t = new Tile(x, y, j, i);
                    t.setWall(Tile.NOT_WALL);
                    tiles.add(t);
                }
                else if(map.getMap()[i][j] == Map.WALL){
                    Tile t = new Tile(x, y, j, i);
                    t.setWall(Tile.IS_WALL);
                    tiles.add(t);
                }
                else if(map.getMap()[i][j] == Map.RAT){
                    Rat r = new Rat(x, y, j, i);
                    tiles.add(r);
                }
                else if(map.getMap()[i][j] == Map.CHEESE){
                    Cheese c = new Cheese(x, y, j, i);
                    tiles.add(c);
                }
                x +=42;
            }
            y += 42;
            x = 0;
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        
        for (Cell c : tiles){
            if(c instanceof Tile){
                if(((Tile) c).getWall())
                    g2d.setColor(Tile.WALL_COLOR);
                else
                    g2d.setColor(Tile.PATH_COLOR);
            }
            else if(c instanceof Rat){
                g2d.setColor(Rat.RAT_COLOR);
            }
            else if(c instanceof Cheese){
                g2d.setColor(Cheese.CHEESE_COLOR);
            }
            g2d.draw(c);
            g2d.fill(c);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    public static void main(String[] args){
        Maze app = new Maze(new File("C:\\Users\\Yuta\\Documents\\2nd year 1st term\\default.txt"));
        JFrame f = new JFrame("Hello world!");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(950, 950);
        
        f.add(app);
        
        f.setVisible(true);
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Rat r = null;
        for(Cell c : tiles){
            if(c instanceof Rat){
                r = (Rat)c;
            }
        }
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                r.moveLeft(map);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                r.moveRight(map);
                break;
            case KeyEvent.VK_UP:
                System.out.println("UP");
                r.moveUp(map);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                r.moveDown(map);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
}
