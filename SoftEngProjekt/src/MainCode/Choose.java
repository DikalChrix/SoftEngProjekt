package planningProject;

import java.util.ArrayList;

public class Choose {

    private static ArrayList<PSA> employeeActivities = new ArrayList<PSA>();
	
    // If activity is chosen
    public static void activity(PSA currentActivity, Project currentProject, boolean projectLeader, Employee employeeID) {
        // Local variable declaration
        String inputLine;
        boolean inputStatus = false;

        System.out.println("Menu for activity "+currentActivity.getName());

        while(!inputStatus) {

            timeLeftofActivity(currentActivity);

            if(!projectLeader) {
                System.out.println("To add hours to this activity type: 'ADD'");
            }

            ifActivityProjectLeader(currentActivity, projectLeader);

            System.out.println("To ask for assistance type: 'HELP'");
            System.out.println("To go back to activity select type: 'BACK'");

            inputLine = Main.scanner();

            inputStatus = readPSAInput(currentActivity, currentProject, projectLeader, employeeID, inputLine,
                    inputStatus);

            projectLeaderCommands(currentActivity, currentProject, projectLeader, employeeID, inputLine);
        }
    }

    private static boolean readPSAInput(PSA currentActivity, Project currentProject, boolean projectLeader,
                                        Employee employeeID, String inputLine, boolean inputStatus) {
        if(inputLine.equalsIgnoreCase("ADD") && !projectLeader) {
            Change.add(currentActivity);
            activity(currentActivity, currentProject, projectLeader, employeeID);
        } else if(inputLine.equalsIgnoreCase("HELP")) {
            Change.getHelp(currentActivity, projectLeader, currentProject);
            activity(currentActivity, currentProject, projectLeader, employeeID);
        } else if(inputLine.equalsIgnoreCase("BACK")) {
            inputStatus = true;
        }
        return inputStatus;
    }

    private static void projectLeaderCommands(PSA currentActivity, Project currentProject, boolean projectLeader,
                                              Employee employeeID, String inputLine) {
        if(projectLeader) {

            // Change expected hours
            if(inputLine.equalsIgnoreCase("CHANGE")) {
                Change.changeHours(currentActivity);
                activity(currentActivity, currentProject, projectLeader, employeeID);

                // Assign employee
            } else if(inputLine.equalsIgnoreCase("ASSIGN")) {
                Change.assign(currentActivity);
                activity(currentActivity, currentProject, projectLeader, employeeID);

                // Unassign employee
            } else if(inputLine.equalsIgnoreCase("UNASSIGN")) {
                Change.unAssign(currentActivity);
                activity(currentActivity, currentProject, projectLeader, employeeID);

            } else if(inputLine.equalsIgnoreCase("ENDDATE")) {
                Change.newPSAEndDate(currentActivity, currentActivity.getStartDate());

            } else if(inputLine.equalsIgnoreCase("NEWNAME")) {
                Change.newPSAName(currentProject, currentActivity);
            }
        }
    }

    private static void ifActivityProjectLeader(PSA currentActivity, boolean projectLeader) {
        if(projectLeader) {
            System.out.println("List of employees in activity: " + currentActivity.getName());
            if(currentActivity.getEmployees().size() > 0) {
                for(int i = 0; i < currentActivity.getEmployees().size(); i++) {
                    System.out.println(currentActivity.getEmployees().get(i).getName());
                }
                System.out.println("");
            } else {
                System.out.println("This activity has no employees assigned");
            }
            System.out.println("To change activity's enddate: Please type 'ENDDATE'");
            System.out.println("To change expected number of hours: Please type 'CHANGE'");
            System.out.println("To assign an employee to the activity: Please type 'ASSIGN'");
            System.out.println("To unassign an employee from the activity: Please type 'UNASSIGN'");
            System.out.println("To unassign assistant from the activity: Please type 'REMOVE'");
        }
    }

    private static void timeLeftofActivity(PSA currentActivity) {
        int timeleft = currentActivity.getTime()-currentActivity.spent();
        System.out.println("Expected hours of work: "+currentActivity.getTime()+"\t Hours spent: "+currentActivity.spent()+"\t Hours left: "+ timeleft);
    }

    // If project is chosen
    public static void project(Project currentProject, Employee employeeID) {
        System.out.println("Menu for project "+currentProject.getName());

        isCurrentProjectLeader(currentProject, employeeID);

        introMessage(currentProject, employeeID);

        System.out.println("To go back to 'Project Overview': Please type 'BACK'");
        System.out.println("To choose activity: Please type the name of the activity");

        if (Main.projectLeader) {
            System.out.println("\r\nProjectleader permissions:");
            System.out.println("To change the expected end date: Please type ENDDATE");
            System.out.println("To change the project name: Please type 'NEWNAME'");
            System.out.println("To create an activity: Please type 'NEWACT'");
            System.out.println("To remove an activity: Please type: 'REMOVE'");
            System.out.println("To generate a report: Please type 'REPORT'");
        }

        readProjectInput(currentProject, employeeID);
    }

