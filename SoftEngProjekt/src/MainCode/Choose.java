
public class Choose {

    // If activity is chosen
    public static void activity(PSA currentActivity, Project currentProject, boolean projectLeader, Employee employeeID) {
        // Local variable declaration
        String inputLine;
        boolean inputStatus = false;

        while(!inputStatus) {
            System.out.println("Startdate of activity: "+currentActivity.StartDate+"\t Enddate of activity: "+currentActivity.EndDate);
            Datemethods.dateDiff(currentActivity.StartDate, currentActivity.EndDate);

            int timeleft = currentActivity.Time-currentActivity.spent();
            System.out.println("Expected hours of work: "+currentActivity.Time+"\t Hours spent: "+currentActivity.spent()+"\t Hours left: "+ timeleft);

            if(!projectLeader)
                System.out.println("To add hours to this activity type: 'ADD'");
            if(projectLeader) {
                System.out.println("List of employees in activity: " + currentActivity.Name);
                if(currentActivity.Employees.size() > 0) {
                    for(int i = 0; i < currentActivity.Employees.size(); i++) {
                        System.out.println(currentActivity.Employees.get(i).Name);
                    }
                    System.out.println("");
                } else {
                    System.out.println("This activity has no employees assigned");
                }
                System.out.println("To change expected number of hours type: 'CHANGE'");
                System.out.println("To assign an employee to the activity type: 'ASSIGN'");
                System.out.println("To unassign an employee from the activity type: 'UNASSIGN'");
            }
            System.out.println("To ask for assistance type: 'HELP'");
            System.out.println("To go back to activity select type: 'BACK'");
            System.out.println("To exit program: Please type 'EXIT'");

            inputLine = Main.input.nextLine();

            // Add hours to activity
            if(inputLine.equalsIgnoreCase("ADD") && !projectLeader) {
                Change.add(currentActivity, employeeID, currentProject, projectLeader);
                // Get assistance
            } else if(inputLine.equalsIgnoreCase("HELP")) {
                Change.getHelp(currentActivity, currentProject, projectLeader, employeeID);
            } else if(inputLine.equalsIgnoreCase("BACK")) {
                Choose.project(currentProject,employeeID);
            } else if (inputLine.equalsIgnoreCase("EXIT")) {

                inputStatus = true;
            }

            if(projectLeader) {

                // Change expected hours
                if(inputLine.equalsIgnoreCase("CHANGE")) {
                    Change.changeHours(currentActivity, currentProject, projectLeader, employeeID);
                }

                // Assign employee
                if(inputLine.equalsIgnoreCase("ASSIGN")) {
                    Change.assign(currentActivity, currentProject, projectLeader, employeeID);
                }

                // Unassign employee
                if(inputLine.equalsIgnoreCase("UNASSIGN")) {
                    Change.unAssign(currentActivity, currentProject, projectLeader, employeeID);
                }
            }
        }
    }

    // If project is chosen
    public static void project(Project currentProject, Employee employeeID) {

        System.out.println("Overview for project: " + currentProject.getProjectID()+"\r\n");
        System.out.println(
                "This project has the following startdate: " + currentProject.getProjectStartDate().toString());
        System.out.println(
                "This project has the following expected end date: " + currentProject.getProjectEndDate().toString());
        System.out.println("This project consists of the following activities:");

        // Metode, som printer alle aktiviteterne i dette projekt
        View.activity(currentProject, Main.projectLeader);

        if(!Main.projectLeader) {
            Main.employeeActivities = Find.activityOfEmployee(currentProject,employeeID);
            System.out.println("\r\n You are part of the team for the following activities:");
            for(int i = 0; i < Main.employeeActivities.size(); i++) {
                System.out.println(Main.employeeActivities.get(i).Name);
            }
        }

        if (Main.projectLeader) {

            System.out.println("The following employees are assigned to this project:");
            // Metode, som printer alle employees, der er i dette projekt

        }

        System.out.println("\r\nYou now have the following choices:");
        System.out.println("To go back to 'Project Overview': Please type 'BACK'");
        System.out.println("To choose activity: Please type the name of the activity");

        if (Main.projectLeader) {
            System.out.println("\r\nProjectleader permissions:");
            System.out.println("To change the expected end date: Please type ENDDATE");
            System.out.println("To generate a report: Please type 'REPORT'");
            System.out.println("To create an activity: Please type 'NEWACT'");
        }

        System.out.println("To exit program: Please type 'EXIT'");

        boolean inputStatus = false;
        while (!inputStatus) {
            String inputLine = Main.input.nextLine();
            if (inputLine.equalsIgnoreCase("BACK")) {
                View.overview(Main.currentEmployeeID);
                inputStatus = true;
            } else if (inputLine.equalsIgnoreCase("EXIT")) {

                inputStatus = true;

            } else if (Main.projectLeader && inputLine.equalsIgnoreCase("ENDDATE")) {

                Change.enddate(currentProject, employeeID, currentProject.StartDate);

            } else if (Main.projectLeader && inputLine.matches("NEWACT")) {

                Create.newActivity(currentProject, employeeID);

            } else if (Main.projectLeader == true && inputLine.equalsIgnoreCase("REPORT")) {

                currentProject.getReport();
                System.out.println("");
                project(currentProject, employeeID);

            } else if (inputLine.matches("^[a-z,A-Z,0-9]+$")) {
                PSA currentActivity = Find.activity(currentProject, inputLine);
                if (currentActivity == null) {
                    System.out.println("The activity does not exist, please try again");
                } else {
                    // Choose activity
                    if(Main.employeeActivities.contains(currentActivity) || Main.projectLeader) {
                        Choose.activity(currentActivity, currentProject, Main.projectLeader, employeeID);
                        inputStatus = true;
                    } else {
                        System.out.println("You do not have rights to this activity");
                    }
                }

            } else {
                System.out.println("Wrong format, please try again");
            }
        }
    }
}
