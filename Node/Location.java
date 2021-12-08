package Node;

public class Location implements Comparable<Location>{
    public int id;
    public String name;

    public Location(int id, String name){
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Location loc){
        return this.name.compareTo(loc.name);
    }

    public boolean equals(Location loc){
        if(this.name == loc.name)
            return true;
        else
            return false;
    }
}
