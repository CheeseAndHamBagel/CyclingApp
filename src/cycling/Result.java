package cycling;

import java.time.LocalDateTime;

public class Result {
    private int stageID;
    private int riderID;
    private LocalDateTime[] checkpointTimes;

    public Result(int sID, int rID, LocalDateTime... cpTimes){
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

    public LocalDateTime[] getTimes(){
        return checkpointTimes;
    }
}
