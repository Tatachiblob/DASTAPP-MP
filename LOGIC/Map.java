package LOGIC;
/**
 *
 * @author Yuta
 */
public class Map {
    
    //private String mapName;
    private int rows, colums;
    private int[][] map;
    public final static int PATH = 0;
    public final static int WALL = 1;
    public final static int RAT = 2;
    public final static int CHEESE = 3;
    public final static int MAX_SIZE = 30;
    public final static int MIN_SIZE = 15;
    
    public Map(/*String mapName,*/ int rows, int colums){
        //this.mapName = mapName;
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
    
    //public String getMapName(){return this.mapName;}
    public int getRows(){return this.rows;}
    public void setRows(int rows){this.rows = rows;}
    public int getColums(){return this.colums;}
    public void setColums(){this.colums = colums;}
    public int[][] getMap(){return this.map;}
    public void setMap(int x, int y, int status){this.map[y][x] = status;}
}
