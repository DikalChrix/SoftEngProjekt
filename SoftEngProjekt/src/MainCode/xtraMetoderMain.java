import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class xtraMetoderMain {

	// Fields
	static boolean inputStatus = false;
	static boolean projectLeader = false;
	static Scanner input = new Scanner(System.in);
	static ArrayList<project> projectList = new ArrayList<project>();

	public static void projectChosen(project currentProject) {

		System.out.println("Overview for project: " + currentProject.getProjectID());
		System.out.println(
				"This project has the following startdate: " + currentProject.getProjectStartDate().toString());
		System.out.println(
				"This project has the following expected end date: " + currentProject.getProjectEndDate().toString());
		System.out.println("This project consists of the following activities:");

		// Metode, som printer alle aktiviteterne i dette projekt
		print(currentProject.ActivityList);

		if (projectLeader == true) {

			System.out.println("The following employees are assigned to this project:");

			// Metode, som printer alle employees, der er i dette projekt

		}

		System.out.println("You now have the following choices:");
		System.out.println("To go back to 'Project Overview': Please type 'BACK'");
		System.out.println("To go to specific activity: Please type 'X'");

		if (projectLeader == true) {

			System.out.println("To change the start date: Please type STARTDATE:dd/mm/yy");
			System.out.println("To change the expected end date: Please type ENDDATE:dd/mm/yy");

			// De nedenstående kommandoer mangler at blive implementeret
			System.out.println("To assign an employee to the project: Please type ASSIGN 'employeeID'");
			System.out.println("To unassign an employee to the project: Please type UNASSIGN 'employeeID'");
			System.out.println("To create an activity: Please type 'NEWACT'");

		}

		while (inputStatus == false) {
			String inputLine = input.nextLine();
			if (inputLine.equalsIgnoreCase("BACK")) {
				overview();
				inputStatus = true;
			} else if (inputLine.matches("^[a-z]{1,}+$")) {
				PSA currentActivity = currentProject.findActivity(inputLine);
				if (currentActivity == null) {
					System.out.print("The activity does not exist, please try again");
					continue;
				} else {
					activityChosen(currentActivity);
					inputStatus = true;
				}

			} else if (projectLeader == true && inputLine
					.matches("STARTDATE:^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+$")) {

				// Mulig test, som sørger for at startdate er før enddate

				String dateString = inputLine.substring(10);
				DateType newStartDate = new DateType(dateString);
				currentProject.setProjectStartDate(newStartDate);

				System.out.println("The start date was succesfully changed to: " + newStartDate.toString());

			} else if (projectLeader == true && inputLine
					.matches("ENDDATE:^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+$")) {

				// Mulig test, som sørger for at enddate er efter startdate

				String dateString = inputLine.substring(8);
				DateType newEndDate = new DateType(dateString);
				currentProject.setProjectEndDate(newEndDate);

				System.out.println("The expected end date was succesfully changed to: " + newEndDate.toString());

			} else {
				System.out.println("Wrong format, please try again");
			}
		}

	}

	public static void overview() {
		System.out.println("Welcome to the ProjectPlanner");

		if (projectLeader == true) {

			System.out.println("You are projectleader of the following projects:");

			// Metoder som printer alle projekter, som man er projektleder af
		}

		System.out.println("You are assigned to the following projects:");

		// Metoder som printer alle projekter som man normal employee af

		System.out.println("To go to a specific project: Please type 'YEARID NUMUD'");

		while (inputStatus == false) {
			String inputLine = input.nextLine();

			if (inputLine.matches("^[0-9]{6}$")) {
				String yearIDString = inputLine.substring(0, 5);
				String numIDString = inputLine.substring(6);

				// Parsing to integers:
				int yearID = Integer.parseInt(yearIDString);
				int numID = Integer.parseInt(numIDString);

				project currentProject = findProject(yearIDString, numIDString);
				if (currentProject == null) {
					System.out.println("A project with that ID does not exist. Please try again");
					continue;

				}
				projectChosen(currentProject);
				inputStatus = true;
			} else {
				System.out.println("Wrong format, please try again");
			}
		}
	}

	public static void activityChosen(activity currentActivity) {

	}

	// Metode, som søger gennem projektlisten og returnerer rigtigt projekt ud fra
	// ID
	public static project findProject(String yearID, String numID) {
		for (int i = 0; i <= projectList.size(); i++) {
			if (projectList.get(i).getProjectID().equals(yearID + " " + numID)) {
				return projectList.get(i);
			}
		}
		return null;
	}

	// Setup, fylder programmet op med data, således at den ikke er tom
	public static void setup()
	{
		//Laver 10 employees-logins
		
		
		//Opretter 5 projekter
		
		//Projekt1:
		DateType startDate = new DateType("05/05/19");
		DateType endDate = new DateType("12/05/19");
		
		//Laver liste med aktiviteter
		
		//Laver liste med employees
		
		project project1 = new project(Project1, 2019, 01, Adam, startDate, endDate,  );
		
		
		
	}

	// Login metode
	public static void login(){
		
		System.out.println("Plese identify yourself by typing your ID");
		
		while (inputStatus == false) {
			
			String inputLine = input.nextLine();
		
			if(inputLine.matches("^[0-9]{6}$"))
			{
				
				currentEmployeeID =findEmployee(inputLine);
				
				if(currentEmployeeID==)
				
				
				inputStatus=true;
			}
			else {
				System.out.println("Wrong format, please try again");
			}
			
		}
		
	}

	public static void print(@SuppressWarnings("rawtypes") ArrayList a) {
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
	}
}
