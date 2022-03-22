import cycling.CyclingPortal;
import cycling.CyclingPortalInterface;
import cycling.StageType;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.sound.midi.Soundbank; 


/**
 * A short program to illustrate an app testing some minimal functionality of a
 * concrete implementation of the CyclingPortalInterface interface -- note you
 * will want to increase these checks, and run it on your CyclingPortal class
 * (not the BadCyclingPortal class).
 *
 * 
 * @author 
 * @version 1.0
 */
public class CyclingPortalInterfaceTestApp {

	/**
	 * Test method.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		System.out.println("The system compiled and started the execution...");
		CyclingPortal portal = new CyclingPortal();

		assert (portal.getRaceIds().length == 0)
				: "Innitial SocialMediaPlatform not empty as required or not returning an empty array.";

		try{
			System.out.println(portal.createRace("Test1","For Testing"));
			for (int i = 0; i < 7; i++){
				portal.addStageToRace(0, "TestStage"+Integer.toString(i), "For Testing", 50.0, LocalDateTime.now(), StageType.FLAT);
			}

			for (Integer i = 0; i <= 5; i++){
				String name = "Team" + Integer.toString(i);
				String description = "Team for testing purposes";
				portal.createTeam(name, description);
			}

			for (Integer i = 0; i <= 28; i++){
				String name = "Bob" + Integer.toString(i);
				int dob = 2000;
				portal.createRider((i % 6), name, dob);
			}

			for (String a : portal.debugTeamsToString()){
				System.out.println(a);
			}
			for (String a : portal.debugRidersToString()){
				System.out.println(a);
			}

			for (String a : portal.debugRacesToStringStage()){
				System.out.println(a);
			}

			portal.registerRiderResultsInStage(0, 0, LocalTime.of(0,9,3),LocalTime.of(0,13,4));
			
			/*

			///Test recording results

			for (int i : portal.getRaceStages(0)){
				for (int j : portal.getTeams()){
					for (int k : portal.getTeamRiders(j)){
						int time1 = (i+4) * (j+5) * 3 * (j+9);
						LocalTime lTime1 = LocalTime.of((time1%(3600*60))%24,(time1%3600)%60,time1%60);
						int time2 = (i+14) * (j+5) * 3 * (j+9);
						System.out.println(time2-time1);
						LocalTime lTime2 = LocalTime.of((time2%(3600*60))%24,(time2%3600)%60,time2%60);
						portal.registerRiderResultsInStage(i,k,lTime1,lTime2);
					}
				}	
			}
			*/

			for (String a : portal.debugResultsToString()){
				System.out.println(a);
			}
			
	
	
		
		
		
		
		
		
		}
		
		catch(Exception e){
			System.out.println("An error occured:");
			System.out.println(e);
		}
	
	
	}





}
