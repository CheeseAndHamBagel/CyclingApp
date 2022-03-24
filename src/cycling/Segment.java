package cycling;

import java.io.Serializable;

/**
 * Segment is a class that creates a Segment object.
 * 
 * @author Jude Goulding & Bethany Whiting
 *
 */

public class Segment implements Serializable{

    private SegmentType segmentType;
    private int segmentID;
    private double averageGradient;
    private double length;
    private double location;

    /**
     * Constructs an instant of a segment
     * 
     * @param sID
     * @param fin
     * @param sType
     * @param aGradient
     * @param len
     */
    public Segment(int sID, double fin, SegmentType sType, double aGradient, double len){
        segmentType = sType;
        averageGradient = aGradient;
        segmentID = sID;
        length = len;
        location = fin;
    }

    /**
    * Adds to the instant of a segment
    *
    * @param sID
    * @param fin
    */
    public Segment(int sID, double fin){
        segmentID = sID;
        location = fin; 
        segmentType = SegmentType.SPRINT;
        averageGradient = 0.0;
        length = 0.0;
    }

    /**
    * Creates string of detail of a segment 
    *
    * @return A string in the format id, type, gradient, location, length 
    */
    public String toString(){
        return String.format("[id=%d, type=%s, average gradient=%s, start location=%d, length=%d]", segmentID, segmentType, averageGradient, location, length);
    }
    
    /**
    * Returns the ID of a segment
    *
    * @return segmentID
    */
    public int getSegmentID(){
        return segmentID;
    }
}
