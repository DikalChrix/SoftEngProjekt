import java.util.ArrayList;

public class Create {

    // Create new activity
    public static void newActivity(Project currentProject, Employee employeeID) {
        // Local fields
        String nameActivity = null;
        int hoursActivity = 0;
        DateType activityStartDate = null;
        DateType activityEndDate = null;
        int employeesNumberActivity = 0;

        ArrayList<Employee> employeeListActivity = new ArrayList<Employee>();

        // Add name to activity
        boolean inputStatus = false;
        while (!inputStatus) {
            System.out.println("What is the name of the activity?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (Find.activity(currentProject, inputLine) != null) {
                System.out.println("An activity with this name already exists. Please try again");
            }else if(inputLine.equals("CANCEL")) {
                Choose.project(currentProject, employeeID);
            } else {
                nameActivity = inputLine;
                inputStatus = true;
            }
        }

        // Add start date to activity
        inputStatus = false;
        while (!inputStatus) {
            System.out.println("On which date should the activity start? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();

            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.datechecker(inputLine)) {
                    activityStartDate = new DateType(inputLine);
                    inputStatus = true;
                }
            } else if (inputLine.equals("CANCEL")) {
                Choose.project(currentProject,employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }

        // Add end date to activity
        inputStatus = false;
        while (!inputStatus) {
            System.out.println("On which date should the activity end? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.datechecker(inputLine)) {
                    activityEndDate = new DateType(inputLine);
                    if(Datemethods.validDate(activityStartDate, activityEndDate)) {
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


        inputStatus = false;
        while (inputStatus == false) {
            System.out.println("How many hours of work should the activity contain?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches("^[0-9]{1,}+$")) {
                hoursActivity = Integer.parseInt(inputLine);
                if (inputLine.matches("^[0-9]{1,}+$")) {
                    if (hoursActivity > 0) {
                        hoursActivity = Integer.parseInt(inputLine);
                        inputStatus = true;
                    } else {
                        System.out.println("Activity cannot be less than 1 hour, please try again");
                    }
                } else {
                    System.out.println("Wrong format, please try again");
                }
            } else if (inputLine.equals("CANCEL")) {
                Choose.project(currentProject,employeeID);
            } else {
                System.out.println("Wrong format, please try again");
            }
        }

        // Choose number of employees to add to activity
        inputStatus = false;
        while (!inputStatus) {
            System.out.println("How many employees should be added to the project?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches("^[0-9]+$")) {
                employeesNumberActivity = Integer.parseInt(inputLine);
                inputStatus = true;
            } else if (inputLine.equals("CANCEL")) {
                Choose.project(currentProject,employeeID);
            } else {
                System.out.println("Wrong format, please try again");
            }
        }

        // Choose specific employees
        for (int i = 0; i < employeesNumberActivity; i++) {
            System.out.println("Please type the ID of the employees on each line:");
            if(i == 0) {
                System.out.println("To cancel creation of activity type: 'CANCEL'");
            }
            inputStatus = false;
            while (!inputStatus) {
                String inputLine = Main.input.nextLine();
                if (inputLine.matches("^[A-Z]{3}+$")) {

                    // Checks if this employee actually exists:
                    if (Find.employee(inputLine) == null) {
                        System.out.println("This employee does not exist. Please try again");
                        System.out.println("To cancel creation of activity type: 'CANCEL'");
                        continue;
                    } else if (inputLine.equals("CANCEL")) {
                        Choose.project(currentProject,employeeID);
                    } else if (employeeListActivity.contains(Find.employee(inputLine))){
                        System.out.println("Employee already part of activity");
                    } else {
                        employeeListActivity.add(Find.employee(inputLine));
                        System.out.println("The employee with ID " + inputLine + " was succesfully added");
                    }

                    inputStatus = true;
                } else if (inputLine.equals("CANCEL")) {
                    Choose.project(currentProject,employeeID);
                } else {
                    System.out.println("Wrong format, please try again");
                    System.out.println("To cancel creation of activity type: 'CANCEL'");
                }
            }
        }
        // Creates activity with the given information:
        PSA activityPlaceholder = new PSA(nameActivity, hoursActivity, activityStartDate, activityEndDate,
                employeeListActivity);
        currentProject.addActivity(activityPlaceholder);

        System.out.println("The activity was succesfully added to the project");
        Choose.project(currentProject,employeeID);
    }

    // Create new non specific activity
    public static void newNSA(Employee employeeID) {
        // Local fields
        String nameActivity = null;
        DateType activityStartDate = null;
        DateType activityEndDate = null;

        // Add name to activity
        boolean inputStatus = false;
        while (inputStatus == false) {
            System.out.println("What is the name of the activity?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if(inputLine.length() < 1) {
                System.out.println("Wrong format");
            } else if (employeeID.findActivity(inputLine) != null) {
                System.out.println("An activity with this name already exists. Please try again");
            } else if(inputLine.equals("CANCEL")) {
                View.overview(employeeID);
            } else {
                nameActivity = inputLine;
                inputStatus = true;
            }
        }

        // Add start date to activity
        inputStatus = false;
        while (!inputStatus) {
            System.out.println("On which date should the activity start? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();

            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.datechecker(inputLine)) {
                    activityStartDate = new DateType(inputLine);
                    inputStatus = true;
                }
            } else if (inputLine.equals("CANCEL")) {
                View.overview(employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }

        // Add end date to activity
        inputStatus = false;
        while (!inputStatus) {
            System.out.println("On which date should the activity end? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = Main.input.nextLine();
            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.datechecker(inputLine)) {
                    activityEndDate = new DateType(inputLine);
                    if(Datemethods.validDate(activityStartDate, activityEndDate)) {
                        inputStatus = true;
                    } else {
                        System.out.println("End date cannot be before start date, please try again");
                    }
                }
            } else if (inputLine.equals("CANCEL")) {
                View.overview(employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }

        // Creates activity with the given information:
        NSA activityPlaceholder = new NSA(nameActivity, activityStartDate, activityEndDate, employeeID);
        employeeID.AddNonActivity(activityPlaceholder);
        System.out.print("The activity was succesfully added");
        View.overview(employeeID);
    }
}
