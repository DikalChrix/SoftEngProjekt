import java.util.*;

public class xtraMetoderMain {

	// Fields
	static boolean projectLeader = false;
	static Scanner input = new Scanner(System.in);
	static ArrayList<Project> projectList = new ArrayList<Project>();
	static ArrayList<Employee> employeeList = new ArrayList<Employee>();
	static Employee currentEmployeeID;

	public static void projectChosen(Project currentProject) {

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
			System.out.println("To create an activity: Please type 'NEWACT'");

		}
		boolean inputStatus = false;
		while (!inputStatus) {
			String inputLine = input.nextLine();
			if (inputLine.equalsIgnoreCase("BACK")) {
				overview(currentEmployeeID);
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

			} else if (projectLeader && inputLine
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
			} else if (projectLeader == true && inputLine.matches("NEWACT")) {

				newActivity(currentProject);

			} else {
				System.out.println("Wrong format, please try again");
			}
		}

	}

	public static void overview(Employee employeeID) {
		System.out.println("Welcome to the ProjectPlanner");

		if (projectLeader == true) {

			System.out.println("You are projectleader of the following projects:");

			// Metoder som printer alle projekter, som man er projektleder af
		}

		System.out.println("You are assigned to the following projects:");

		// Metoder som printer alle projekter som man normal employee af

		System.out.println("To go to a specific project: Please type 'YEARID NUMUD'");
		boolean inputStatus =false;
		while (!inputStatus) {
			String inputLine = input.nextLine();

			if (inputLine.matches("^[0-9]{6}$")) {
				String yearIDString = inputLine.substring(0, 5);
				String numIDString = inputLine.substring(6);

				// Parsing to integers:
				int yearID = Integer.parseInt(yearIDString);
				int numID = Integer.parseInt(numIDString);

				Project currentProject = findProject(yearIDString, numIDString);
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

	public static void activityChosen(PSA currentActivity) {

	}

	// Metode, som søger gennem projektlisten og returnerer rigtigt projekt ud fra
	// ID
	public static Project findProject(String yearID, String numID) {
		for (int i = 0; i <= projectList.size(); i++) {
			if (projectList.get(i).getProjectID().equals(yearID + " " + numID)) {
				return projectList.get(i);
			}
		}
		return null;
	}

	// Setup, fills program with content
	public static void setup()
		{
			//Laver 10 employees-logins


			//Opretter 5 projekter

			//Projekt1:
			DateType startDate = new DateType("05/05/19");
			DateType endDate = new DateType("12/05/19");

			//Laver liste med aktiviteter

			//Laver liste med employees
			Project project1 = new Project("Project1", 2019, 01, "Adam", startDate, endDate, /*activitetsliste*/ , /*employeeliste*/);



		}

	// Login method
	public static void login() {

		System.out.println("Plese identify yourself by typing your ID");
		boolean inputStatus = false;
		while (inputStatus == false) {

			String inputLine = input.nextLine();

			if (inputLine.matches("^[0-9]{6}$")) {

				currentEmployeeID = findEmployee(inputLine);

				if (currentEmployeeID == null) {
					System.out.println("An employee with that ID does not exist in the system. Please try again");
					continue;
				} else {
					overview(currentEmployeeID);
					inputStatus = true;
				}

			} else {
				System.out.println("Wrong format, please try again");
			}

		}

	}

	public static void print(@SuppressWarnings("rawtypes") ArrayList a) {
		for (int i = 0; i < a.size(); i++) {
			System.out.println(a.get(i));
		}
	}

	public static void findProjectOfEmployee(Employee ID) {
		// Find Employee and call Employee.java
		if (employeeList.contains(ID)) {
			for (int i = 0; i < projects.size(); i++) {
				if (projectList.get(i).Econtains(ID)) {
					System.out.println(projectList.get(i).ProjectName + ": " + projectList.get(i).getProjectID());
				}
			}
		} else {
			System.out.println("Employee not found. Please enter employee agian(Remember All CAPS");
		}
	}

	public static Employee findEmployee(String EmployeeID) {
		for (int i = 0; i <= employeeList.size(); i++) {
			if (employeeList.get(i).Name.equals(EmployeeID)) {
				return employeeList.get(i);
			}
		}
		return null;
	}


	public static void newActivity(project currentProject) {
		// Local fields
		String nameActivity;
		int hoursActivity;
		DateType activityStartDate;
		DateType activityEndDate;
		ArrayList<Employee> employeeListActivity = new ArrayList<Employee>();

		System.out.print("What is the name of the activity?");
		boolean inputStatus = false;
		while (inputStatus == false) {
			String inputLine = input.nextLine();
			if (!findActivity(inputLine) == null) {
				System.out.println("An activity with this name already exists. Please try again");
				continue;
			} else {
				nameActivity = inputLine;
				inputStatus = true;
			}
		}

		System.out.print("On which date should the activity start?");
		inputStatus = false;
		while (inputStatus == false) {
			String inputLine = input.nextLine();

			if (inputLine.matches(
					"^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
				activityStartDate = new DateType(inputLine);
			} else {
				System.out.println("Wrong date format, please try again");
				continue;
			}

			inputStatus = true;
		}

		System.out.print("On which date should the activity end?");
		inputStatus = false;
		while (inputStatus == false) {
			String inputLine = input.nextLine();

			if (inputLine.matches(
					"^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
				activityEndDate = new DateType(inputLine);
			} else {
				System.out.println("Wrong date format, please try again");
				continue;
			}

			inputStatus = true;
		}

		System.out.print("How many hours of work should the activity contain?");
		inputStatus = false;
		while (inputStatus == false) {
			String inputLine = input.nextLine();
			if (inputLine.matches("^[0-9]{1,}+$")) {
				hoursActivity = Integer.parseInt(inputLine);
				inputStatus = true;
			} else {
				System.out.println("Wrong format, please try again");
				continue;
			}
		}

		System.out.print("How many employees should be added to the project?");
		inputStatus = false;
		int employeesNumberActivity;
		while (inputStatus == false) {
			String inputLine = input.nextLine();
			if (inputLine.matches("^[0-9]{2}+$")) {
				employeesNumberActivity = Integer.parseInt(inputLine);
				inputStatus = true;
			} else {
				System.out.println("Wrong format, please try again");
			}
		}

		System.out.print("Please type the ID of the employees on each line:");
		for (int i = 0; i < employeesNumberActivity; i++) {
			inputStatus = false;
			while (inputStatus = false) {
				String inputLine = input.nextLine();
				if (inputLine.matches("^[A-Z]{3}+$")) {

					// Cheks if this employee actually exists:
					if (findEmployee(inputLine) == null) {
						System.out.println("This employee does not exist. Please try again");
						continue;
					} else {
						employeeListActivity.add(findEmployee(inputLine));
						System.out.println("The employee with ID " + inputLine + " was succesfully added");
					}

					inputStatus = true;
				} else {
					System.out.println("Wrong format, please try again");
				}

				// Creates activity with the given information:
				PSA activityPlaceholder = new PSA(nameActivity, hoursActivity, activityStartDate, activityEndDate,
						employeeListActivity);
				currentProject.addActivity(activityPlaceholder);

				System.out.print("The activity was succesfully added to the project");

			}
		}
	}
}
