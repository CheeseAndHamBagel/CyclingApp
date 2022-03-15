package cycling;
public class Rider {
    /**
     * The rider class
     * 
     * 
     */

    private String name;
    private int riderID;
    private int yearOfBirth;
    private int teamID;

    /**
     * Rider contructor
     * 
     * @param nameIN
     * @param birthYr
     * @param team
     */
    public Rider(String nameIN, int birthYr, int team){
        name = nameIN;
        yearOfBirth = birthYr;
        teamID = team;
    }
    /**
     * Returns the name of the rider
     * 
     * @return name
     */
    public String getName(){
        return name;
    }
    
    /**
     * Sets the name of the rider to the string given
     * 
     * @param nameIN
     */
    public void setName(String nameIN){
        name = nameIN;
    }

    public int getYear(){
        return yearOfBirth;
    }

    public void setYear(int year){
        yearOfBirth = year;
    }

    public int getRiderID(){
        return riderID;
    }

    public int getTeamID(){
        return teamID;
    }

    public void changeTeam(int newTeamID){
        teamID = newTeamID;
    }
}
