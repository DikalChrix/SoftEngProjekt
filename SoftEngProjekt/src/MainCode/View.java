import java.util.*;

public class View {
    static ArrayList<Project> employeeProjects = new ArrayList<Project>();
    static ArrayList<Project> leaderProjects = new ArrayList<Project>();

    // Login method
    public static void login() {
        System.out.println("Please identify yourself by typing your ID");
        while (true) {

            String inputLine = Main.input.nextLine();

            verifyInputForUserID(inputLine);
        }
    }

    private static void verifyInputForUserID(String inputLine) {
        if (inputLine.matches("^[A-Z]{3}$")) {
            assert inputLine.matches("^[A-Z]{3}$"); //pre condition
            Main.currentEmployeeID = Find.employee(inputLine);

            verifyUserID();

        } else {

            System.out.println("Wrong format, please try again");

        }
    }

    private static void verifyUserID() {
        if (Main.currentEmployeeID == null) {

            System.out.println("An employee with that ID does not exist in the system. Please try again");

        }else {
            View.overview(Main.currentEmployeeID);
            login();
        }
    }

    public static void overview(Employee employeeID) {
        System.out.println("Welcome to the ProjectPlanner\r\n");

        isProjectLeaderForAnyProjectInSystem(employeeID);

        System.out.println("You are assigned to the following projects:");

        printAllProjectsAssignedToEmploye(employeeID);

        System.out.println("");

        boolean inputStatus =false;
        while (!inputStatus) {
            System.out.println("To go to a specific project: Please type the project ID");
            System.out.println("To create an activity: Please type 'CREATE'");
            System.out.println("To log out: Please type 'LOGOUT'");
            String inputLine = Main.input.nextLine();
            Project currentProject = validProject(inputLine);
            inputStatus = readUserInput(employeeID, inputStatus, inputLine, currentProject);
        }
    }

    private static boolean readUserInput(Employee employeeID, boolean inputStatus, String inputLine,
                                         Project currentProject) {
        if(currentProject != null) {
            Choose.project(currentProject, employeeID);
            overview(employeeID);
        } else if(inputLine.equalsIgnoreCase("CREATE")) {
            Create.newNSA(employeeID);
            overview(employeeID);
        } else if(inputLine.equalsIgnoreCase("LOGOUT")) {
            inputStatus = true;
        } else {
            System.out.println("Wrong format, please try again");
        }
        return inputStatus;
    }

    private static void printAllProjectsAssignedToEmploye(Employee employeeID) {
        Find.projectOfEmployee(employeeID);
    }

    private static void isProjectLeaderForAnyProjectInSystem(Employee employeeID) {
        leaderProjects = Find.projectLeader(Main.projectList, employeeID);
        if (leaderProjects.size() > 0) {

            Main.projectLeader = true;
            System.out.println("You are projectleader of the following projects:");

            // Metoder som printer alle projekter, som man er projektleder af
            for (int i = 0; i < leaderProjects.size(); i++) {
                System.out.println("Project name: "+leaderProjects.get(i).ProjectName + "\tProjectID: " + leaderProjects.get(i).getProjectID());
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
        System.out.println("List of activities for project: "+project.name());
        ArrayList<PSA> activities = project.ActivityList;
        if(activities.size() < 1) {
            System.out.println("Project has no activities");
        } else {
            for(int i = 0; i < activities.size(); i++) {
                System.out.println(activities.get(i).Name);
            }
        }
    }
}