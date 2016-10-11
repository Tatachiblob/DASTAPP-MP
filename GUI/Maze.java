package GUI;
/**
 *
 * @author Yuta
 */
import LOGIC.*;
import java.awt.GridLayout;
import javax.swing.*;
import java.util.ArrayList;
public class Maze extends JFrame {
    
    private ArrayList<Cell> tiles;
    
    public Maze(){
        super("Machine Project");
        this.tiles = new ArrayList<>();
    }
    
    public ArrayList<Cell> getTiles(){return this.tiles;}
    
    public void generateMaze(Map map){
        this.setLayout(new GridLayout(map.getRows(), map.getColums()));
        for(int i = 0; i < map.getColums(); i++){
            for(int j = 0; i < map.getRows(); j++){
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
    
    
}
