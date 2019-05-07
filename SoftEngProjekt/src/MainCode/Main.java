import java.util.*;

public class Main {

    // Fields
    static boolean projectLeader = false;
    static Scanner input = new Scanner(System.in);
    static ArrayList<Project> projectList = new ArrayList<Project>();
    static ArrayList<Employee> employeeList;
    static Employee currentEmployeeID;

    public static void main(String[] args) {
        setup();
        View.login();
    }

    // Setup, fills program with content
    public static void setup() {
        //Laver 10 employees-logins
        Employee ABC = new Employee("ABC");
        Employee DEF = new Employee("DEF");
        Employee GHI = new Employee("GHI");
        Employee JKL = new Employee("JKL");
        Employee MNO = new Employee("MNO");
        Employee PQR = new Employee("PQR");
        Employee STU = new Employee("STU");
        Employee WVX = new Employee("WVX");
        Employee YZA = new Employee("YZA");
        Employee OEA = new Employee("OEA");

        //Laver liste med employees
        employeeList = new ArrayList<Employee>(Arrays.asList(ABC,DEF,GHI,JKL,MNO,PQR,STU,WVX,YZA,OEA));


        //Opretter aktiviteter
        //Aktivitet 1
        DateType AstartDate1 = new DateType("05/05/2019");
        DateType AendDate1 = new DateType("12/05/2019");
        String AName1 = "Activity1";
        ArrayList<Employee> Ae1 = new ArrayList<Employee>(Arrays.asList(ABC,DEF,PQR,YZA));

        PSA Activity1 = new PSA(AName1,10, AstartDate1, AendDate1, Ae1);

        //Aktivitet 2
        DateType AstartDate2 = new DateType("05/05/2019");
        DateType AendDate2 = new DateType("12/05/2019");
        String AName2 = "Activity2";
        ArrayList<Employee> Ae2 = new ArrayList<Employee>(Arrays.asList(DEF,JKL,YZA));

        PSA Activity2 = new PSA(AName2,20, AstartDate2, AendDate2, Ae2);

        //Opretter 5 projekter

        //Projekt1:
        DateType startDate1 = new DateType("05/05/2019");
        DateType endDate1 = new DateType("12/05/2019");
        String Name1 = "Project1";
        ArrayList<PSA> activityList1 = new ArrayList<PSA>(Arrays.asList(Activity1));

        Project project1 = new Project(Name1, "2019", "01", ABC.Name, startDate1, endDate1, activityList1, employeeList);

        //Projekt2:
        DateType startDate2 = new DateType("05/05/2019");
        DateType endDate2 = new DateType("12/05/2019");
        String Name2 = "Project2";
        ArrayList<PSA> activityList2 = new ArrayList<PSA>(Arrays.asList(Activity2));

        Project project2 = new Project(Name2, "2019", "02", DEF.Name, startDate2, endDate2, activityList2, employeeList);

        // Tilføjer projekter til projektlisten
        projectList.add(project1);
        projectList.add(project2);

    }

