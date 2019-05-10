import java.util.ArrayList;

public class Create {

    // Create new activity
    public static void newActivity(Project currentProject, Employee employeeID) {
        // Local fields
        String nameActivity = null;
        int hoursActivity = 0,employeesNumberActivity = 0;
        DateType activityStartDate=null, activityEndDate = null;

        ArrayList<Employee> employeeListActivity = new ArrayList<Employee>();

        nameActivity = addName(currentProject, employeeID, nameActivity);

        activityStartDate = addStartdate(currentProject, employeeID, activityStartDate);

        activityEndDate = addEnddate(currentProject, employeeID, activityStartDate, activityEndDate);

        hoursActivity = chooseHours(currentProject, employeeID, hoursActivity);

        employeesNumberActivity = addNumberOfEmployees(currentProject, employeeID, employeesNumberActivity);

        // Choose specific employees
        addEmployees(currentProject, employeeID, employeesNumberActivity, employeeListActivity);
        // Creates activity with the given information:
        PSA activityPlaceholder = new PSA(nameActivity, hoursActivity, activityStartDate, activityEndDate,
                employeeListActivity);
        currentProject.addActivity(activityPlaceholder);

        System.out.println("The activity was succesfully added to the project");
        Choose.project(currentProject,employeeID);
    }

