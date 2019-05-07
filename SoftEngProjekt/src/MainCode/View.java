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
        System.out.println("\r\nWelcome to the ProjectPlanner");
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
                Project currentProject = View.findProject(yearIDString, numIDString);
                if (currentProject == null) {
                    System.out.println("A project with that ID does not exist. Please try again");
                } else {
                    if(currentProject.isProjectLeader(employeeID)){
                        Main.projectLeader = true;
                    } else {
                        Main.projectLeader = false;
                    }
                    View.projectChosen(currentProject);
                    inputStatus = true;
                }
            } else if(inputLine.equals("CREATE")) {
                Main.newNSA(employeeID);
            } else if(inputLine.equals("LOGOUT")) {
                login();
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

    public static void projectChosen(Project currentProject) {

        System.out.println("Overview for project: " + currentProject.getProjectID());
        System.out.println(
                "This project has the following startdate: " + currentProject.getProjectStartDate().toString());
        System.out.println(
                "This project has the following expected end date: " + currentProject.getProjectEndDate().toString());
        System.out.println("This project consists of the following activities:");

        // Metode, som printer alle aktiviteterne i dette projekt
        View.chooseActivity(currentProject, Main.projectLeader);

        if (Main.projectLeader) {

            System.out.println("The following employees are assigned to this project:");
            // Metode, som printer alle employees, der er i dette projekt

        }

        System.out.println("You now have the following choices:");
        System.out.println("To go back to 'Project Overview': Please type 'BACK'");
        System.out.println("To choose activity: Please type the name of the activity");

        if (Main.projectLeader) {
            System.out.println("Projectleader permissions:");
            System.out.println("To change the start date: Please type STARTDATE:dd/mm/yy");
            System.out.println("To change the expected end date: Please type ENDDATE:dd/mm/yy");
            System.out.println("To generate a report: Please type 'REPORT'");
            System.out.println("To create an activity: Please type 'NEWACT'");

        }
        boolean inputStatus = false;
        while (!inputStatus) {
            String inputLine = Main.input.nextLine();
            if (inputLine.equalsIgnoreCase("BACK")) {
                View.overview(Main.currentEmployeeID);
                inputStatus = true;
            } else if (Main.projectLeader && inputLine
                    .matches("STARTDATE:^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+$")) {

                // Mulig test, som sørger for at startdate er før enddate

                String dateString = inputLine.substring(10);
                DateType newStartDate = new DateType(dateString);
                currentProject.setProjectStartDate(newStartDate);

                System.out.println("The start date was succesfully changed to: " + newStartDate.toString());

            } else if (Main.projectLeader && inputLine
                    .matches("ENDDATE:^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+$")) {

                // Mulig test, som sørger for at enddate er efter startdate

                String dateString = inputLine.substring(8);
                DateType newEndDate = new DateType(dateString);
                currentProject.setProjectEndDate(newEndDate);

                System.out.println("The expected end date was succesfully changed to: " + newEndDate.toString());
            } else if (Main.projectLeader && inputLine.matches("NEWACT")) {

                Main.newActivity(currentProject);

            } else if (Main.projectLeader == true && inputLine.equals("REPORT")) {

                currentProject.getReport();

            } else if (inputLine.matches("^[a-z,A-Z]+$")) {
                PSA currentActivity = currentProject.findActivity(inputLine);
                if (currentActivity != null) {
                    System.out.print("The activity does not exist, please try again");
                    continue;
                } else {
                    // Choose activity
                    Main.activityChosen(currentActivity, currentProject, Main.projectLeader);
                    inputStatus = true;
                }

            } else {
                System.out.println("Wrong format, please try again");
            }
        }
    }
}
