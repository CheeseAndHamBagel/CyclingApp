package cycling;

import java.time.LocalTime;
import java.io.Serializable;
import java.time.temporal.ChronoUnit;

/**
 * Resuls class holds the rider ID, stage ID and checkpoint times in one place
 */
public class Result implements Serializable{
    private int stageID;
    private int riderID;
    private LocalTime[] checkpointTimes;

    /**
     * Contructs a result.
     * 
     * @param sID   id of the stage the times were reecorded in
     * @param rID   id of the rider who rode
     * @param cpTimes the list of times through each checkpoint
     */
    public Result(int sID, int rID, LocalTime[] cpTimes){
        stageID = sID;
        riderID = rID;
        checkpointTimes = cpTimes;
    }
    
    /**
     * returns the ID of the rider who achieved the result
     * @return riderID
     */
    public int getRiderID(){
        return riderID;
    }

    /**
     * returns the id of the stage the result was recorded in
     * @return stageID
     */
    public int getStageID(){
        return stageID;
    }


    /**
     * returns an array of times recorded in the stage by the rider
     * @return checkpointTimes
     */
    public LocalTime[] getTimes(){
        return checkpointTimes;
    }
    
    /**
     * returns the total amount of time the stage took to complete, in seconds
     * @return defInt
     */
    public int getTimeTotal(){
        LocalTime start = checkpointTimes[0];
        LocalTime finish = checkpointTimes[checkpointTimes.length-1];
        long difference = start.until(finish, ChronoUnit.SECONDS);
        Integer defInt = Math.toIntExact(difference);
        return defInt;
    }

    /**
     * returns the details of the result as a singe string
     * @return details
     */
    public String toString(){
        return String.format("rider=%d,stage=%d,time=%dseconds",riderID,stageID,this.getTimeTotal());
    }
}
