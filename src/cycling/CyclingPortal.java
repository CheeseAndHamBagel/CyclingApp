package cycling;

import java.io.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * CyclingPortal is a minimally compiling, but non-functioning implementor
 * of the CyclingPortalInterface interface.
 * 
 * @author Jude Goulding & Bethany Whiting
 * @version 1.13.6
 *
 */
public class CyclingPortal implements CyclingPortalInterface{
	private ArrayList<Rider> ridersInternal = new ArrayList<Rider>();
	private ArrayList<Team> teamsInternal = new ArrayList<Team>();
	private ArrayList<Race> racesInternal = new ArrayList<Race>();
	private ArrayList<Result> resultsInternal = new ArrayList<Result>();
	private int currentStageID = 0;
	private int currentSegmentID = 0;
	private int currentRaceID = 0;
	private int currentTeamID = 0;
	private int currentRiderID = 0;

	public CyclingPortal(){
		ridersInternal = new ArrayList<Rider>();
		teamsInternal = new ArrayList<Team>();
		racesInternal = new ArrayList<Race>();
		resultsInternal = new ArrayList<Result>();
		currentStageID = 0;
		currentSegmentID = 0;
		currentRaceID = 0;
		currentTeamID = 0;
		currentRiderID = 0;
	}
	/**
	* This method gets the races that are currently in the system
	* 
	* @return An array of raceIds in the system or a empty array if none are in the system
	*/
	@Override
	public int[] getRaceIds() {
		int[] races = new {racesInternal.size()};
		for(int i = 0; i < racesInternal.size(); i++ ){
			races[i] = racesInternal.get(i).getRaceID();
		}
		return races;
	}

	/**
	* This method checks to see if the name of the race exists
	*
	* @param name : Race's name 
	* @param arraySet race : List of races within the system 
	* @return boolean value depending on if the name is already in the system
	*/
	public boolean nameRaceExists(String name, List<Race> arraySet){
		for (Race a : arraySet){
			if (name == a.getName()){
				return true;
			}
		}
		return false;
	}

	/**
	* This method checks to see if the name of the race exists
	*
	* @param name : Team's name 
	* @param arraySet race : List of teams within the system 
	* @return boolean value depending on if the name is already in the system
	*/
	public boolean nameTeamExists(String name, List<Team> arraySet){
		for (Team a : arraySet){
			if (name == a.getName()){
				return true;
			}
		}
		return false;
	}

	/**
	* Checks to see if the name given for a race is valid, not longer then 30 characters and is not blank
	*
	* @param name : Race's name
	* @return boolean value depending on if the name is invalid, true if invalid and false if valid
	*/
	public boolean nameInValid(String name){
		if (name.length() > 30 || name.contains("\s") || name.contains(" ") || name.equals(null) || name.equals("")){
			return true;
		}
		return false;
	}

