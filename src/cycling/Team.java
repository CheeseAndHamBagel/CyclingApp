package cycling;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * The team object. Represents a cycling team if cyclists.
 *  
 * @author Jude Goulding & Bethany Whiting
 * 
 */
public class Team implements Serializable{
    private String name;
    private String description;
    private int teamID;
    private ArrayList<Integer> riders;

    /**
     * Constructs an instant of a team
     * 
     * @param tID
     * @param n
     * @param desc
     */
    public Team(int tID, String n, String desc){
        name = n;
        description = desc;
        teamID = tID;
        riders = new ArrayList<Integer>();
    }

    /**
     * Returns the name of the team
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the description of the team
     * @return description
     */
    public String getDesc(){
        return description;
    }

    /**
     * Returns the ID of the team
     * @return team ID
     */
    public int getTeamID(){
        return teamID;
    }

    /**
     * return a list of the IDs of the riders in the team
     * @return list of rider IDs
     */
    public int[] getRiders(){
        int[] riderIDs = new int[riders.size()];
        for (int a = 0; a < riders.size(); a++){
            riderIDs[a] = riders.get(a);
        }
        return riderIDs;
    }
    
    /**
     * Adds a rider's ID to the list in the team
     * @param riderID
     * @return the ID of the rider that was added
     */
    public int addRider(int riderID){
        riders.add(riderID);
        return riderID;
    }
    
    /**
     * removes a rider from the list to be used in tangent with adjacent methods in the Rider class that modify the team it belongs to
     * @param riderID
     */
    public void removeRider(int riderID){
        for (int a = 0; a < riders.size(); a++){
            if (riders.get(a) == riderID){
                riders.remove(a);
            }
        }
    }

    /**
     * returns a string containing th details of the team
     * @return string representation of the team
     */
    public String toString(){
        String ridersStr = "";
        for (Integer a : riders){
            ridersStr += Integer.toString(a) + ",";
        }
        return String.format("id=%d,name=%s,description=%s,riders=[%s]",teamID,name,description,ridersStr);
    }
}
