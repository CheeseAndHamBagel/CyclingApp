package cycling;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Stage {

    private StageType stageType;
    private int stageID;
    private String name;
    private String description;
    private double length;
    private LocalDateTime startTime;
    private ArrayList<Segment> segments;
    private boolean waitingForResults = false;

    public Stage(int stageIdIn, String stageName, String stageDescription, double stageLength, LocalDateTime stageStartTime,
    StageType sType){
        stageID = stageIdIn;
        stageType = sType;
        name = stageName;
        description = stageDescription;
        length = stageLength;
        startTime = stageStartTime;
    }

    public String toString(){
        String segmentList = "";
        for (Segment a : segments){
            segmentList += a.toString() + ",";
        }
        return String.format("[id=%d,name=%s,description=%s,starttime=%s,type=%s,length=%f,segments=[%s]]", stageID, name, description, startTime, stageType, length, segmentList);
    }

    public int getStageId(){
        return stageID;
    }

    public double getStageLength(){
        return length;
    }
    
    public int addCatClimbSegment(int segmentId, Double location, SegmentType type, Double averageGradient, Double length){
        Segment temp = new Segment(segmentId, location, type, averageGradient, length);
        segments.add(temp);
        return temp.getSegmenID();
    }

    public int addSprintSegment(int segmentId, Double location){
        Segment temp = new Segment(segmentId, location);
        segments.add(temp);
        return temp.getSegmenID();
    }

    public StageType getStageType(){
        return stageType;
    }

    public boolean isWaiting(){
        return waitingForResults;
    }
}
