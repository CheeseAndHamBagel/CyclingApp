package cycling;

import java.time.LocalTime;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;

public class Result implements Serializable{
    private int stageID;
    private int riderID;
    private LocalTime[] checkpointTimes;

    public Result(int sID, int rID, LocalTime[] cpTimes){
        stageID = sID;
        riderID = rID;
        checkpointTimes = cpTimes;
    }

    public int getRiderID(){
        return riderID;
    }

    public int getStageID(){
        return stageID;
    }

    public LocalTime[] getTimes(){
        return checkpointTimes;
    }
    
    public int getTimeTotal(){
        LocalTime start = checkpointTimes[0];
        LocalTime finish = checkpointTimes[checkpointTimes.length-1];
        long difference = start.until(finish, ChronoUnit.SECONDS);
        Integer defInt = Math.toIntExact(difference);
        return defInt;
    }
}
