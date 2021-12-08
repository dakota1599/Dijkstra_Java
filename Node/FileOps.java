package Node;
import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
public class FileOps {
    
    //Pareses the Roads file.
    public static ArrayList<ArrayList<Node>> ParseRoads(File file, int size){
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        Scanner read;

        for(int i = 0; i < size; i++){
            graph.add(new ArrayList<>());
        }

        try{
            read = new Scanner(file);
            while(read.hasNextLine()){
                String[] data = read.nextLine().split(",");
                int src = Integer.parseInt(data[0]);
                int dest = Integer.parseInt(data[1]);
                float weight = Float.parseFloat(data[2]);
                String street;
                if(data.length >= 4)
                    street = data[3];
                else
                    street = "";

                graph.get(src).add(new Node(dest, weight));
            }
            read.close();
            return graph;
        }catch(Exception ex){
            System.err.println(ex);
            return null;
        }
        
    }

    //Parses the Locations file
    public static Dictionary<String, Integer> ParseLocations(File file){
        Dictionary<String, Integer> loc = new Hashtable<String, Integer>();
        Scanner read;
        try{
            read = new Scanner(file);
            while(read.hasNextLine()){
                String[] data = read.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                loc.put(name, id);
            }
            read.close();
            return loc;
        }catch(Exception ex){
            System.err.println(ex);
            return null;
        }
        
    }

    //Gets the line count of a file.
    public static int GetLineCount(File file){
        Scanner read;
        int i = 0;
        try{
            read = new Scanner(file);
            
            while(read.hasNextLine()){
                read.nextLine();
                i++;
            }
            read.close();
            return i;
        }catch(Exception ex){
            System.err.println(ex);
            return -1;
        }

        
    }
}
