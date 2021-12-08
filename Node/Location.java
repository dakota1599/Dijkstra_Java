package Node;

public class Location implements Comparable<Location>{
    public int id;
    public String name;
    public float distance;

    public Location(int id, String name, float distance){
        this.id = id;
        this.name = name;
        this.distance = distance;
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
