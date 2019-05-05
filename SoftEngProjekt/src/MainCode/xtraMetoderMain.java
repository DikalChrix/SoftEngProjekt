import MainCode.PSA;

import java.util.Scanner;

public class xtraMetoderMain {

	// Fields


	static boolean inputStatus = false;

	static Scanner input = new Scanner(System.in);

	public static void projectChosen(Project currentProject) {

		System.out.println("Activity overview for project: " + currentProject.getProjectID());
		System.out.println("You now have the following choices:");
		System.out.println("To go back to 'Project Overview': Please type 'BACK'");
		System.out.println("To go to specific activity: Please type 'X'");

		while (inputStatus == false) {
			String inputLine = input.nextLine();
			if (inputLine.equals("BACK")) {
				overview();
				inputStatus=true;
			} else if (inputLine.matches("^[0-9]+$")) {
				int inputNumber = Integer.parseInt(inputLine);
				PSA currentActivity = findActivity(inputNumber);
				activityChosen(currentActivity);
				inputStatus=true;
			} else {
				System.out.println("Wrong format, please try again");
			}
		}
	}

	public static void overview() {
			System.out.println("Welcome to the ProjectPlanner");
			System.out.println("You are assigned to the following projects:");
			
			//Metoder som printer alle projekterne
			
			System.out.println("To go to a specific project: Please type 'YEARID NUMID'");
			
			while (inputStatus == false) {
				String inputLine = input.nextLine();
			
				if (inputLine.matches("^[0-9]{4}+\\s+[0-9]{2}$")) {
					String[] stringParts=inputLine.split(" ");
					String YearIDString = stringParts[0];
					String numIDString = stringParts[1];
					
					//Parsing to integers:
					int yearID = Integer.parseInt(YearIDString);
					int numID = Integer.parseInt(numIDString);
					
					project currentProject = findProject(yearID, numID);
					projectChosen(currentProject);
					inputStatus=true;
				} else {
					System.out.println("Wrong format, please try again");
				}
		}	
	}

	public static void activityChosen(activity currentActivity) {

	}

}
