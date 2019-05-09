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

            Main.currentEmployeeID = Find.employee(inputLine);

            verifyUserID();

        } else {
            System.out.println("Wrong format, please try again");
        }
    }

    private static void verifyUserID() {
        if (Main.currentEmployeeID == null) {
            System.out.println("An employee with that ID does not exist in the system. Please try again");
        } else {
            View.overview(Main.currentEmployeeID);
            login();
        }
    }

    public static void overview(Employee employeeID) {
        System.out.println("Welcome to the ProjectPlanner\r\n");

        isProjectLeaderForAnyProjectInSystem(employeeID);

        System.out.println("You are assigned to the following projects:");

        printAllProjectsAssignedToEmploye(employeeID);

        boolean inputStatus =false;
        while (!inputStatus) {
            String inputLine = Main.input.nextLine();
            if(inputLine.matches("[1-3]+$")) {
                int selected = Integer.parseInt(inputLine);
                switch(selected) {
                    case 1:
                        System.out.println("1 Select project 201901");
                        System.out.println("2 Select project 201902");
                        inputLine = Main.input.nextLine();
                        if(inputLine.matches("[1-2]+$")) {
                            selected = Integer.parseInt(inputLine);
                            switch(selected) {
                                case 1:
                                    Project currentProject = Find.project("2019", "01");
                                    Choose.project(currentProject, employeeID);
                                    break;
                                case 2:
                                    currentProject = Find.project("2019", "02");
                                    Choose.project(currentProject, employeeID);
                                    break;
                            }
                        }
                        break;
                    case 2:
                        Create.newNSA(employeeID);
                        break;
                    case 3:
                        inputStatus = true;
                }
            }
        }
    }

    private static void printAllProjectsAssignedToEmploye(Employee employeeID) {
        Find.projectOfEmployee(employeeID);
        System.out.println("");
        System.out.println("You now have the following options:");
        System.out.println("1 Select project");
        System.out.println("2 Create non specific activity");
        System.out.println("3 Log out");
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
