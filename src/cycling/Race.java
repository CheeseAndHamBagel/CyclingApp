package cycling;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;

/**
 * Race is a class that creates a race object.
 * 
 * @author Jude Goulding & Bethany Whiting
 *
 */

public class Race implements Serializable{
    private int raceID;
    private ArrayList<Stage> stages;
    private String name;
    private String description; 
                        
     /**
     * Constructs an instant of a race
     * 
     * @param stageIdIn
     * @param stageName
     * @param stageDescription
     * @param stageLength
     * @param stageStartTime
     * @param sType
     */
    public Race(int index, String n, String d){
        name = n;
        description = d;
        raceID = index;
        stages = new ArrayList<Stage>();
    }

    /**
    * Returns the ID of a race
    *
    * @return raceID
    */
    public int getRaceID(){
        return raceID;
    }

    /**
    * Returns of the name of a race
    *
    * @param name 
    */
    public String getName(){
        return name;
    }

    /**
    * Returns the id of a stage created
    *
    * @param stageID
    * @param stageName
    * @param stageDescription 
    * @param stageLength
    * @param stageStartTime
    * @param sType 
    * @return a Id of the stage
    */ 
    public int addStage(int stageID, String stageName, String stageDescription, double stageLength, LocalDateTime stageStartTime,
    StageType sType){
        Stage stage = new Stage(stageID, stageName, stageDescription, stageLength, stageStartTime, sType);
        stages.add(stage);
        return stage.getStageId();
    }

    /**
    * Returns a list of the stages in a race
    *
    * @return stages
    */
    public ArrayList<Stage> getStages(){
        return stages;
    }

    /**
    * Returns an array of the stage Id from a race
    *
    * @return stageIDs
    */
    public int[] getStageIDs(){
        int[] stageIDs = new int[stages.size()];
        for (int a = 0; a < stages.size(); ++a){
            stageIDs[a] = stages.get(a).getStageId();
        }
        return stageIDs;
    }

    /**
    * Returns a string with all the details for a race
    *
    * @return The details in the format id, name, description and total length 
    */ 
    public String toString(){
        double stageLength = 0.0;
        for (Stage a : stages){
            stageLength += a.getStageLength();
        }
        return String.format("id=%d,name=%s,description=%s,total length=%f km",raceID,name,description,stageLength);
    }

    /**
    * Returns a string of the deatils about a race and the stages in it 
    *
    * @return The details in the format id, name, description and stages
    */
    public String toStringStage(){
        String stagesStr = "";
        for (Stage a : stages){
            stagesStr += a.toString();
        }
        return String.format("id=%d,name=%s,description=%s,stages=[%s]",raceID,name,description,stagesStr);
    }

    /**
    * Removes a stage from a race
    * 
    * @param stageID
    */
    public void removeStage(int stageID){
        for (int index = 0; index <= stages.size(); index++){
            if (stages.get(index).getStageId() == stageID){
                stages.remove(index);
            }
        }
    }
}
