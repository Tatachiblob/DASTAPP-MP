package LOGIC;
/**
 *
 * @author Yuta
 */
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.util.ArrayList;
public class Maze extends JPanel {
    
    private ArrayList<Cell> tiles;
    
    public Maze(){
        this.tiles = new ArrayList<>();
    }
    
    public ArrayList<Cell> getTiles(){return this.tiles;}
    
    public void generateMaze(Map map){
        this.setLayout(new GridLayout(map.getRows(), map.getColums()));
        
        for(int i = 0; i < map.getMap().length; i++){
            
        }
    }
    
    
}
