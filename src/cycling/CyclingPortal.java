package cycling;

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
public class CyclingPortal implements CyclingPortalInterface {
	private ArrayList<Rider> ridersInternal = new ArrayList<Rider>();
	private ArrayList<Team> teamsInternal = new ArrayList<Team>();
	private ArrayList<Race> racesInternal = new ArrayList<Race>();
	private int currentStageID = 0;
	private int currentSegmentID = 0;
	
	/**
	* This method gets the races that are currently in the system
	* 
	* @return An array of raceIds in the system or a empty array if none are in the system
	*/
	@Override
	public int[] getRaceIds() {
		int[] races = {};
		for(int i = 0; i < racesInternal.size(); ){
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
	
	public boolean nameTeamExists(String name, List<Team> arraySet){
		for (Team a : arraySet){
			if (name == a.getName()){
				return true;
			}
		}
		return false;
	}
	
	/**
	* Checks to see if the name given for a race is invalid, not longer then 30 characters and is not blank
	*
	* @param name : Race's name
	* @return boolean value depending on if the name is invalid
	*/
	public boolean nameInValid(String name){
		if (name.length() > 30 || name.contains(" ") || name.equals(null) || name.equals("")){
			return true;
		}
		return false;
	}

	/**
	* This method creates the race within the system with the name and description given
	*
	*@param name : Race's name
	*@param description : Race's description
	*@throws IllegalNameException : If the name given is already within the system
	*@throws InvalidNameException : If the name given is blank or is more then 30 characters long
	*@return 
	*/
	@Override
	public int createRace(String name, String description) throws IllegalNameException, InvalidNameException {
		if (nameRaceExists(name, racesInternal)){
			throw new IllegalNameException();
		}
		if (nameInValid(name)){
			throw new InvalidNameException();
		}
		int index = racesInternal.size();
		Race newRace = new Race(name, description, index);
		racesInternal.add(newRace);
		return racesInternal.get(index).getRaceID();
	}

	@Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			if (a.getRaceID() == raceId){
				return a.toString();
			}
		}
		throw new IDNotRecognisedException();
	}

	@Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
		for (int index = 0; index < racesInternal.size(); index++){
			if (racesInternal.get(index).getRaceID() == raceId){
				racesInternal.remove(index);
			}
		}
		throw new IDNotRecognisedException();
	}

	@Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			if (a.getRaceID() == raceId){
				return a.getStages().size();
			}
		}
		throw new IDNotRecognisedException();
	}

	@Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime,
			StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {
		for (Race a : racesInternal){
			if (a.getRaceID() == raceId){
					return a.addStage(currentStageID++, stageName, description, length, startTime, type);
				}
			}
		throw new IDNotRecognisedException();
	}

	@Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			if (a.getRaceID() == raceId){
				return a.getStageIDs();
			}
		}
		throw new IDNotRecognisedException();
	}

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


	@Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {
		for (Race a : racesInternal){
			for (Stage b : a.getStages()){
				return b.getSegmentIDs();
			}
		}
		throw new IDNotRecognisedException();
	}

	@Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {
		if (nameTeamExists(name, teamsInternal)){
			throw new IllegalNameException();
		}
		else if (nameInValid(name)){
			throw new InvalidNameException();
		}
		int teamID = teamsInternal.size();
		Team team = new Team(teamID, name, description);
		teamsInternal.add(team);
		return teamsInternal.get(teamID).getTeamID();
	}

	@Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
		for(int a = 0; a < teamsInternal.size(); a++){
			if (teamsInternal.get(a).getTeamID() == teamId){
				teamsInternal.remove(a);
			}	
		}
	}

	@Override
	public int[] getTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void eraseCyclingPortal() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveCyclingPortal(String filename) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
		// TODO Auto-generated method stub

	}

	@Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		// TODO Auto-generated method stub
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

}
