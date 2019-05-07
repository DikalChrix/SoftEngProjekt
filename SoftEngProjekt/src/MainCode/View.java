import java.util.*;

public class View {
    static ArrayList<Project> employeeProjects = new ArrayList<Project>();
    static ArrayList<Project> leaderProjects = new ArrayList<Project>();

    // Login method
    public static void login() {
        System.out.println("Please identify yourself by typing your ID");
        boolean inputStatus = false;
        while (inputStatus == false) {

            String inputLine = Main.input.nextLine();

            if (inputLine.matches("^[A-Z]{3}$")) {

                Main.currentEmployeeID = Find.employee(inputLine);

                if (Main.currentEmployeeID == null) {
                    System.out.println("An employee with that ID does not exist in the system. Please try again");
                    continue;
                } else {
                    View.overview(Main.currentEmployeeID);
                    inputStatus = true;
                }

            } else {
                System.out.println("Wrong format, please try again");
            }
        }
    }

    public static void overview(Employee employeeID) {
        System.out.println("Welcome to the ProjectPlanner\r\n");
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

        System.out.println("You are assigned to the following projects:");

        // Metoder som printer alle projekter som man normal employee af
        Find.projectOfEmployee(employeeID);
        System.out.println("");

        System.out.println("To go to a specific project: Please type the project ID");
        System.out.println("To create an activity: Please type 'CREATE'");
        System.out.println("To log out: Please type 'LOGOUT'");
        boolean inputStatus =false;
        while (!inputStatus) {
            String inputLine = Main.input.nextLine();

            if (inputLine.matches("^[0-9]{6}$")) {
                String yearIDString = inputLine.substring(0, 4);
                String numIDString = inputLine.substring(4);

                System.out.println(yearIDString+numIDString);
                Project currentProject = Find.project(yearIDString, numIDString);
                if (currentProject == null) {
                    System.out.println("A project with that ID does not exist. Please try again");
                } else {
                    if(currentProject.isProjectLeader(employeeID)){
                        Main.projectLeader = true;
                    } else {
                        Main.projectLeader = false;
                    }
                    Choose.project(currentProject, employeeID);
                    inputStatus = true;
                }
            } else if(inputLine.equals("CREATE")) {
                Create.newNSA(employeeID);
            } else if(inputLine.equals("LOGOUT")) {
                login();
            } else {
                System.out.println("Wrong format, please try again");
            }
        }
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