	/**
	* This method creates the race within the system with the name and description given
	*
	* @param name : Race's name
	* @param description : Race's description
	* @throws IllegalNameException : If the name given is already within the system
	* @throws InvalidNameException : If the name given is blank or is more then 30 characters long
	* @return A unique Id for the race created
	*/
	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		if (nameRaceExists(name, racesInternal)){
			throw new IllegalNameException("That name is already taken");
		}
		if (nameInValid(name)){
			throw new InvalidNameException("That name is invalid");
		}
		Race newRace = new Race(currentRaceID++, name, description);
		racesInternal.add(newRace);
		return racesInternal.get(racesInternal.size()-1).getRaceID();
	}

	/**
	* Getting the details about a race from its ID
	*
	* @param raceId : The ID of the race wishing to find details on
	* @throws IDNotRecognisedException : If the ID is not within the system
	* @return A formatted string in the form raceID,name,description,stageLength
	*/
	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			if (a.getRaceID() == raceId){
				return a.toString();
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* This method removes the race and all the details about it 
	*
	* @param raceId : The Id is the race wanted to be removed
	* @throws IDNotRecgonisedException : If the Id is not within the system
	*/
	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		for (int index = 0; index < racesInternal.size(); index++){
			if (racesInternal.get(index).getRaceID() == raceId){
				racesInternal.remove(index);
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* The method gets the number of stages within a race
	*
	* @param raceId : The ID of the race that is being queried
	* @throws IDNotRecognisedException : If the Id is not within the system
	* @return The number of stages that have been created for the race
	*/
	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			if (a.getRaceID() == raceId){
				return a.getStages().size();
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* This creates a new stage within a race
	*
	* @param raceId : The Id of the race that stage wants to be added to
	* @param stageName : The name for the stage
	* @param description : A description for the stage 
	* @param length : The length of the stage in kilometers
	* @param startTime : The data and time at which the stage will be raced
	* @throws IDNotRecognisedException : If the Id is not within the system
	* @throws IllegalNameException : If the name given is blank or is more then 30 characters long
	* @throws InvalidLengthException : If the length is less than 5km
	* @return A unique Id for the stage created
	*/
	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		if (nameInValid(stageName)){
			throw new InvalidNameException("Invalid name");
		}
		if (length < 5.0){
			throw new InvalidLengthException();
		}
		for (Race a : racesInternal){
			if (a.getRaceID() == raceId){
				if ( a.getStages() != null){
					for (Stage b : a.getStages()){
						if (b.getName() == stageName){
							throw new IllegalNameException();
							}
						}
					}
				return a.addStage(currentStageID++, stageName, description, length, startTime, type);
				}
			}
		throw new IDNotRecognisedException();
	}

	/**
	* Gets a list of the id of the stages that are in a race
	*
	* @param raceId : The Id of the race that is wanted
	* @throws IdNotRecognisedException : If the Id is not within the system
	* @return The list of stage IDs within the wanted race, ordered from first to last.
	*/
	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			if (a.getRaceID() == raceId){
				return a.getStageIDs();
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* This method gets the length of a stage in km
	*
	* @param stageId : The ID of the stage needed
	* @throws IdNotRecognisedException : If the Id is not within the system
	* @return The length of the stage
	*/
	@Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			for (Stage b : a.getStages()){
				if(b.getStageId() == stageId){
					return b.getStageLength();
				}
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* The method removes a stage and all the details relating to it 
	*
	* @param stageId : The ID of the stage wanted
	* @throws IdNotRecognisedException : If the Id is not within the system
	*/
	@Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			for (int b : a.getStageIDs()){
				if(b == stageId){
					a.removeStage(b);
				}
			}
		}
		throw new IDNotRecognisedException();

	}

	/**
	* Adds a climb segment to a stage
	*
	* @param stageId : The ID of the stage that you want to add a climb to 
	* @param location : The location in km where the climb finishes within the stage
	* @param type :  The type of the climb - {@link SegmentType#C4}, {@link SegmentType#C3}, {@link SegmentType#C2}, {@link SegmentType#C1}, or {@link SegmentType#HC}.
	* @param averagerGradient : The average gradient of the climb
	* @param length : The length of the climb in km
	* @throws IDNotRecognisedException : If the Id is not within the system
	* @throws InvalidLocationException : If the location is not within the length of the stage
	* @throws InvalidStageStateException : The stage is waiting for results
	* @throws InvalidStageTypeException : Time-trial stages cannot contain any segments 
	* @return The ID of the segment created
	*/
	@Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient,
			Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {

		for (Race a : racesInternal){
			for (Stage b : a.getStages()){
				if(b.getStageId() == stageId){
					if (location < 0 || location >= b.getStageLength()){
						throw new InvalidLocationException();
					}
					else if (b.getStageType() == StageType.TT){
						throw new InvalidStageTypeException();
					}
					else if (b.isWaiting()){
						throw new InvalidStageStateException();
					}
					b.addCatClimbSegment(currentSegmentID++, location, type, averageGradient, length);
				}
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* Adds a sprint segment to a stage
	*
	* @param stageId : The Id of the stage you wish to put the sprint into
	* @param location : The location in km where the sprint finshed within the stage
	* @throws IDNotRecognisedException : If the Id is not within the system
	* @throws InvalidLocationException : If the location is not within the length of the stage
	* @throws InvalidStageStateException : The stage is waiting for results
	* @throws InvalidStageTypeException : Time-trial stages cannot contain any segments 
	* @return The ID of the segment created
	*/
	@Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {
		for (Race a : racesInternal){
			for (Stage b : a.getStages()){
				if(b.getStageId() == stageId){
					if (location < 0 || location >= b.getStageLength()){
						throw new InvalidLocationException();
					}
					else if (b.getStageType() == StageType.TT){
						throw new InvalidStageTypeException();
					}
					else if (b.isWaiting()){
						throw new InvalidStageStateException();
					}
					b.addSprintSegment(currentSegmentID++, location);
				}
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* Removes a segment from a stage
	*
	* @param segmentId : The ID of the segment wishing to be you removed
	* @throws IDNotRecognisedException : If the Id is not within the system
	* @throws InvalidStageStateException : The stage is waiting for results
	*/
	@Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {
		for (Race a : racesInternal){
			for (Stage b : a.getStages()){
				for (int c = 0; c < b.getSegments().size(); c++){
					if (b.getSegments().get(c).getSegmentID() == segmentId){
						if(b.isWaiting()){
							throw new InvalidStageStateException();
						}
						b.removeSeg(c);
					}
				}
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* This concludes the preparation of a stage and will make the state of the stage "waiting for results"
	*
	* @param stageId : The ID of the stage to be concluded 
	* @throws IDNotRecognisedException : If the Id is not within the system
	* @throws InvalidStageStateException : The stage is waiting for results
	*/
	@Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		for (Race a : racesInternal){
			for (Stage b : a.getStages()){
				if (b.getStageId() == stageId){
					if(b.isWaiting()){
						throw new InvalidStageStateException();
					}
					b.setWaiting();
				}
			}
		}
		throw new IDNotRecognisedException();
	}

	/** 
	* Gets all the segments within a stage
	*
	* @param stageId : The ID of the stage wanted
	* @throws IDNotRecognisedException : If the Id is not within the system
	* @return A list of segment IDs ordered by there location within the stage
	*/
	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			for (Stage b : a.getStages()){
				return b.getSegmentIDs();
			}
		}
		throw new IDNotRecognisedException();
	}

	/**
	* Creates a team using a name and desciption
	* 
	* @param name : The name of the team 
	* @param description : The desciption of the team 
	* @throws IllegalNameException : If the name given is already within the system
	* @throws InvalidNameException : If the name given is blank or is more then 30 characters long
	* @return A ID for the team
	*/
	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		if (nameTeamExists(name, teamsInternal)){
			throw new IllegalNameException();
		}
		else if (nameInValid(name)){
			throw new InvalidNameException();
		}
		Team team = new Team(currentTeamID++, name, description);
		teamsInternal.add(team);
		return teamsInternal.get(teamsInternal.size()-1).getTeamID();
	}

	/**
	* Removes a team
	*
	* @param teamId : The ID of the team to be removed
	* @throws IDNotRecognisedException : If the Id is not within the system
	*/
	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		for(int a = 0; a < teamsInternal.size(); a++){
			if (teamsInternal.get(a).getTeamID() == teamId){
				teamsInternal.remove(a);
			}	
		}
	}

	/**
	* Gets all the team IDs in the system 
	*
	* @return The list of IDs of teams
	*/
	@Override
	public int[] getTeams() {
		int teamsSize = teamsInternal.size();
		int[] teamIDs = new int[teamsSize];
		for (int a = 0; a < teamsSize; a++){
			teamIDs[a] = teamsInternal.get(a).getTeamID();
		}
		return teamIDs;
	}

	/**
	* Get the riders within a team 
	*
	* @param teamId : The Id of the team wanted
	* @throws IDNotRecognisedException : If the Id is not within the system
	* @return A list of hte riders' ID
	*/
	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		for(Team a : teamsInternal){
			if(a.getTeamID() == teamId){
				return a.getRiders();
			}
		}
		throw new IDNotRecognisedException();
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		for(Team a : teamsInternal){
			if(a.getTeamID() == teamID){
				if (name == null || yearOfBirth <= 1900){
					throw new IllegalArgumentException();
				}
				
				Rider temp = new Rider(currentRiderID++, name, yearOfBirth, teamID);
				ridersInternal.add(temp);
				return a.addRider(temp.getRiderID());
			}
		}
		throw new IDNotRecognisedException();

	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		for (int a = 0; a < ridersInternal.size(); a++){
        	if(ridersInternal.get(a).getRiderID() == riderId){
				
				//remove from system
				Rider riderToRemove = ridersInternal.get(a);
				ridersInternal.remove(a);

				//remove from team
				for(Team b : teamsInternal){
					if(b.getTeamID() == riderToRemove.getTeamID()){
						b.removeRider(riderToRemove.getRiderID());						
					}
				}

				//remove results
				for (int c = 0; c < resultsInternal.size(); c++){
					Result currentResult = resultsInternal.get(c);
					if (currentResult.getRiderID() == riderToRemove.getRiderID()){
						resultsInternal.remove(c);
					}
				}
			}
        }
		throw new IDNotRecognisedException();

	}


	public boolean riderIDInvalid(int riderID){
		for (Rider a : ridersInternal){
			if(a.getRiderID() == riderID){
				return false;
			}
		}
		return true;
	}


	public boolean stageIDInvalid(int stageID){
		for (Race a : racesInternal){
			for(int b : a.getStageIDs()){
				if (b == stageID){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		if (riderIDInvalid(riderId) || stageIDInvalid(stageId)){
			throw new IDNotRecognisedException();
		}
		LocalTime temp = checkpoints[0]; 
		for(LocalTime a : checkpoints){
			if (a.isBefore(temp)){
				throw new InvalidCheckpointsException();
			}
			temp = a;
		}
		Result newResult = new Result(stageId, riderId, checkpoints);
		resultsInternal.add(newResult);
	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		for (Result a : resultsInternal){
			if (a.getRiderID() == riderId && a.getStageID() == stageId){
				return a.getTimes();
			}
		}
		throw new IDNotRecognisedException();
	}


	public void sortResultsByTime(){
		int n = resultsInternal.size();  
        for (int j = 1; j < n; j++) {  
            Result key = resultsInternal.get(j);
            int i = j-1;
            while ( (i > -1) && ( resultsInternal.get(i).getTimeTotal() > key.getTimeTotal() ) ) {
                resultsInternal.set(i+1,resultsInternal.get(i));
                i--;
            }
            resultsInternal.set(i+1, key);
        }
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		int desiredRiderIndex = -1;
		ArrayList<Integer> riderTimes = new ArrayList<Integer>();
		int index = 0;
		sortResultsByTime();

		//step through to find the result needed
		for (Result a : resultsInternal){
			if(a.getStageID() == stageId){
				if(a.getRiderID() == riderId){
					desiredRiderIndex = index;
				}
				
				int currentRiderTime = a.getTimeTotal();
				riderTimes.add(currentRiderTime);
			}
		}

		if (desiredRiderIndex < 0){
			throw new IDNotRecognisedException();		
		}

		//step backwards through the results to adjust the times

		Integer prevCheckedTime = riderTimes.get(riderTimes.size()-1);
		Integer smallestInSectionIndex = riderTimes.size() - 1;

		for (int i = riderTimes.size()-1; i >= 0; i--){
			int currentTime = riderTimes.get(i);
			int difference = currentTime - prevCheckedTime;

			if (difference > 1 || (difference <= 1 && i == 0)){
				for(int a = i+1; a <= smallestInSectionIndex; a++){
					riderTimes.set(a,prevCheckedTime);
				}
				smallestInSectionIndex = i;
			}
		}

		int desiredRiderTime = riderTimes.get(desiredRiderIndex);
		int hours = desiredRiderTime / 3600;
		int minutes = (desiredRiderTime % 3600) / 60;
		int seconds = desiredRiderTime % 60;
		LocalTime desiredRiderAdjustedTime = LocalTime.of(hours, minutes, seconds);
		return desiredRiderAdjustedTime;
	}
	
	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		for (int a = 0; a < resultsInternal.size(); a ++){
			if (resultsInternal.get(a).getRiderID() == riderId && resultsInternal.get(a).getStageID() == stageId){
				resultsInternal.remove(a);
			}
		}
		throw new IDNotRecognisedException();
	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		ArrayList<Integer> riderIDsInOrder = new ArrayList<Integer>();
		sortResultsByTime();
		for(Result res : resultsInternal){
			if (res.getStageID() == stageId){
				riderIDsInOrder.add(res.getRiderID());
			}
		}
		if (riderIDsInOrder.size() == 0){
			throw new IDNotRecognisedException();
		}
		int[] ridersIntArray = new int[riderIDsInOrder.size()];
		for (int i = 0; i < ridersIntArray.length; i++){
			ridersIntArray[i] = riderIDsInOrder.get(i);
		}
		return ridersIntArray;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		int desiredRiderIndex = -1;
		ArrayList<Integer> riderTimes = new ArrayList<Integer>();
		sortResultsByTime();

		//step through to find the result needed
		for (Result a : resultsInternal){
			if(a.getStageID() == stageId){
				int currentRiderTime = a.getTimeTotal();
				riderTimes.add(currentRiderTime);
			}
		}

		if (desiredRiderIndex < 0){
			throw new IDNotRecognisedException();		
		}

		//step backwards through the results to adjust the times

		Integer prevCheckedTime = riderTimes.get(riderTimes.size()-1);
		Integer smallestInSectionIndex = riderTimes.size() - 1;
		LocalTime[] riderAdjustedTimesArray = new LocalTime[riderTimes.size()];

		for (int i = riderTimes.size()-1; i >= 0; i--){
			int currentTime = riderTimes.get(i);
			int difference = currentTime - prevCheckedTime;

			if (difference > 1 || (difference <= 1 && i == 0)){
				for(int a = i+1; a <= smallestInSectionIndex; a++){
					riderTimes.set(a,prevCheckedTime);
				}
				smallestInSectionIndex = i;
			}

		}
		for (int i = 0; i < riderTimes.size()-1; i++){
			int timeSeconds = riderTimes.get(i);
			int hours = timeSeconds / 3600;
			int minutes = (timeSeconds % 3600) / 60;
			int seconds = timeSeconds % 60;
			riderAdjustedTimesArray[i] = LocalTime.of(hours, minutes, seconds);
		}
		return riderAdjustedTimesArray;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		final int[] POINTS_FLAT_STAGE = {50,30,20,18,16,14,12,10,8,7,6,5,4,3,2};
		final int QUALIFYING = 15;
		//get rider IDs in order
		ArrayList<Integer> riderIDsInOrder = new ArrayList<Integer>();
		sortResultsByTime();
		for(Result res : resultsInternal){
			if (res.getStageID() == stageId){
				riderIDsInOrder.add(res.getRiderID());
			}
		}
		if (riderIDsInOrder.size() == 0){
			throw new IDNotRecognisedException();
		}

		//find stage type - points are allocated differently
		StageType desiredStageType = null;
		for (Race race : racesInternal){
			for (Stage stage : race.getStages()){
				if (stage.getStageId() == stageId){
					desiredStageType = stage.getStageType();
				}
			}
		}
		//Assume that the desired stage is already a flat stage
		
		//replace IDs with points for each entry
		for (int i = 0; i < riderIDsInOrder.size(); i++){
			if (i <= QUALIFYING){
				riderIDsInOrder.set(i,0);
			}
			switch (desiredStageType){
				case HIGH_MOUNTAIN:
					riderIDsInOrder.set(i,POINTS_FLAT_STAGE [i]);
					break;
				default:
					break;

			}
		}
		
		//swap from arraylist to int[]
		int[] ridersIntArray = new int[riderIDsInOrder.size()];
		for (int i = 0; i < ridersIntArray.length; i++){
			ridersIntArray[i] = riderIDsInOrder.get(i);
		}
		return ridersIntArray;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		final int[] POINTS_MID_MNTN_STAGE = {30,25,22,19,17,15,13,11,9,7,6,5,4,3,2};
		final int[] POINTS_HIGH_MNTN_STAGE = {20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
		final int QUALIFYING = 15;
		//get rider IDs in order
		ArrayList<Integer> riderIDsInOrder = new ArrayList<Integer>();
		sortResultsByTime();
		for(Result res : resultsInternal){
			if (res.getStageID() == stageId){
				riderIDsInOrder.add(res.getRiderID());
			}
		}
		if (riderIDsInOrder.size() == 0){
			throw new IDNotRecognisedException();
		}

		//find stage type - points are allocated differently
		StageType desiredStageType = null;
		for (Race race : racesInternal){
			for (Stage stage : race.getStages()){
				if (stage.getStageId() == stageId){
					desiredStageType = stage.getStageType();
				}
			}
		}
		//Assume that the desired stage is already a flat stage
		
		//replace IDs with points for each entry
		for (int i = 0; i < riderIDsInOrder.size(); i++){
			if (i <= QUALIFYING){
				riderIDsInOrder.set(i,0);
			}
			switch (desiredStageType){
				case HIGH_MOUNTAIN:
					riderIDsInOrder.set(i,POINTS_HIGH_MNTN_STAGE[i]);
					break;
				case MEDIUM_MOUNTAIN:
					riderIDsInOrder.set(i,POINTS_MID_MNTN_STAGE[i]);
					break;
				default:
					break;
			}
		}
		
		//swap from arraylist to int[]
		int[] ridersIntArray = new int[riderIDsInOrder.size()];
		for (int i = 0; i < ridersIntArray.length; i++){
			ridersIntArray[i] = riderIDsInOrder.get(i);
		}
		return ridersIntArray;
	}

	@Override
	public void eraseCyclingPortal() {
		ridersInternal = new ArrayList<Rider>();
		teamsInternal = new ArrayList<Team>();
		racesInternal = new ArrayList<Race>();
		resultsInternal = new ArrayList<Result>();
		currentStageID = 0;
		currentSegmentID = 0;
		currentRaceID = 0;
		currentTeamID = 0;
		currentRiderID = 0;
	}

	@Override 
	public void saveCyclingPortal(String filename) throws IOException {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename+".ser"))) {
			out.writeObject(ridersInternal);
			out.writeObject(teamsInternal);
			out.writeObject(racesInternal);
			out.writeObject(resultsInternal);
			out.writeObject(currentStageID);
			out.writeObject(currentSegmentID);
			out.writeObject(currentRaceID);
			out.writeObject(currentTeamID);
			out.writeObject(currentRiderID);
		}		
	}

	@Override
	@SuppressWarnings("unchecked")
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename+".ser"))) {
			ridersInternal = (ArrayList<Rider>) in.readObject();
			teamsInternal =  (ArrayList<Team>) in.readObject();
			racesInternal =  (ArrayList<Race>) in.readObject();
			resultsInternal = (ArrayList<Result>)  in.readObject();
			currentStageID =  (Integer) in.readObject();
			currentSegmentID = (Integer)  in.readObject();
			currentRaceID =  (Integer) in.readObject();
			currentTeamID = (Integer) in.readObject();
			currentRiderID= (Integer) in.readObject();
		} 
	}

//The end of MiniCyclingPortalInterface
//The beginning of CyclingPortalInterface
	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		for (int a = 0; a < racesInternal.size(); a++){
			if (racesInternal.get(a).getName().equals(name)){
				racesInternal.remove(a);
			}
		}
		throw new NameNotRecognisedException();

	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		
		////-----rethink after getRidersGeneralClassificationRank-----////
		/*

		int[] requiredStagesFromRace = {};
		Integer amountOfStages = null;
		for (Race a : racesInternal){
			if(a.getRaceID() == raceId){
				requiredStagesFromRace = a.getStageIDs();
				amountOfStages = requiredStagesFromRace.length;
			}
		}
		sortResultsByTime();
		//get race results per stage
		for (Result a : resultsInternal){
			//search through results
			//find results in order
			//find riderIDs in order of times
			//find times in order

		}
		//sum times
		//sum points
		//sort both in parallel
		//


		//get rider order per stage
		//find total eadjusted time for each stage
		//total points
		//sort both lists so that points are in order of time
		
		*/
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}


	private ArrayList<Result> getResultListInStage(int stageID){
		ArrayList<Result> resultsNeeded = new ArrayList<Result>();
		for (Result res : resultsInternal){
			if (res.getStageID() == stageID){
				resultsNeeded.add(res);
			}
		}
		return resultsNeeded;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		//find the race in racesInternal
		Race raceInQuestion = null;
		for (Race race : racesInternal){
			if (race.getRaceID() == raceId){
					raceInQuestion = race;
			}
		}

		if (raceInQuestion == null){
			throw new IDNotRecognisedException();
		}

		//iterate through stages in the race
		sortResultsByTime();
		ArrayList<Result> resultsInStage = new ArrayList<Result>();
		for (Stage stage : raceInQuestion.getStages()){
			//find a list of results for that stage
			resultsInStage = getResultListInStage(stage.getStageId());
			



		}
		return null;
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] debugTeamsToString(){
		int size = teamsInternal.size();
		String[] teamsAsStrings = new String[size];
		for (int i = 0; i < size; i++){
			teamsAsStrings[i] = teamsInternal.get(i).toString();
		}
		return teamsAsStrings;
	}

	public String[] debugRidersToString(){
		int size = ridersInternal.size();
		String[] ridersAsStrings = new String[size];
		for (int i = 0; i < size; i++){
			ridersAsStrings[i] = ridersInternal.get(i).toString();
		}
		return ridersAsStrings;
	}

	public String[] debugRacesToStringLen(){
		int size = racesInternal.size();
		String[] racesAsStrings = new String[size];
		for (int i = 0; i < size; i++){
			racesAsStrings[i] = racesInternal.get(i).toString();
		}
		return racesAsStrings;
	}

	public String[] debugRacesToStringStage(){
		int size = racesInternal.size();
		String[] racesAsStrings = new String[size];
		for (int i = 0; i < size; i++){
			racesAsStrings[i] = racesInternal.get(i).toStringStage();
		}
		return racesAsStrings;
	}

	public String[] debugResultsToString(){
		int size = resultsInternal.size();
		String[] resultsAsStrings = new String[size];
		for (int i = 0; i < size; i++){
			resultsAsStrings[i] = resultsInternal.get(i).toString();
		}
		return resultsAsStrings;
	}
}
