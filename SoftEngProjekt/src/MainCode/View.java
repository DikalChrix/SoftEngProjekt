import java.util.*;

public class View {
    static ArrayList<Project> employeeProjects = new ArrayList<Project>();
    static ArrayList<Project> leaderProjects;

    // Login method
    public static void login() {
        System.out.println("Plese identify yourself by typing your ID");
        boolean inputStatus = false;
        while (inputStatus == false) {

            String inputLine = Main.input.nextLine();

            if (inputLine.matches("^[A-Z]{3}$")) {

                Main.currentEmployeeID = View.findEmployee(inputLine);

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
        System.out.println("Welcome to the ProjectPlanner");
        leaderProjects = findProjectLeader(Main.projectList, employeeID);
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
        findProjectOfEmployee(employeeID);
        System.out.println("");

        System.out.println("To go to a specific project: Please type 'YEARID NUMID'");
        System.out.println("To create an activity: Please type 'CREATE'");
        boolean inputStatus =false;
        while (!inputStatus) {
            String inputLine = Main.input.nextLine();

            if (inputLine.matches("^[0-9]{6}$")) {
                String yearIDString = inputLine.substring(0, 4);
                String numIDString = inputLine.substring(4);

                System.out.println(yearIDString+numIDString);
                Project currentProject = View.findProject(yearIDString, numIDString);
                if (currentProject == null) {
                    System.out.println("A project with that ID does not exist. Please try again");
                } else {
                    if(currentProject.isProjectLeader(employeeID)){
                        Main.projectLeader = true;
                    } else {
                        Main.projectLeader = false;
                    }
                    Main.projectChosen(currentProject);
                    inputStatus = true;
                }
            } else if(inputLine.equals("CREATE")) {
                Main.newNSA(employeeID);
            } else {
                System.out.println("Wrong format, please try again");
            }
        }
    }

    public static void findProjectOfEmployee(Employee ID) {
        // Find Employee and call Employee.java
        if (Main.employeeList.contains(ID)) {
            for (int i = 0; i < Main.projectList.size(); i++) {
                if (Main.projectList.get(i).Econtains(ID)) {
                    System.out.println("Project name: " + Main.projectList.get(i).ProjectName + "\tProjectID: " + Main.projectList.get(i).getProjectID());
                }
            }
        } else {
            System.out.println("Employee not found. Please enter employee agian(Remember All CAPS");
        }
    }

    // Metode, som søger gennem projektlisten og returnerer rigtigt projekt ud fra
    // ID
    public static Project findProject(String yearID, String numID) {
        if(Main.projectList.size() > 0) {
            for (int i = 0; i < Main.projectList.size(); i++) {
                if (Main.projectList.get(i).getProjectID().equals(yearID + numID)) {
                    return Main.projectList.get(i);
                }
            }
        }
        return null;
    }

    public static void print(@SuppressWarnings("rawtypes") ArrayList a) {
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }

    public static ArrayList<Project> findProjectLeader(ArrayList<Project> projects, Employee EmployeeID) {
        ArrayList<Project> leaderProjects = new ArrayList<Project>();
        for(int i = 0; i < projects.size(); i++) {
            if(projects.get(i).isProjectLeader(EmployeeID)) {
                leaderProjects.add(projects.get(i));
            }
        }
        return leaderProjects;
    }

    public static Employee findEmployee(String EmployeeID) {
        for (int i = 0; i < Main.employeeList.size(); i++) {
            if (Main.employeeList.get(i).Name.equals(EmployeeID)) {
                return Main.employeeList.get(i);
            }
        }
        return null;
    }

    public static void chooseActivity(Project project, boolean projectLeader){
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
