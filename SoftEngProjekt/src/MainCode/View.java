
import java.util.*;

public class View {
    private static ArrayList<Project> leaderProjects = new ArrayList<Project>();
    private static Employee currentEmployeeID;

    // Login method
    public static void login() {
        System.out.println("Please identify yourself by typing your ID");
        while (true) {

            String inputLine = Main.scanner();

            verifyInputForUserID(inputLine);
        }
    }

    private static void verifyInputForUserID(String inputLine) {
        if (inputLine.matches("^[A-Z]{3}$")) {
            currentEmployeeID = Find.employee(inputLine);

            verifyUserID();

        } else {

            System.out.println("Wrong format, please try again");

        }
    }

    private static void verifyUserID() {
        if (currentEmployeeID == null) {

            System.out.println("An employee with that ID does not exist in the system. Please try again");

        }else {
            View.overview(currentEmployeeID);
            login();
        }
    }

    public static void overview(Employee employeeID) {
        System.out.println("Welcome to the ProjectPlanner\r\n");

        isProjectLeaderForAnyProjectInSystem(employeeID);

        System.out.println("You are assigned to the following projects:");

        printAllProjectsAssignedToEmploye(employeeID);

        System.out.println("");

        boolean hasNSA = printAllNSA(employeeID);

        boolean inputStatus =false;
        while (!inputStatus) {
            System.out.println("To go to a specific project: Please type the project ID");
            if(hasNSA) {
                System.out.println("To go to a specific personal activity: Please type the actvity name");
            }
            System.out.println("To create a personal activity: Please type 'CREATE'");
            System.out.println("To delete a personal activity: Please type 'REMOVE'");
            System.out.println("To log out: Please type 'LOGOUT'");
            String inputLine = Main.scanner();
            Project currentProject = validProject(inputLine);
            NSA currentNSA = Find.activityNSA(employeeID, inputLine);
            readUserInput(employeeID, inputLine, currentProject, currentNSA);
        }
    }

    private static boolean printAllNSA(Employee employeeID) {
        if(employeeID.getActivities().size() > 0) {
            System.out.println("You have created the following personal activities:");
            for(int i = 0; i < employeeID.getActivities().size(); i++) {
                System.out.println(employeeID.getActivities().get(i).getName());
            }
            System.out.println("");
            return true;
        }
        return false;
    }

    private static void readUserInput(Employee employeeID, String inputLine,
                                         Project currentProject, NSA currentNSA) {
        if(currentProject != null) {
            Choose.project(currentProject, employeeID);
            overview(employeeID);
        } else if(currentNSA != null) {
            Choose.NSA(currentNSA, employeeID);
            overview(employeeID);
        } else if(inputLine.equalsIgnoreCase("CREATE")) {
            Create.newNSA(employeeID);
            overview(employeeID);
        } else if(inputLine.equalsIgnoreCase("LOGOUT")) {
            System.out.println("Succesfully logged out user with ID: "+employeeID.getName()+"\r\n");
        	login();
        } else if(inputLine.equalsIgnoreCase("REMOVE")) {
            Change.removeNSA(employeeID);
            overview(employeeID);
        } else {
            System.out.println("Wrong format, please try again");
        }
    }

    private static void printAllProjectsAssignedToEmploye(Employee employeeID) {
        ArrayList<Project> employeeProjects = Find.projectsOfEmployee(employeeID);
        if(employeeProjects != null) {
        	for(int i = 0; i < employeeProjects.size(); i++) {
        		System.out.println("Project name: "+employeeProjects.get(i).getName()+" \t Project ID: "+employeeProjects.get(i).getProjectID());
        	}
        } else {
			System.out.println("Employee not found. Please enter employee agian");
		}
    }

    private static void isProjectLeaderForAnyProjectInSystem(Employee employeeID) {
        leaderProjects = Find.projectLeader(Main.getProjects(), employeeID);
        if (leaderProjects.size() > 0) {

            Main.projectLeader = true;
            System.out.println("You are projectleader of the following projects:");

            // Metoder som printer alle projekter, som man er projektleder af
            for (int i = 0; i < leaderProjects.size(); i++) {
                System.out.println("Project name: "+leaderProjects.get(i).getName() + "\tProjectID: "
            + leaderProjects.get(i).getProjectID());
            }
            System.out.println("");
        }
    }

    public static Project validProject(String inputLine) {
        if (inputLine.matches("^[0-9]{6}$")) {
            String yearIDString = inputLine.substring(0, 4);
            String numIDString = inputLine.substring(4);
            Project currentProject = Find.project(yearIDString, numIDString);
            if (currentProject == null) {
                System.out.println("A project with that ID does not exist. Please try again");
            } else {
                return currentProject;
            }
        }
        return null;
    }

    public static void activity(Project project, boolean projectLeader){
        ArrayList<PSA> activities = project.getActivities();
        System.out.println("");
        if(activities.size() < 1) {
            System.out.println("Project has no activities");
        } else {
            System.out.println("List of activities for project: "+project.getName());
            for(int i = 0; i < activities.size(); i++) {
                System.out.println(activities.get(i).getName());
            }
        }
        System.out.println("");
    }

    public static void report(Project project) {
        int ExpectedTime = 0;
        int LoggedTime = 0;
        for(int i = 0; i < project.getActivities().size(); i++) {
            PSA selected = project.getActivities().get(i);
            ExpectedTime += selected.getTime();
            LoggedTime += selected.spent();
        }
        int TimeLeft = ExpectedTime - LoggedTime;
        // Prints project times
        System.out.printf("%-25s %-25s", "Start date: "+project.getStartDate(),"End date: "+project.getEndDate());
        System.out.println("");
        System.out.printf("%-20s %-20s %-20s","Expected Hours: " + ExpectedTime, "Logged Hours: " + LoggedTime, "Hours Left: " + TimeLeft);
        System.out.println("\r\n");
        // Prints employees in project
        System.out.printf("%42s", "Employees in project:\r\n");
        for(int i = 0; i < project.getEmployees().size(); i++) {
            Employee selected = project.getEmployees().get(i);
            System.out.printf("%-26s", selected.getName());
            if((i+1)%3 == 0) {
                System.out.println("");
            }
        }
        // Prints assistants in project
        if(project.getAssistants().size() > 0) {
            System.out.println("\r\n");
            System.out.printf("%42s", "Assistants in project:\r\n");
            for(int i = 0; i < project.getAssistants().size(); i++) {
                Employee selected = project.getAssistants().get(i);
                System.out.printf("%-26s", selected.getName());
                if((i+1)%3 == 0) {
                    System.out.println("");
                }
            }
        }
        System.out.println("");
    }
}