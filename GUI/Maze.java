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
import java.io.*;
public class Maze extends JPanel implements ActionListener, KeyListener {
    
    private Cell[][] tiles;
    private Map map;
    private JFrame frame;
    private Stack myStack;
    public int totalX, totalY;
    
    public Maze(File f, JFrame frame){
        this.frame = frame;
        this.map = Map.loadMap(f);
        this.tiles = new Cell[map.getColums()][map.getRows()];
        this.myStack = new Stack();
        this.generateMaze();
        this.addKeyListener(this);
        this.setFocusable(true);
        Timer t = new Timer(1, this);
        t.start();
    }
    
    public Cell[][] getTiles(){return this.tiles;}
    public void swap(int x1, int y1, int x2, int y2){
        this.tiles[y1][x1].setPosX(x2);
        this.tiles[y1][x1].setPosY(y2);
        this.tiles[y2][x2].setPosX(x1);
        this.tiles[y2][x2].setPosY(y1);
        Cell ratTemp = this.tiles[y1][x1];
        this.tiles[y1][x1] = this.tiles[y2][x2];
        this.tiles[y2][x2] = ratTemp;
        
    }
    public Map getMap(){return this.map;}
    
    public void generateMaze(){
        int width = 0, height = 0;
        if(map.getColums() >= 15 && map.getColums() < 20 || map.getRows() >= 15 && map.getRows() < 20){
            width = 40;
            height = 40;
        }
        else if(map.getColums() >= 20 && map.getColums() < 26 || map.getRows() >= 20 && map.getRows() < 26){
            width = 35;
            height = 35;
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
                    t.setWall(Tile.NOT_WALL);
                    tiles[i][j] = t;
                }
                else if(map.getMap()[i][j] == Map.WALL){
                    Tile t = new Tile(x, y, j, i, width, height);
                    t.setWall(Tile.IS_WALL);
                    tiles[i][j] = t;
                }
                else if(map.getMap()[i][j] == Map.RAT){
                    Rat r = new Rat(x, y, j, i, width, height);
                    tiles[i][j] = r;
                }
                else if(map.getMap()[i][j] == Map.CHEESE){
                    Cheese c = new Cheese(x, y, j, i, width, height, frame);
                    tiles[i][j] = c;
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
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        for (int i = 0; i < map.getColums(); i++) {
            for (int j = 0; j < map.getRows(); j++) {
                if (tiles[i][j] instanceof Tile) {
                    if (((Tile) tiles[i][j]).getWall()) {
                        g2d.setColor(Tile.WALL_COLOR);
                    } else {
                        g2d.setColor(Tile.PATH_COLOR);
                    }
                } 
                else if (tiles[i][j] instanceof Rat) {
                    g2d.setColor(Rat.RAT_COLOR);
                } 
                else if (tiles[i][j] instanceof Cheese) {
                    g2d.setColor(Cheese.CHEESE_COLOR);
                }
                g2d.draw(tiles[i][j]);
                g2d.fill(tiles[i][j]);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < map.getColums(); i++){
            for(int j = 0; j < map.getRows(); j++){
                repaint(tiles[i][j]);
            }
        }
        updateUI();
    }
    
    public Rat findRat(){
        Rat r = null;
        for(int i = 0; i < this.map.getColums(); i++){
            for(int j = 0; j < this.map.getRows(); j++){
                if(tiles[i][j] instanceof Rat){
                    r = (Rat)tiles[i][j];
                }
            }
        }
        return r;
    }
    
    public Cheese findCheese(){
        Cheese c = null;
        for(int i = 0; i < this.map.getColums(); i++){
            for(int j = 0; j < this.map.getRows(); j++){
                if(tiles[i][j] instanceof Cheese){
                    c = (Cheese)tiles[i][j];
                }
            }
        }
        return c;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        Rat r = null;
        
        for(int i = 0; i < map.getColums(); i++){
            for(int j = 0; j < map.getRows(); j++){
                if(tiles[i][j] instanceof Rat){
                    r = (Rat)tiles[i][j];
                }
            }
        }
        switch(e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                System.out.println("LEFT");
                r.moveLeft(this);
                break;
            case KeyEvent.VK_RIGHT:
                System.out.println("RIGHT");
                r.moveRight(this);
                break;
            case KeyEvent.VK_UP:
                System.out.println("UP");
                r.moveUp(this);
                break;
            case KeyEvent.VK_DOWN:
                System.out.println("DOWN");
                r.moveDown(this);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    
    @Override
    public String toString(){
        String s = "";
        for(int i = 0; i < map.getColums(); i++){
            for(int j = 0; j < map.getRows(); j++){
                if(tiles[i][j] instanceof Rat){
                    s += "S";
                }
                if(tiles[i][j] instanceof Cheese){
                    s += "G";
                }
                if(tiles[i][j] instanceof Tile){
                    if(((Tile)tiles[i][j]).getWall()){
                        s += "#";
                    }
                    else
                        s += " ";
                }
            }
            s += "\n";
        }
        return s;
    }
}
