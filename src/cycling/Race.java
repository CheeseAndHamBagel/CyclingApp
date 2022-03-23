package cycling;

import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class Race implements Serializable{
    private int raceID;
    private ArrayList<Stage> stages;
    private String name;
    private String description; 
                        

    public Race(int index, String n, String d){
        name = n;
        description = d;
        raceID = index;
        stages = new ArrayList<Stage>();
    }

    public int getRaceID(){
        return raceID;
    }

    public String getName(){
        return name;
    }

    public int addStage(int stageID, String stageName, String stageDescription, double stageLength, LocalDateTime stageStartTime,
    StageType sType){
        Stage stage = new Stage(stageID, stageName, stageDescription, stageLength, stageStartTime, sType);
        stages.add(stage);
        return stage.getStageId();
    }

    public ArrayList<Stage> getStages(){
        return stages;
    }

    public int[] getStageIDs(){
        int[] stageIDs = new int[stages.size()];
        for (int a = 0; a < stages.size(); ++a){
            stageIDs[a] = stages.get(a).getStageId();
        }
        return stageIDs;
    }

    public String toString(){
        double stageLength = 0.0;
        for (Stage a : stages){
            stageLength += a.getStageLength();
        }
        return String.format("id=%d,name=%s,description=%s,total length=%f km",raceID,name,description,stageLength);
    }

    public String toStringStage(){
        String stagesStr = "";
        for (Stage a : stages){
            stagesStr += a.toString();
        }
        return String.format("id=%d,name=%s,description=%s,stages=[%s]",raceID,name,description,stagesStr);
    }

    public void removeStage(int stageID){
        for (int index = 0; index <= stages.size(); index++){
            if (stages.get(index).getStageId() == stageID){
                stages.remove(index);
            }
        }
    }
}
