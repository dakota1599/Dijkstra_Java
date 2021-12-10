//This class is for taking care of all (most) of the file operations that need to be completed.
package Node;
import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;
public class FileOps {
    
    //Pareses the Roads file.
    public static ArrayList<Node> ParseRoads(File file, int size){
        ArrayList<Node> nodes = new ArrayList<Node>();
        Scanner read;

        //Creates the edges height.
        for(int i = 0; i < size; i++){
            nodes.add(new Node(i));
        }

        //Trys and looks for errors.
        try{
            read = new Scanner(file);
            //Parsing through the file slowly creates the adjaceny lists.
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

                Edge next = new Edge(dest, weight, street);
                if(nodes.get(src).neighbors == null){
                    nodes.get(src).neighbors = next;
                }else{
                    Edge current = nodes.get(src).neighbors;
                    while(true){
                        if(current.next == null){
                            current.next = next;
                            break;
                        }
                        current = current.next;
                    }
                }

                Edge back = new Edge(src, weight, street);
                if(nodes.get(dest).neighbors == null){
                    nodes.get(dest).neighbors = back;
                }else{
                    Edge current = nodes.get(dest).neighbors;
                    while(true){
                        if(current.next == null){
                            current.next = back;
                            break;
                        }
                        current = current.next;
                    }
                }

                //Creates an edge on the edges.
                
            }
            read.close();
            return nodes;
        }
        //Error catching.
        catch(Exception ex){
            System.err.println(ex);
            return null;
        }
        
    }

//------------------------------------------------------------------------
//The locations parsing is divided into two functions.  One parses it by string, int
//values.  The other parses it into int, string values.

    //Parses the Locations file
    public static Dictionary<String, Integer> ParseLocations(File file){
        //This uses a dictionary.
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

    //This costs more,but it makes it easier to refernce what I need based on id or name.
    public static Dictionary<Integer, String> ParseLocationsReverse(File file){
        Dictionary<Integer, String> loc = new Hashtable<Integer, String>();
        Scanner read;
        try{
            read = new Scanner(file);
            while(read.hasNextLine()){
                String[] data = read.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                loc.put(id, name);
            }
            read.close();
            return loc;
        }catch(Exception ex){
            System.err.println(ex);
            return null;
        }
    }

    //Gets the line count of a file.
    //I use this to get the initial line count for each of the files being read.
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