    private static void addEmployees(Project currentProject, Employee employeeID, int employeesNumberActivity,
                                     ArrayList<Employee> employeeListActivity) {
        for (int i = 0; i < employeesNumberActivity;) {
            System.out.println("Please type the ID of the employees on each line:");
            if(i == 0) {
                System.out.println("To cancel creation of activity type: 'CANCEL'");
            }
            while (true) {
                String inputLine = Main.input.nextLine();
                if (inputLine.matches("^[A-Z]{3}+$")) {

                    doesEmployeeExist(currentProject, employeeID, employeeListActivity, inputLine);

                } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                    Choose.project(currentProject,employeeID);
                } else {
                    System.out.println("Wrong format, please try again");
                    System.out.println("To cancel creation of activity type: 'CANCEL'");
                }
            }
        }
    }

    private static void doesEmployeeExist(Project currentProject, Employee employeeID,
                                          ArrayList<Employee> employeeListActivity, String inputLine) {
        if (Find.employee(inputLine) == null) {
            System.out.println("This employee does not exist. Please try again");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
        } else if (inputLine.equalsIgnoreCase("CANCEL")) {
            Choose.project(currentProject,employeeID);
        } else if (employeeListActivity.contains(Find.employee(inputLine))){
            System.out.println("Employee already part of activity");
        } else {
            employeeListActivity.add(Find.employee(inputLine));
            System.out.println("The employee with ID " + inputLine + " was succesfully added");
        }
    }

    private static int addNumberOfEmployees(Project currentProject, Employee employeeID, int employeesNumberActivity) {
        while (true) {
            System.out.println("How many employees should be added to the project?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches("^[0-9]+$")) {
                employeesNumberActivity = Integer.parseInt(inputLine);
                break;
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                Choose.project(currentProject,employeeID);
            } else {
                System.out.println("Wrong format, please try again");
            }
        }
        return employeesNumberActivity;
    }

    private static int chooseHours(Project currentProject, Employee employeeID, int hoursActivity) {
        while (true) {
            System.out.println("How many hours of work should the activity contain?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches("^[0-9]{1,}+$")) {
                hoursActivity = Integer.parseInt(inputLine);
                if (hoursActivity > 0) {
                    break;
                } else {
                    System.out.println("Activity cannot be less than 1 hour, please try again");
                }
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                Choose.project(currentProject,employeeID);
            } else {
                System.out.println("Wrong format, please try again");
            }
        }
        return hoursActivity;
    }

    private static DateType addEnddate(Project currentProject, Employee employeeID, DateType activityStartDate,
                                       DateType activityEndDate) {
        while (true) {
            System.out.println("On which date should the activity end? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.dateChecker(inputLine)) {
                    activityEndDate = new DateType(inputLine);
                    if(DateType.validDate(activityStartDate, activityEndDate)) {
                        break;
                    } else {
                        System.out.println("End date cannot be before start date, please try again");
                    }
                } else {
                    System.out.println("Incorrect date, try again");
                }
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                Choose.project(currentProject,employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
        return activityEndDate;
    }

    private static DateType addStartdate(Project currentProject, Employee employeeID, DateType activityStartDate) {
        while (true) {
            System.out.println("On which date should the activity start? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();

            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.dateChecker(inputLine)) {
                    activityStartDate = new DateType(inputLine);
                    break;
                } else {
                    System.out.println("Incorrect date, try again");
                }
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                Choose.project(currentProject,employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
        return activityStartDate;
    }

    private static String addName(Project currentProject, Employee employeeID, String nameActivity) {
        while (true) {
            System.out.println("What is the name of the activity?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (Find.activity(currentProject, inputLine) != null) {
                System.out.println("An activity with this name already exists. Please try again");
            }else if(inputLine.equalsIgnoreCase("CANCEL")) {
                Choose.project(currentProject, employeeID);
            }else if(inputLine.equalsIgnoreCase("REPORT") || inputLine.equalsIgnoreCase("CREATE") || inputLine.equalsIgnoreCase("ADD")
                    || inputLine.equalsIgnoreCase("LOGOUT") || inputLine.equalsIgnoreCase("ASSIGN") || inputLine.equalsIgnoreCase("UNASSIGN")
                    || inputLine.equalsIgnoreCase("NEWACT") || inputLine.equalsIgnoreCase("BACK")) {
                System.out.println("Activity name cannot be the same as a system command, please try again");
            } else {
                nameActivity = inputLine;
                break;
            }
        }
        return nameActivity;
    }

    // Create new non specific activity
    public static void newNSA(Employee employeeID) {
        // Local fields
        String nameActivity = null;
        DateType activityStartDate = null, activityEndDate = null;

        // Add name to activity
        nameActivity = addNSAName(employeeID, nameActivity);

        // Add start date to activity
        activityStartDate = addNSAStartdate(employeeID, activityStartDate);

        // Add end date to activity
        activityEndDate = addNSAEnddate(employeeID, activityStartDate, activityEndDate);

        // Creates activity with the given information:
        NSA activityPlaceholder = new NSA(nameActivity, activityStartDate, activityEndDate, employeeID);
        employeeID.AddNonActivity(activityPlaceholder);
        System.out.println("The activity was succesfully added\r\n");
        View.overview(employeeID);
    }

    private static DateType addNSAEnddate(Employee employeeID, DateType activityStartDate, DateType activityEndDate) {
        while (true) {
            System.out.println("On which date should the activity end? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.dateChecker(inputLine)) {
                    activityEndDate = new DateType(inputLine);
                    if(DateType.validDate(activityStartDate, activityEndDate)) {
                        break;
                    } else {
                        System.out.println("End date cannot be before start date, please try again");
                    }
                } else {
                    System.out.println("Incorrect date, try again");
                }
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                View.overview(employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
        return activityEndDate;
    }

    private static DateType addNSAStartdate(Employee employeeID, DateType activityStartDate) {
        while (true) {
            System.out.println("On which date should the activity start? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();

            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.dateChecker(inputLine)) {
                    activityStartDate = new DateType(inputLine);
                    break;
                } else {
                    System.out.println("Incorrect date, try again");
                }
            } else if (inputLine.equalsIgnoreCase("CANCEL")) {
                View.overview(employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
        return activityStartDate;
    }

    private static String addNSAName(Employee employeeID, String nameActivity) {
        while (true) {
            System.out.println("What is the name of the activity?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if(inputLine.length() < 1) {
                System.out.println("Wrong format");
            } else if (employeeID.findActivity(inputLine) != null) {
                System.out.println("An activity with this name already exists. Please try again");
            } else if(inputLine.equalsIgnoreCase("CANCEL")) {
                View.overview(employeeID);
            }else if(inputLine.equalsIgnoreCase("REPORT") || inputLine.equalsIgnoreCase("CREATE") || inputLine.equalsIgnoreCase("ADD")
                    || inputLine.equalsIgnoreCase("LOGOUT") || inputLine.equalsIgnoreCase("ASSIGN") || inputLine.equalsIgnoreCase("UNASSIGN")
                    || inputLine.equalsIgnoreCase("NEWACT") || inputLine.equalsIgnoreCase("BACK") || inputLine.equalsIgnoreCase("EXIT")) {
                System.out.println("Activity name cannot be the same as a system command, please try again");
            } else {
                nameActivity = inputLine;
                break;
            }
        }
        return nameActivity;
    }
}
