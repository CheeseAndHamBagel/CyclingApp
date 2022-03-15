package cycling;
public class Team {
    /*
    name
    description
    id
    list of team members or member ids
    */

    private String name;
    private String description;
    private int teamID;
    private int[] riders; //Official rules usually have 10-20 riders so will be instatiated to have 20 elements, even if most are empty

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
        return riders;
    }
}
