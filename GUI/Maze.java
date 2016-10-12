package GUI;
/**
 *
 * @author Yuta
 */
import LOGIC.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;
public class Maze extends JPanel implements ActionListener {
    
    private ArrayList<Cell> tiles;
    private ArrayList<Rectangle> rects;
    
    public Maze(File f){
        this.tiles = new ArrayList<>();
        this.rects = new ArrayList<>();
        this.generateMaze(Map.loadMap(f));
        
        for(int i = 0; i < tiles.size(); i++){
            if(tiles.get(i) instanceof Tile){
                rects.add(new Rectangle(tiles.get(i).getX(), tiles.get(i).getY(), 40, 40));
            }
            if(tiles.get(i) instanceof Rat){
                rects.add(new Rectangle(tiles.get(i).getX(), tiles.get(i).getY(), 40, 40));
            }
            if(tiles.get(i) instanceof Cheese){
                rects.add(new Rectangle(tiles.get(i).getX(), tiles.get(i).getY(), 40, 40));
            }
        } 
        Timer t = new Timer(1, this);
        t.start();
    }
    
    public ArrayList<Cell> getTiles(){return this.tiles;}
    public ArrayList<Rectangle> getRects(){return this.rects;}
    
    public void generateMaze(Map map){
        for(int i = 0; i < map.getColums(); i++){
            for(int j = 0; j < map.getRows(); j++){
                if(map.getMap()[i][j] == Map.PATH){
                    Tile t = new Tile(i, j);
                    t.setWall(Tile.NOT_WALL);
                    tiles.add(t);
                }
                else if(map.getMap()[i][j] == Map.WALL){
                    Tile t = new Tile(i, j);
                    t.setWall(Tile.IS_WALL);
                    tiles.add(t);
                }
                else if(map.getMap()[i][j] == Map.RAT){
                    Rat r = new Rat(i, j);
                    tiles.add(r);
                }
                else if(map.getMap()[i][j] == Map.CHEESE){
                    Cheese c = new Cheese(i, j);
                    tiles.add(c);
                }
            }
        }
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
        
        Graphics2D g2d = (Graphics2D) g;
        int i = 0;
        for(Cell c : tiles){
            if(c instanceof Tile){
                if(((Tile) c).getWall()){
                    g2d.setColor(Color.BLACK);
                }
                else{
                    g2d.setColor(Color.WHITE);
                }
            }
            if(c instanceof Rat){
                g2d.setColor(Color.YELLOW);
            }
            
            if(c instanceof Cheese){
                g2d.setColor(Color.CYAN);
            }
            g2d.draw(rects.get(i));
            g2d.fill(rects.get(i));
            i++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
    
    public static void main(String[] args){
        Maze app = new Maze(new File("C:\\Users\\Yuta\\Downloads\\default.map"));
        JFrame f = new JFrame("Hello world!");
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 500);
        
        f.add(app);
        
        f.setResizable(true);
        f.setVisible(true);
    }
    
}