    public static void newNSA(Employee employeeID) {
        // Local fields
        String nameActivity = null;
        int hoursActivity = 0;
        DateType activityStartDate = null;
        DateType activityEndDate = null;

        ArrayList<Employee> employeeListActivity = new ArrayList<Employee>();

        // Add name to activity
        boolean inputStatus = false;
        while (inputStatus == false) {
            System.out.println("What is the name of the activity?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = input.nextLine();
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
            String inputLine = input.nextLine();

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
            String inputLine = input.nextLine();
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


        inputStatus = false;
        while (inputStatus == false) {
            System.out.println("How many hours should the activity be?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = input.nextLine();
            if (inputLine.matches("^[0-9]+$")) {
                hoursActivity = Integer.parseInt(inputLine);
                if (inputLine.matches("^[0-9]+$")) {
                    hoursActivity = Integer.parseInt(inputLine);
                    inputStatus = true;
                }else if (hoursActivity < 0 || hoursActivity == 0) {
                    System.out.println("Activity cannot be less than 1 hour, please try again");
                } else {
                    System.out.println("Wrong format, please try again");
                }
            } else if (inputLine.equals("CANCEL")) {
                View.overview(employeeID);
            }
        }

        // Creates activity with the given information:
        NSA activityPlaceholder = new NSA(nameActivity, hoursActivity, activityStartDate, activityEndDate, employeeID);
        employeeID.AddNonActivity(activityPlaceholder);
        System.out.print("The activity was succesfully added");
        View.overview(employeeID);
    }

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
            String inputLine = input.nextLine();
            if (Find.activity(currentProject, inputLine) != null) {
                System.out.println("An activity with this name already exists. Please try again");
            }else if(inputLine.equals("CANCEL")) {
                View.projectChosen(currentProject, employeeID);
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
            String inputLine = input.nextLine();

            if (inputLine.matches(
                    "^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+[0-9]{1}+$")) {
                if(DateType.datechecker(inputLine)) {
                    activityStartDate = new DateType(inputLine);
                    inputStatus = true;
                }
            } else if (inputLine.equals("CANCEL")) {
                View.projectChosen(currentProject,employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }

        // Add end date to activity
        inputStatus = false;
        while (!inputStatus) {
            System.out.println("On which date should the activity end? DD/MM/YYYY");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = input.nextLine();
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
                View.projectChosen(currentProject,employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }


        inputStatus = false;
        while (inputStatus == false) {
            System.out.println("How many hours of work should the activity contain?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = input.nextLine();
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
                View.projectChosen(currentProject,employeeID);
            } else {
                System.out.println("Wrong format, please try again");
            }
        }

        // Choose number of employees to add to activity
        inputStatus = false;
        while (!inputStatus) {
            System.out.println("How many employees should be added to the project?");
            System.out.println("To cancel creation of activity type: 'CANCEL'");
            String inputLine = input.nextLine();
            if (inputLine.matches("^[0-9]+$")) {
                employeesNumberActivity = Integer.parseInt(inputLine);
                inputStatus = true;
            } else if (inputLine.equals("CANCEL")) {
                View.projectChosen(currentProject,employeeID);
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
                String inputLine = input.nextLine();
                if (inputLine.matches("^[A-Z]{3}+$")) {

                    // Checks if this employee actually exists:
                    if (Find.employee(inputLine) == null) {
                        System.out.println("This employee does not exist. Please try again");
                        System.out.println("To cancel creation of activity type: 'CANCEL'");
                        continue;
                    } else if (inputLine.equals("CANCEL")) {
                        View.projectChosen(currentProject,employeeID);
                    } else if (employeeListActivity.contains(Find.employee(inputLine))){
                        System.out.println("Employee already part of activity");
                    } else {
                        employeeListActivity.add(Find.employee(inputLine));
                        System.out.println("The employee with ID " + inputLine + " was succesfully added");
                    }

                    inputStatus = true;
                } else if (inputLine.equals("CANCEL")) {
                    View.projectChosen(currentProject,employeeID);
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
        View.projectChosen(currentProject,employeeID);
    }

    // If activity is chosen
    public static void activityChosen(PSA currentActivity, Project currentProject, boolean projectleader, Employee employeeID) {
        // Local variable declaration
        String inputLine;
        String inputHours;
        int hours = 0;
        boolean inputStatus = false;

        while(!inputStatus) {
            if(!projectLeader)
                System.out.println("To add hours to this activity type: 'ADD'");
            if(projectLeader) {
                System.out.println("Startdate of activity: "+currentActivity.StartDate+"\t Enddate of activity: "+currentActivity.EndDate);
                Datemethods.dateDiff(currentActivity.StartDate, currentActivity.EndDate);

                int timeleft = currentActivity.spent()-currentActivity.Time;
                System.out.println("Expected time of activity: "+currentActivity.Time+"\t Time spent: "+currentActivity.spent()+"\t Time left: "+ timeleft);

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
                System.out.println("To assign an employee to the activity type: 'ASSIGN");
                System.out.println("To unassign an employee from the activity type: 'UNASSIGN'");
            }
            System.out.println("To ask for assistance type: 'HELP'");
            System.out.println("To go back to activity select type: 'BACK'");

            inputLine = input.nextLine();

            // Add hours to activity
            if(inputLine.equals("ADD") && !projectLeader) {
                while(!inputStatus) {
                    System.out.println("Enter hours:");
                    if(input.hasNextInt()) {
                        hours = input.nextInt();
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
                // Get assistance
            } else if(inputLine.equals("HELP")) {
                getHelp(currentActivity, currentProject, projectLeader, employeeID);
            } else if(inputLine.equals("BACK")) {
                View.projectChosen(currentProject,employeeID);
            }

            if(projectLeader) {

                // Change expected hours
                if(inputLine.equals("CHANGE")) {
                    changeHours(currentActivity, currentProject, projectLeader, employeeID);
                }

                // Assign employee
                if(inputLine.equals("ASSIGN")) {
                    assign(currentActivity, currentProject, projectLeader, employeeID);
                }

                // Unassign employee
                if(inputLine.equals("UNASSIGN")) {
                    unAssign(currentActivity, currentProject, projectLeader, employeeID);
                }
            }
        }




    }

    public static void getHelp(PSA currentActivity, Project currentProject, boolean projectLeader, Employee employee) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to add as assistance");
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = input.nextLine();
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
                activityChosen(currentActivity, currentProject, projectLeader,employee);
            }
        }
    }

    // Assigns employee to activity
    public static void assign(PSA currentActivity, Project currentProject, boolean projectleader, Employee employee) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to add to the activity");
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = input.nextLine();
            if (projectLeader == true && inputLine.matches("[A-Z]{3}+$")) {
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
                activityChosen(currentActivity, currentProject, projectLeader,employee);
            }
        }
    }

    // Unassigns employee from activity
    public static void unAssign(PSA currentActivity, Project currentProject, boolean projectleader, Employee employee) {
        boolean inputstatus = false;
        while (!inputstatus) {
            System.out.println("Please enter the ID of the employee you wish to remove from the activity");
            System.out.println("To go back to activity select type: 'BACK'");
            String inputLine = input.nextLine();
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
                activityChosen(currentActivity, currentProject, projectLeader,employee);
            }
        }
    }

    // Change expected hours
    public static void changeHours(PSA currentActivity, Project currentProject, boolean projectleader, Employee employee) {
        boolean inputStatus = false;
        int inputHours = 0;
        String inputLine;

        while (!inputStatus) {
            System.out.println("Enter new amount of hours");
            System.out.println("To go back to activity select type: 'BACK'");

            if(input.hasNextInt()) {
                inputHours = input.nextInt();
                if (inputHours > 0) {
                    currentActivity.ChangeExpectedTime(inputHours);
                    System.out.println("Expected time changed to: "+inputHours);
                    inputStatus = true;
                }
            } else {
                inputLine = input.nextLine();
                if (inputLine.equals("BACK")) {
                    activityChosen(currentActivity, currentProject, projectLeader, employee);
                } else {
                    System.out.println("Wrong format, please try again");
                    System.out.println("Enter new amount of hours");
                }
            }
        }
    }

    // Change enddate for project
    public static void changeEnddate(Project currentProject, Employee employeeID, DateType startdate) {
        System.out.println("Please enter new enddate DD/MM/YYYY");
        boolean inputStatus = false;
        DateType activityEndDate = new DateType("01/01/0000");
        while (!inputStatus) {
            System.out.println("To cancel selection of new enddate, please type: 'CANCEL'");
            String inputLine = input.nextLine();
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
                View.projectChosen(currentProject,employeeID);
            } else {
                System.out.println("Wrong date format, please try again");
            }
        }
        System.out.println("Enddate has been changed to: "+activityEndDate.toString());
    }
}

