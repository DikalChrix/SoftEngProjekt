public class Change {
    // Change enddate for project
    public static void enddate(Project currentProject, Employee employeeID, DateType startdate) {
        System.out.println("Please enter new enddate DD/MM/YYYY");

        while (true) {
            System.out.println("To cancel selection of new enddate, please type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            DateType projectEnddate = newValidEnddate(inputLine,startdate);
            if(projectEnddate != null) {
                currentProject.setProjectEndDate(projectEnddate);
                System.out.println("Enddate has been changed to: "+projectEnddate.toString());
                break;
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                break;
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
    }

    public static DateType newValidEnddate(String inputLine, DateType startdate) {
        if (inputLine.matches(
                "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
            if(DateType.dateChecker(inputLine)) {
                DateType activityEnddate = new DateType(inputLine);
                if(DateType.validDate(startdate, activityEnddate)) {
                    return activityEnddate;
                } else {
                    System.out.println("End date cannot be before start date, please try again");
                }
            } else {
                System.out.println("Incorrect date, try again");
            }
        }
        return null;
    }

    // Change expected hours
    public static void changeHours(PSA currentActivity) {
        boolean inputStatus = false;
        while (!inputStatus) {
            System.out.println("Enter new amount of hours");
            System.out.println("To go back to activity select type: 'BACK'");

            String inputLine = Main.input.nextLine();
            if(inputLine.matches("^[0-9]+$")) {
                inputStatus = changeTime(currentActivity, inputStatus, inputLine);
            } else if(inputLine.equalsIgnoreCase("BACK")) {
                break;
            } else {
                System.out.println("Wrong format, please try again");
            }
        }
    }

    private static boolean changeTime(PSA currentActivity, boolean inputStatus, String inputLine) {
        int hours = Integer.parseInt(inputLine);
        if (hours > 0) {
            currentActivity.ChangeExpectedTime(hours);
            System.out.println("Expected time changed to: "+hours);
            inputStatus = true;
        } else {
            System.out.println("Hours cannot be less than 1");
        }
        return inputStatus;
    }

    // Assign help for an activity
    public static void getHelp(PSA currentActivity, boolean projectLeader, Project currentProject) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to add as assistance");
            System.out.println("To go back to activity select: Please type 'BACK'");

            String inputLine = Main.input.nextLine();

            if (projectLeader && inputLine.matches("^[A-Z]{3}+$")) {
                inputstatus = assignAssistant(currentActivity, currentProject, inputLine);
            } else if (inputLine.equalsIgnoreCase("BACK")) {
                break;
            }
        }
    }

    private static boolean assignAssistant(PSA currentActivity, Project currentProject,	String employeeID) {
        boolean inputstatus = false;
        if (Find.employee(employeeID) == null) {
            System.out.println("Employee does not exist, try agian");
        } else {
            inputstatus = addEmployee(currentActivity, currentProject, employeeID);
        }
        return inputstatus;
    }

    // Unassigns employee from activity
    public static void removeHelp(PSA currentActivity, boolean projectLeader, Project currentProject) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the assistant you wish to remove from the activity");
            System.out.println("To go back to activity select type: 'BACK'");

            String inputLine = Main.input.nextLine();

            if (inputLine.matches("^[A-Z]{3}+$")) {
                String employeeID = inputLine;

                if (employeeDoesExist(employeeID)) {
                    inputstatus = removeAssistant(currentActivity, inputstatus, employeeID);
                }
            } else if (inputLine.equalsIgnoreCase("BACK")) {
                break;
            }
        }
    }

    private static boolean removeAssistant(PSA currentActivity, boolean inputstatus, String assistantID) {
        if(currentActivity.containsAssistant(Find.employee(assistantID))) {
            currentActivity.removeHelp(Find.employee(assistantID));
            System.out.println("Assistant "+assistantID+" succesfully removed from activity: "+currentActivity.Name);
            inputstatus = true;
        } else {
            System.out.println("Assistant is not part of this activity");
        }
        return inputstatus;
    }


    private static boolean addEmployee(PSA currentActivity, Project currentProject, String employeeID) {
        boolean inputstatus = false;
        if(!currentActivity.eContains(Find.employee(employeeID)) && !currentActivity.containsAssistant(Find.employee(employeeID))) {
            if(currentActivity.addHelp(Find.employee(employeeID),currentProject)) {
                inputstatus = true;
                System.out.println(employeeID + " added as an assistant in activity " + currentActivity.Name + "in project " + currentProject.ProjectName);
            } else {
                inputstatus = false;
            }
        } else if(currentActivity.eContains(Find.employee(employeeID))) {
            System.out.println("Employee is already part of the activity");
        } else if(currentActivity.containsAssistant(Find.employee(employeeID))) {
            System.out.println("Employee is already an assistant in this activity");
        }
        return inputstatus;
    }

    // Assigns employee to activity
    public static void assign(PSA currentActivity) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to add to the activity");
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = Main.input.nextLine();
            inputstatus = checksValidInput(currentActivity, inputstatus, inputLine);
        }
    }

    private static boolean checksValidInput(PSA currentActivity, boolean inputstatus, String inputLine) {
        if (Main.projectLeader == true && inputLine.matches("[A-Z]{3}+$")) {
            String employeeID = inputLine;
            if(employeeDoesExist(employeeID)) {
                inputstatus = assignEmployee(currentActivity, inputstatus, employeeID);
            }
        } else if (inputLine.equalsIgnoreCase("BACK")) {
            inputstatus = true;
        } else {

            System.out.println("Wrong format, please try again");

        }
        return inputstatus;
    }

    private static boolean assignEmployee(PSA currentActivity, boolean inputstatus, String employeeID) {
        if(!currentActivity.eContains(Find.employee(employeeID))) {
            if(currentActivity.eContains(Find.employee(employeeID))) {
                currentActivity.addEmployee(Find.employee(employeeID));
                System.out.println("Employee "+employeeID+" succesfully assigned to activity: "+currentActivity.Name);
                inputstatus = true;
            } else if(currentActivity.eContains(Find.employee(employeeID))){

                System.out.println("Employee is already part of this activity");

            } else {

                System.out.println("Employee is not part of this project");
            }
        }
        return inputstatus;
    }

    private static boolean employeeDoesExist(String employeeID) {
        if (Find.employee(employeeID) == null) {
            System.out.println("Employee does not exist, try agian");
            System.out.println("To go back to activity select type: 'BACK'");
        } else {
            return true;
        }
        return false;
    }

    // Unassigns employee from activity
    public static void unAssign(PSA currentActivity) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to remove from the activity");
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches("^[A-Z]{3}+$")) {
                String employeeID = inputLine;
                if (employeeDoesExist(employeeID)) {
                    inputstatus = unAssignEmployee(currentActivity, inputstatus, employeeID);
                }
            } else if (inputLine.equalsIgnoreCase("BACK")) {
                break;
            }
        }
    }

    private static boolean unAssignEmployee(PSA currentActivity, boolean inputstatus, String employeeID) {
        if(currentActivity.eContains(Find.employee(employeeID))) {
            currentActivity.removeEmployee(Find.employee(employeeID));
            System.out.println("Employee "+employeeID+" succesfully unassigned from activity: "+currentActivity.Name);
            inputstatus = true;
        } else {
            System.out.println("Employee is not part of this activity");
        }
        return inputstatus;
    }

    // Adds hours to given activity
    public static void add(PSA currentActivity) {
        boolean inputStatus = false;
        while(!inputStatus) {
            System.out.println("Enter hours:");
            String inputLine = Main.input.nextLine();
            if(inputLine.matches("^[0-9]+$")) {
                inputStatus = addHoursToActivity(currentActivity, inputStatus, inputLine);
            } else if(inputLine.equalsIgnoreCase("BACK")) {
                break;
            } else {
                System.out.println("Not a number, please try again");
                System.out.println("To go back to current activity type: 'BACK'");
            }
        }
    }

    private static boolean addHoursToActivity(PSA currentActivity, boolean inputStatus, String inputLine) {
        int hours = Integer.parseInt(inputLine);
        if(hours > 0) {
            currentActivity.registerTime(hours);
            System.out.println(hours+" was added to the time of the worktime of activity "+currentActivity.Name);
            inputStatus = true;
        } else {
            System.out.println("Hours cannot be less than 1, please try again");
            System.out.println("To go back to current activity type: 'BACK'");
        }
        return inputStatus;
    }

    public static void removeNSA(Employee employeeID) {
        if(employeeID.Activities.size() > 0) {
            printAllNSA(employeeID);
            while(true) {
                System.out.println("\r\nEnter the activity you wish to remove:");

                String inputLine = Main.input.nextLine();

                if(Find.activityNSA(employeeID, inputLine) != null) {
                    employeeID.RemoveNonActivity(Find.activityNSA(employeeID, inputLine));
                    System.out.println("The activity "+inputLine+" was removed\r\n");
                    break;
                } else {
                    System.out.println("Activity does not exist");
                }
            }
        } else {
            System.out.println("Employee does not have any personal activities");
        }
    }

    private static void printAllNSA(Employee employeeID) {
        System.out.println("List of personal activities:");
        for(int i = 0; i < employeeID.Activities.size(); i++) {
            System.out.println(employeeID.Activities.get(i).Name);
        }
    }

    public static void removePSA(Project currentProject) {
        if(currentProject.ActivityList.size() > 0) {
            printAllPSA(currentProject);

            while(true) {
                System.out.println("\r\nEnter the activity you wish to remove");

                String inputLine = Main.input.nextLine();

                if(Find.activity(currentProject, inputLine) != null) {
                    currentProject.removeActivity(Find.activity(currentProject, inputLine));
                    System.out.println("The activity "+inputLine+" was removed from the project "+currentProject.ProjectName+"\r\n");
                    break;
                } else {
                    System.out.println("Activity does not exist");
                }
            }
        } else {
            System.out.println("Project does not have any activities");
        }
    }

    private static void printAllPSA(Project currentProject) {
        System.out.println("List of all activities in project:");
        for(int i = 0; i < currentProject.ActivityList.size(); i++) {
            System.out.println(currentProject.ActivityList.get(i).Name);
        }
    }

    public static void newNSAName(NSA currentNSA, Employee employeeID) {
        System.out.println("Please type the new name of the activity");
        System.out.println("To cancel selection of new name: Please type 'CANCEL'");
        boolean inputStatus = false;
        while(!inputStatus) {
            String inputLine = Main.input.nextLine();
            inputStatus = readNSANameInput(currentNSA, employeeID, inputStatus, inputLine);
        }
    }

    private static boolean readNSANameInput(NSA currentNSA, Employee employeeID, boolean inputStatus,
                                            String inputLine) {
        if(inputLine.length() < 1) {
            System.out.println("Wrong format");
        } else if(inputLine.equalsIgnoreCase("CANCEL")) {
            inputStatus = true;
        } else if(inputLine.equalsIgnoreCase("REPORT") || inputLine.equalsIgnoreCase("CREATE") || inputLine.equalsIgnoreCase("ADD")
                || inputLine.equalsIgnoreCase("LOGOUT") || inputLine.equalsIgnoreCase("ASSIGN") || inputLine.equalsIgnoreCase("UNASSIGN")
                || inputLine.equalsIgnoreCase("NEWACT") || inputLine.equalsIgnoreCase("BACK")|| inputLine.equalsIgnoreCase("REMOVE")
                || inputLine.equalsIgnoreCase("EXIT")) {
            System.out.println("New name cannot be the same as a command");
            System.out.println("To cancel selection of new name: Please type 'CANCEL'");
        } else if(Find.activityNSA(employeeID, inputLine) != null){
            System.out.println("New name cannot be the same as the name of another personal activity");
        } else if(inputLine.matches("^[ ]{1,}") || inputLine.equals(null)) {
            System.out.println("New name cannot be empty");
        } else {
            System.out.println("Name of activity "+currentNSA.Name+" succesfully changed to: "+inputLine);
            currentNSA.setName(inputLine);
            inputStatus = true;
        }
        return inputStatus;
    }

    public static void newNSAStartDate(NSA currentNSA, DateType enddate) {
        System.out.println("Please enter new startdate DD/MM/YYYY");
        while (true) {
            System.out.println("To cancel selection of new startdate, please type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            DateType activityStartDate = newValidNSAStartDate(inputLine,enddate);
            if(activityStartDate != null) {
                currentNSA.setEndDate(activityStartDate);
                System.out.println("Startdate has been changed to: "+activityStartDate.toString());
                break;
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                break;
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
    }

    public static DateType newValidNSAStartDate(String inputLine, DateType enddate) {
        if (inputLine.matches(
                "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
            if(DateType.dateChecker(inputLine)) {
                DateType activityStartDate = new DateType(inputLine);
                if(DateType.validDate(activityStartDate, enddate)) {
                    return activityStartDate;
                } else {
                    System.out.println("Startdate cannot be after enddate, please try again");
                }
            } else {
                System.out.println("Incorrect date, try again");
            }
        }
        return null;
    }

    public static void newNSAEndDate(NSA currentNSA, DateType startdate) {
        System.out.println("Please enter new enddate DD/MM/YYYY");
        while (true) {
            System.out.println("To cancel selection of new enddate, please type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            DateType activityEndDate = newValidNSAEndDate(inputLine,startdate);
            if(activityEndDate != null) {
                currentNSA.setEndDate(activityEndDate);
                System.out.println("Enddate has been changed to: "+activityEndDate.toString());
                break;
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                break;
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
    }

    public static DateType newValidNSAEndDate(String inputLine, DateType startdate) {
        if (inputLine.matches(
                "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
            if(DateType.dateChecker(inputLine)) {
                DateType activityEndDate = new DateType(inputLine);
                if(DateType.validDate(startdate, activityEndDate)) {
                    return activityEndDate;
                } else {
                    System.out.println("Enddate cannot be before startdate, please try again");
                }
            } else {
                System.out.println("Incorrect date, try again");
            }
        }
        return null;
    }

    public static void newProjectName(Project currentProject) {
        System.out.println("Please type the new name of the project");
        System.out.println("To cancel selection of new name: Please type 'CANCEL'");
        boolean inputStatus = false;
        while(!inputStatus) {
            String inputLine = Main.input.nextLine();
            inputStatus = readProjectNameInput(currentProject, inputStatus, inputLine);
        }
    }

    private static boolean readProjectNameInput(Project currentProject, boolean inputStatus,
                                                String inputLine) {
        if(inputLine.length() < 1) {
            System.out.println("Wrong format");
        } else if(inputLine.equalsIgnoreCase("CANCEL")) {
            inputStatus = true;
        } else if(inputLine.equalsIgnoreCase("REPORT") || inputLine.equalsIgnoreCase("CREATE") || inputLine.equalsIgnoreCase("ADD")
                || inputLine.equalsIgnoreCase("LOGOUT") || inputLine.equalsIgnoreCase("ASSIGN") || inputLine.equalsIgnoreCase("UNASSIGN")
                || inputLine.equalsIgnoreCase("NEWACT") || inputLine.equalsIgnoreCase("BACK")|| inputLine.equalsIgnoreCase("REMOVE")
                || inputLine.equalsIgnoreCase("EXIT")) {
            System.out.println("New name cannot be the same as a command");
            System.out.println("To cancel selection of new name: Please type 'CANCEL'");
        } else if(findProjectName(inputLine) != null){
            System.out.println("New name cannot be the same as the name of another project");
        } else if(inputLine.matches("^[ ]{1,}") || inputLine.equals(null)) {
            System.out.println("New name cannot be empty");
        } else {
            System.out.println("Name of activity "+currentProject.ProjectName+" succesfully changed to: "+inputLine);
            currentProject.setProjectName(inputLine);
            inputStatus = true;
        }
        return inputStatus;
    }

    private static Project findProjectName(String projectName) {
        for(int i = 0; i < Main.projectList.size(); i++) {
            if(Main.projectList.get(i).ProjectName.equals(projectName)) {
                return Main.projectList.get(i);
            }
        }
        return null;
    }

    public static void newPSAEndDate(PSA currentPSA, DateType startdate) {
        System.out.println("Please enter new enddate DD/MM/YYYY");
        while (true) {
            System.out.println("To cancel selection of new enddate, please type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            DateType activityEndDate = newValidPSAEndDate(inputLine,startdate);
            if(activityEndDate != null) {
                currentPSA.setEndDate(activityEndDate);
                System.out.println("Enddate has been changed to: "+activityEndDate.toString());
                break;
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                break;
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
    }

    public static DateType newValidPSAEndDate(String inputLine, DateType startdate) {
        if (inputLine.matches(
                "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
            if(DateType.dateChecker(inputLine)) {
                DateType activityEndDate = new DateType(inputLine);
                if(DateType.validDate(startdate, activityEndDate)) {
                    return activityEndDate;
                } else {
                    System.out.println("Enddate cannot be before startdate, please try again");
                }
            } else {
                System.out.println("Incorrect date, try again");
            }
        }
        return null;
    }

    public static void newPSAName(Project currentProject, PSA currentActivity) {
        System.out.println("Please type the new name of the activity");
        System.out.println("To cancel selection of new name: Please type 'CANCEL'");
        boolean inputStatus = false;
        while(!inputStatus) {
            String inputLine = Main.input.nextLine();
            inputStatus = readPSANameInput(currentProject, currentActivity, inputStatus, inputLine);
        }
    }

    private static boolean readPSANameInput(Project currentProject, PSA currentActivity, boolean inputStatus,
                                            String inputLine) {
        if(inputLine.length() < 1) {
            System.out.println("Wrong format, try again");
        } else if(inputLine.equalsIgnoreCase("CANCEL")) {
            inputStatus = true;
        } else if(inputLine.equalsIgnoreCase("REPORT") || inputLine.equalsIgnoreCase("CREATE") || inputLine.equalsIgnoreCase("ADD")
                || inputLine.equalsIgnoreCase("LOGOUT") || inputLine.equalsIgnoreCase("ASSIGN") || inputLine.equalsIgnoreCase("UNASSIGN")
                || inputLine.equalsIgnoreCase("NEWACT") || inputLine.equalsIgnoreCase("BACK")|| inputLine.equalsIgnoreCase("REMOVE")
                || inputLine.equalsIgnoreCase("EXIT")) {
            System.out.println("New name cannot be the same as a command");
            System.out.println("To cancel selection of new name: Please type 'CANCEL'");
        } else if(Find.activity(currentProject, inputLine) != null){
            System.out.println("New name cannot be the same as the name of another activity in this project, try again");
        } else if(inputLine.matches("^[ ]{1,}") || inputLine.equals(null)) {
            System.out.println("New name cannot be empty, try again");
        } else {
            System.out.println("Name of activity "+currentActivity.Name+" succesfully changed to: "+inputLine);
            currentActivity.setName(inputLine);
            inputStatus = true;
        }
        return inputStatus;
    }
}