    private static void readProjectInput(Project currentProject, Employee employeeID) {
        while(true) {
            String inputLine = Main.scanner();
            if (inputLine.equalsIgnoreCase("BACK")) {

                View.overview(employeeID);

            } else if (Main.projectLeader && inputLine.equalsIgnoreCase("ENDDATE")) {

                Change.enddate(currentProject, employeeID, currentProject.getStartDate());
                project(currentProject, employeeID);

            } else if (Main.projectLeader && inputLine.matches("NEWACT")) {

                Create.newActivity(currentProject, employeeID);
                project(currentProject, employeeID);

            } else if(Main.projectLeader && inputLine.equalsIgnoreCase("REMOVE")) {

                Change.removePSA(currentProject);
                project(currentProject, employeeID);

            } else if (Main.projectLeader && inputLine.equalsIgnoreCase("REPORT")) {

                View.report(currentProject);
                System.out.println("");
                project(currentProject, employeeID);

            } else if (Main.projectLeader && inputLine.equalsIgnoreCase("NEWNAME")) {

                Change.newProjectName(currentProject);
                project(currentProject, employeeID);

            } else if (inputLine.matches("^[a-z,A-Z,0-9]+$")) {

                chooseActivity(currentProject, inputLine, employeeID);

            } else {
                System.out.println("Wrong format, please try again");
            }
        }
    }

    private static void chooseActivity(Project currentProject, String inputLine, Employee employeeID) {

        PSA currentActivity = Find.activity(currentProject, inputLine);
        if (currentActivity == null) {
            System.out.println("The activity does not exist, please try again");
        } else {
            // Choose activity
            if(employeeActivities.contains(currentActivity) || Main.projectLeader) {
                Choose.activity(currentActivity, currentProject, Main.projectLeader, employeeID);
                project(currentProject, employeeID);
            } else {
                System.out.println("You do not have rights to this activity");
            }
        }


    }

    private static void introMessage(Project currentProject, Employee employeeID) {
        System.out.println("Overview for project: " + currentProject.getProjectID()+"\r\n");
        System.out.println(
                "This project has the following startdate: " + currentProject.getStartDate().toString());
        System.out.println(
                "This project has the following expected end date: " + currentProject.getEndDate().toString());
        System.out.println("This project consists of the following activities:");

        // Metode, som printer alle aktiviteterne i dette projekt
        View.activity(currentProject, Main.projectLeader);

        if(!Main.getProjectLeaderStatus()) {
            employeeActivities = Find.activitiesOfEmployee(currentProject,employeeID);
            System.out.println("\r\nYou are part of the team for the following activities:");
            for(int i = 0; i < employeeActivities.size(); i++) {
                System.out.println(employeeActivities.get(i).getName());
            }
        }

        if (Main.projectLeader) {

            System.out.println("The following employees are assigned to this project:");
            // Metode, som printer alle employees, der er i dette projekt

        }
    }

    private static void isCurrentProjectLeader(Project currentProject, Employee employeeID) {
        if(currentProject.isProjectLeader(employeeID)){
            Main.projectLeader = true;
        } else {
            Main.projectLeader = false;
        }
    }

    public static void NSA(NSA currentNSA, Employee employeeID) {
        System.out.println("Menu for personal activity "+currentNSA.getName()+" for employee "+employeeID.getName());
        System.out.println("Startdate: "+currentNSA.getStartDate()+" \t Enddate: "+currentNSA.getEndDate());

        System.out.println("\r\nTo change the name of the personal activity: Please type 'NEWNAME'");
        System.out.println("To change the startdate of the personal activity: Please type 'NEWSTART");
        System.out.println("To change the enddate of the personal activity: Please type 'NEWEND'");
        System.out.println("To go back to the project select screen: Please type 'BACK'");

        while(true) {
            String inputLine = Main.scanner();

            if(inputLine.equalsIgnoreCase("NEWNAME")) {
                Change.newNSAName(currentNSA, employeeID);
                NSA(currentNSA, employeeID);
            } else if(inputLine.equalsIgnoreCase("NEWSTART")) {
                Change.newNSAStartDate(currentNSA, currentNSA.getEndDate());
                NSA(currentNSA, employeeID);
            } else if(inputLine.equalsIgnoreCase("NEWEND")) {
                Change.newNSAEndDate(currentNSA, currentNSA.getStartDate());
                NSA(currentNSA, employeeID);
            } else if(inputLine.equalsIgnoreCase("BACK")) {
                break;
            }

        }

    }
}
