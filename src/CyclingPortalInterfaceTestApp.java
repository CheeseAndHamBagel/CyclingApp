import cycling.CyclingPortal;
import cycling.CyclingPortalInterface;


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
			System.out.println(portal.createTeam("TeamOne", "Initial testing team") == 1);
			System.out.println(portal.createRider(0,"bob01", 2000));
			System.out.println(portal.createRider(0,"bob02", 2000));
			System.out.println(portal.createRider(0,"bob03", 2000));
			System.out.println(portal.createRider(0,"bob04", 2000));
			
			/*
			for (int a : portal.getTeamRiders(0)){
				System.out.println(a);
			}
			for (String a : portal.debugTeamsToString()){
				System.out.println(a);
			}
			for (String a : portal.debugRidersToString()){
				System.out.println(a);
			}



			for (String a : portal.debugRacesToString()){
				System.out.println(a);
			}
			
			
			for (String a : portal.debugResultsToString()){
				System.out.println(a);
			}
			*/
	
	
		
		
		
		
		
		
		}
		


		
		
		
		catch(Exception e){
			System.out.println("An error occured:");
			System.out.println(e.getMessage());
		}
	
	
	}





}
