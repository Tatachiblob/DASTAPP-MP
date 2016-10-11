package LOGIC;
/**
 *
 * @author Yuta
 */
import java.io.*;
public class Map {
    
    private String mapName;
    private int rows, colums;
    private int[][] map;
    public final static int PATH = 0;
    public final static int WALL = 1;
    public final static int RAT = 2;
    public final static int CHEESE = 3;
    public final static int MAX_SIZE = 30;
    public final static int MIN_SIZE = 15;
    
    public Map(String mapName, int rows, int colums){
        this.mapName = mapName;
        this.rows = rows;
        this.colums = colums;
        this.map = new int[colums][rows];
        //Initialize Everything to a wall
        for(int x = 0; x < colums; x++){
            for(int y = 0; y < rows; y++){
                map[x][y] = WALL;
            }
        }
    }
    
    public String getMapName(){return this.mapName;}
    public int getRows(){return this.rows;}
    public void setRows(int rows){this.rows = rows;}
    public int getColums(){return this.colums;}
    public void setColums(int colums){this.colums = colums;}
    public int[][] getMap(){return this.map;}
    public void setMap(int x, int y, int status){this.map[y][x] = status;}
    
    public boolean isLegitMap(){
        int ratCount = 0;
        int cheeseCount = 0;
        
        for(int i = 0; i < this.colums; i++){
            for(int j = 0; j < this.rows; j++){
                if(map[i][j] == RAT){
                    ratCount++;
                }
                else if(map[i][j] == CHEESE){
                    cheeseCount++;
                }
            }
        }
        
        return ratCount == 1 && ratCount == 1;
    }
    
    @Override
    public String toString(){
        String map = "";
        for(int i = 0; i < this.colums; i++){
            for(int j = 0; j < this.rows; j++){
                map = map + this.map[i][j];
            }
            map = map + "\n";
        }
        return map;
    }
    
    public void saveMap(File f){
        BufferedWriter bw;
        
        try{
            bw = new BufferedWriter(new FileWriter(f));
            bw.write(Integer.toString(rows));
            bw.write(" ");
            bw.write(Integer.toString(colums));
            bw.newLine();
            for(int i = 0; i < this.colums; i++){
                for(int j = 0; j < this.rows; j++){
                    if(map[i][j] == PATH){
                        bw.write(" ");
                    }
                    if(map[i][j] == WALL){
                        bw.write("#");
                    }
                    if(map[i][j] == RAT){
                        bw.write("S");
                    }
                    if(map[i][j] == CHEESE){
                        bw.write("G");
                    }
                }
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }catch(IOException e){
            System.out.println("(Map:saveMap) " + e.toString());
        }
    }
    public static void main(String[] args) {
        Map m = new Map("Kiku", 5, 10);
        m.setMap(0, 0, 0);
        m.setMap(1, 1, 0);
        m.setMap(2, 1, 0);
        System.out.println(m);
        m.saveMap(new File("C:\\Users\\Yuta\\Documents\\2nd year 1st term\\Yuta.txt"));
    }
}
