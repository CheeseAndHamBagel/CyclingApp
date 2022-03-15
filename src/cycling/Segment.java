package cycling;

public class Segment {

    private SegmentType segmentType;
    private int segmentID;
    private double averageGradient;
    private double length;
    private double location;

    public Segment(int sID, double fin, SegmentType sType, double aGradient, double len){
        segmentType = sType;
        averageGradient = aGradient;
        segmentID = sID;
        length = len;
        location = fin;
    }

    public Segment(int sID, double fin){
        segmentID = sID;
        location = fin; 
        segmentType = SegmentType.SPRINT;
        averageGradient = 0.0;
        length = 0.0;
    }

    public String toString(){
        return String.format("[id=%d, type=%s, average gradient=%s, start location=%d, length=%d]", segmentID, segmentType, averageGradient, location, length);
    }
    
    public int getSegmentID(){
        return segmentID;
    }
}
