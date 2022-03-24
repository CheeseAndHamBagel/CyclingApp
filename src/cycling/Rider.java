package cycling;

import java.io.Serializable;

public class Rider implements Serializable{
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
    public Rider(int rID, String nameIN, int birthYr, int team){
        riderID = rID;
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

    /**
     * Returns the year that rider was born in
     * @return yearOfBirth
     */
    public int getYear(){
        return yearOfBirth;
    }

    /**
     * Sets the riders year of birth to be the inputted integer
     * @param year
     */
    public void setYear(int year){
        yearOfBirth = year;
    }

    /**
     * returns thhe ID of the rider
     * @return riderID
     */
    public int getRiderID(){
        return riderID;
    }

    /**
     * returns the ID of the team that the rider is in
     * @return teamID
     */
    public int getTeamID(){
        return teamID;
    }

    /**
     * Sets the rider's team to the inputted team. To be used with addRider or removeRider to change the rider's team in the system
     * @param newTeamID
     */
    public void changeTeam(int newTeamID){
        teamID = newTeamID;
    }

    /**
     * returns a string of the details of the rider
     * @return details
     */
    public String toString(){
        return String.format("id=%d,name=%s,yob=%d,team=%d",riderID,name,yearOfBirth,teamID);
    }
}
