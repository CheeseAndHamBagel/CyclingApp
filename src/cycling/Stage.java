package cycling;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Stage is a class that creates a stage object as part of a race.
 * 
 * @author Jude Goulding & Bethany Whiting
 *
 */

public class Stage implements Serializable{

    private StageType stageType;
    private int stageID;
    private String name;
    private String description;
    private double length;
    private LocalDateTime startTime;
    private ArrayList<Segment> segments;
    private boolean waitingForResults = false;

    /**
     * Constructs an instant of a stage
     * 
     * @param stageIdIn
     * @param stageName
     * @param stageDescription
     * @param stageLength
     * @param stageStartTime
     * @param sType
     */
    public Stage(int stageIdIn, String stageName, String stageDescription, double stageLength, LocalDateTime stageStartTime,
    StageType sType){
        stageID = stageIdIn;
        stageType = sType;
        name = stageName;
        description = stageDescription;
        length = stageLength;
        startTime = stageStartTime;
        segments = new ArrayList<Segment>();
    }

    /**
    * Creates a string of all the information about a stage
    *
    * @return A string with the id, name, desciption, start time, type, length and segments 
    */
    public String toString(){
        String segmentList = "";
        for (Segment a : segments){
            segmentList += a.toString() + ",";
        }
        return String.format("[id=%d,name=%s,description=%s,starttime=%s,type=%s,length=%f,segments=[%s]]", stageID, name, description, startTime, stageType, length, segmentList);
    }

    /**
    * Returns the ids of the stage 
    *
    * @return stageID
    */
    public int getStageId(){
        return stageID;
    }

    /**
    * Returns the name of the stage 
    *
    * @return name
    */
    public String getName(){
        return name;
    }

    /** 
    * Returns the decription of the stage
    *
    * @return description 
    */
    public String getDescription(){
        return description;
    }
    
    /**
    * Returns the length of the stage
    *
    * @return length
    */
    public double getStageLength(){
        return length;
    }
    
    /**
    * Gets a new segment object and returns the ID for the climb segment 
    *
    * @return The ID of the new segment 
    */
    public int addCatClimbSegment(int segmentId, Double location, SegmentType type, Double averageGradient, Double length){
        Segment temp = new Segment(segmentId, location, type, averageGradient, length);
        segments.add(temp);
        return temp.getSegmentID();
    }

    /**
    * Gets a new segment object and returns the ID fof the sprint segment
    *
    * @return The ID of the new segment
    */
    public int addSprintSegment(int segmentId, Double location){
        Segment temp = new Segment(segmentId, location);
        segments.add(temp);
        return temp.getSegmentID();
    }

    /**
    * Returns the type of stage it is
    *
    * @return stageType
    */
    public StageType getStageType(){
        return stageType;
    }

    /**
    * Checks if the stage is waiting for results
    *
    * @return waitingForResults
    */
    public boolean isWaiting(){
        return waitingForResults;
    }

    /**
    * Returns a list of segments in a stage 
    *
    * @return segments
    */
    public ArrayList<Segment> getSegments(){
        return segments;
    }

    /**
    * Removes a segment within the index given 
    *
    * @param index
    */ 
    public void removeSeg(int index){
        segments.remove(index);
    }

    /**
    * Sets waitingForResults to true
    */
    public void setWaiting(){
        waitingForResults = true;
    }

    /**
    * Returns an array of id of the segments within a stage
    */
    public int[] getSegmentIDs(){
        int[] stageIDs = new int[segments.size()];
        for (int a = 0; a < segments.size(); ++a){
            stageIDs[a] = segments.get(a).getSegmentID();
        }
        return stageIDs;
    }
}

