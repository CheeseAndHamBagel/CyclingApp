package cycling;

import java.io.Serializable;
import java.util.ArrayList;

public class Team implements Serializable{
    private String name;
    private String description;
    private int teamID;
    private ArrayList<Integer> riders;

    public Team(int tID, String n, String desc){
        name = n;
        description = desc;
        teamID = tID;
    }

    public String getName(){
        return name;
    }

    public String getDesc(){
        return description;
    }

    public int getTeamID(){
        return teamID;
    }

    public int[] getRiders(){
        int[] riderIDs = new int[riders.size()];
        for (int a = 0; a < riders.size(); a++){
            riderIDs[a] = riders.get(a);
        }
        return riderIDs;
    }

    public int addRider(int riderID){
        riders.add(riderID);
        return riderID;
    }

    public void removeRider(int riderID){
        for (int a = 0; a < riders.size(); a++){
            if (riders.get(a) == riderID){
                riders.remove(a);
            }
        }
    }
}
