public class Change {
    // Change enddate for project
    public static void enddate(Project currentProject, Employee employeeID, DateType startdate) {
        System.out.println("Please enter new enddate DD/MM/YYYY");

        while (true) {
            System.out.println("To cancel selection of new enddate, please type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            DateType activityEnddate = newValidEnddate(inputLine,startdate);
            if(activityEnddate != null) {
                System.out.println("Enddate has been changed to: "+activityEnddate.toString());
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
            if(DateType.datechecker(inputLine)) {
                DateType activityEnddate = new DateType(inputLine);
                if(Datemethods.validDate(startdate, activityEnddate)) {
                    return activityEnddate;
                } else {
                    System.out.println("End date cannot be before start date, please try again");
                }
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
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = Main.input.nextLine();
            if (projectLeader && inputLine.matches("^[A-Z]{3}+$")) {
                inputstatus = assignAssistant(currentActivity, currentProject, inputstatus, inputLine);
            } else if (inputLine.equalsIgnoreCase("BACK")) {
                break;
            }
        }
    }

    private static boolean assignAssistant(PSA currentActivity, Project currentProject, boolean inputstatus,
                                           String inputLine) {
        String employeeID = inputLine;
        if (Find.employee(employeeID) == null) {
            System.out.println("Employee does not exist, try agian");
        } else {
            inputstatus = addEmployee(currentActivity, currentProject, inputstatus, employeeID);
        }
        return inputstatus;
    }

    private static boolean addEmployee(PSA currentActivity, Project currentProject, boolean inputstatus,
                                       String employeeID) {
        if(!currentActivity.eContains(Find.employee(employeeID)) && !currentActivity.containsAssistant(Find.employee(employeeID))) {
            if(currentActivity.addHelp(Find.employee(employeeID),currentProject)) {
                inputstatus = true;
                System.out.println(employeeID + " added as an assistant in activity " + currentActivity.Name + "in project " + currentProject.name());
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
            try{
                inputstatus = checksValidInput(currentActivity, inputstatus, inputLine);
            }
            catch(Exception e) {
                System.out.println(e.getMessage());
            }
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

            throw new IllegalArgumentException("Wrong format, please try again");
            //System.out.println("Wrong format, please try again");
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

                throw new IllegalArgumentException("Employee is already part of this activity");
                //System.out.println("Employee is already part of this activity");
            } else {

                throw new IllegalArgumentException("Employee is not part of this project");
                //System.out.println("Employee is not part of this project");
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
            inputStatus = true;
        } else {
            System.out.println("Hours cannot be less than 1, please try again");
            System.out.println("To go back to current activity type: 'BACK'");
        }
        return inputStatus;
    }
}
