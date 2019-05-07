public class Change {
    // Change enddate for project
    public static void enddate(Project currentProject, Employee employeeID, DateType startdate) {
        System.out.println("Please enter new enddate DD/MM/YYYY");
        boolean inputStatus = false;
        DateType activityEndDate = new DateType("01/01/0000");
        while (!inputStatus) {
            System.out.println("To cancel selection of new enddate, please type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.datechecker(inputLine)) {
                    activityEndDate = new DateType(inputLine);
                    if(Datemethods.validDate(startdate, activityEndDate)) {
                        inputStatus = true;
                    } else {
                        System.out.println("End date cannot be before start date, please try again");
                    }
                }
            } else if (inputLine.equals("CANCEL")) {
                Choose.project(currentProject,employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
        System.out.println("Enddate has been changed to: "+activityEndDate.toString());
    }

    // Change expected hours
    public static void changeHours(PSA currentActivity, Project currentProject, boolean projectleader, Employee employee) {
        boolean inputStatus = false;
        int inputHours = 0;
        String inputLine;

        while (!inputStatus) {
            System.out.println("Enter new amount of hours");
            System.out.println("To go back to activity select type: 'BACK'");

            if(Main.input.hasNextInt()) {
                inputHours = Main.input.nextInt();
                if (inputHours > 0) {
                    currentActivity.ChangeExpectedTime(inputHours);
                    System.out.println("Expected time changed to: "+inputHours);
                    inputStatus = true;
                }
            } else {
                inputLine = Main.input.nextLine();
                if (inputLine.equals("BACK")) {
                    Choose.activity(currentActivity, currentProject, Main.projectLeader, employee);
                } else {
                    System.out.println("Wrong format, please try again");
                    System.out.println("Enter new amount of hours");
                }
            }
        }
    }

    // Assign help for an activity
    public static void getHelp(PSA currentActivity, Project currentProject, boolean projectLeader, Employee employee) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to add as assistance");
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = Main.input.nextLine();
            if (projectLeader == true && inputLine.matches("^[A-Z]{3}+$")) {
                String employeeID = inputLine;
                if (Find.employee(employeeID) == null) {
                    System.out.println("Employee does not exist, try agian");
                } else {
                    if(!currentActivity.eContains(Find.employee(employeeID)) && !currentActivity.hContains(Find.employee(employeeID))) {
                        currentActivity.addHelp(Find.employee(employeeID),currentProject);
                        System.out.println("Employee succesfully assigned as assistance to activity: "+currentActivity);
                        inputstatus = true;
                    } else if(currentActivity.eContains(Find.employee(employeeID))) {
                        System.out.println("Employee is already part of the activity");
                    } else if(currentActivity.hContains(Find.employee(employeeID))) {
                        System.out.println("Employee is already an assistant in this activity");
                    }
                }
            } else if (inputLine.equals("BACK")) {
                Choose.activity(currentActivity, currentProject, projectLeader,employee);
            }
        }
    }

    // Assigns employee to activity
    public static void assign(PSA currentActivity, Project currentProject, boolean projectleader, Employee employee) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to add to the activity");
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = Main.input.nextLine();
            if (Main.projectLeader == true && inputLine.matches("[A-Z]{3}+$")) {
                String employeeID = inputLine;
                if (Find.employee(employeeID) == null) {
                    System.out.println("Employee does not exist, try agian");
                    System.out.println("To go back to activity select type: 'BACK'");
                } else if(!currentActivity.eContains(Find.employee(employeeID))) {
                    if(currentActivity.eContains(Find.employee(employeeID))) {
                        currentActivity.addEmployee(Find.employee(employeeID));
                        System.out.println("Employee "+employeeID+" succesfully assigned to activity: "+currentActivity.Name);
                        inputstatus = true;
                    } else {
                        System.out.println("Employee is not part of this project");
                    }
                } else if(currentActivity.eContains(Find.employee(employeeID))){
                    System.out.println("Employee is already part of this activity");
                }
            } else if (inputLine.equals("BACK")) {
                Choose.activity(currentActivity, currentProject, Main.projectLeader,employee);
            }
        }
    }

    // Unassigns employee from activity
    public static void unAssign(PSA currentActivity, Project currentProject, boolean projectLeader, Employee employee) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to remove from the activity");
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches("^[A-Z]{3}+$")) {
                String employeeID = inputLine;
                if (Find.employee(employeeID) == null) {
                    System.out.println("Employee does not exist, try agian");
                    System.out.println("To go back to activity select type: 'BACK'");
                } else if (inputLine.equals("BACK")) {
                    break;
                } else if (Find.employee(employeeID) != null){
                    if(currentActivity.eContains(Find.employee(employeeID))) {
                        currentActivity.removeEmployee(Find.employee(employeeID));
                        System.out.println("Employee "+employeeID+" succesfully unassigned from activity: "+currentActivity.Name);
                        inputstatus = true;
                    } else {
                        System.out.println("Employee is not part of this activity");
                    }
                }
            } else if (inputLine.equals("BACK")) {
                Choose.activity(currentActivity, currentProject, projectLeader,employee);
            }
        }
    }

    // Adds hours to given activity
    public static void add(PSA currentActivity, Employee employeeID, Project currentProject, boolean projectLeader) {
        boolean inputStatus = false;
        while(!inputStatus) {
            System.out.println("Enter hours:");
            if(Main.input.hasNextInt()) {
                int hours = Main.input.nextInt();
                if(hours > 0) {
                    currentActivity.registerTime(hours);
                    inputStatus = true;
                } else {
                    System.out.println("Hours cannot be less than 1, please try again");
                    System.out.println("To go back to current activity type: 'BACK'");
                }
            } else {
                System.out.println("Not a number, please try again");
                System.out.println("To go back to current activity type: 'BACK'");
            }
        }
        Choose.activity(currentActivity, currentProject, projectLeader, employeeID);
    }
}
