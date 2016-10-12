package LOGIC;
/**
 *
 * @author Yuta
 */
import java.io.*;
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
        
        return ratCount == 1 && ratCount == 1 && this.rows <= MAX_SIZE && this.colums <= MAX_SIZE && this.rows >= MIN_SIZE && this.colums >= MIN_SIZE;
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
        BufferedWriter bw = null;
        
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
        }finally{
            if(bw != null){
                try{
                    bw.close();
                }catch(IOException e2){}
            }
        }
    }
    
    public static Map loadMap(File f){
        BufferedReader br = null;
        String line;
        Map m = null;
        String con = "";
        
        try{
            br = new BufferedReader(new FileReader(f));
            int content;
            int ctr;
            while((line = br.readLine()) != null){
                con = con + line + "\n";
            }
        }catch(IOException e){
            System.out.println("(Map:loadMap): " + e.toString());
        }finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException e2){}
            }
        }
        
        int row = Integer.parseInt(con.substring(0, con.indexOf(" ")));
        int col = Integer.parseInt(con.substring(con.indexOf(" ") + 1, con.indexOf("\n")));
        con = con.substring(con.indexOf("\n") + 1);
        char[] arr = con.replace("\n", "").toCharArray();
        int x = 0, y = 0;
        m = new Map(row, col);
        for(int i = 0; i < arr.length; i++){
            if(x % row == 0 && x != 0){
                x = 0;
                y++;
            }
            if(arr[i] == ' '){
                m.setMap(x, y, PATH);
            }
            if(arr[i] == '#'){
                m.setMap(x, y, WALL);
            }
            if(arr[i] == 'G'){
                m.setMap(x, y, CHEESE);
            }
            if(arr[i] == 'S'){
                m.setMap(x, y, RAT);
            }
            x++;
        }
        return m;
    }
    public static void main(String[] args) {
        Map a = loadMap(new File("C:\\Users\\Yuta\\Downloads\\new-1.txt"));
        System.out.println(a);
    }
}
