import java.util.ArrayList;

public class Project {

    // Fields
    public String ProjectName;
    public String ProjectYearID;
    public String ProjectNumberID;
    public String ProjectLeaderName;
    public DateType StartDate;
    public DateType EndDate;
    public ArrayList<PSA> ActivityList = new ArrayList<PSA>();
    public ArrayList<Employee> Employees = new ArrayList<Employee>();
    public ArrayList<Employee> Assistants = new ArrayList<Employee>();

    // Constructor
    public Project(String projectName, String projectYearID, String projectNumberID, String projectLeaderName, DateType startDate, DateType endDate,
                   ArrayList<PSA> activityList, ArrayList<Employee> Employees) {

        this.ProjectName = projectName;
        this.ProjectYearID = projectYearID;
        this.ProjectNumberID = projectNumberID;
        this.ProjectLeaderName = projectLeaderName;
        this.StartDate = startDate;
        this.EndDate = endDate;
        this.ActivityList = activityList;
        this.Employees = Employees;
    }

    // Methods
    public void addActivity(PSA activity) {
        ActivityList.add(activity);
    }

    public void removeActivity(PSA activity) {
        ActivityList.remove(activity);
    }

    public void addHelp(Employee ID) {
        if(!Assistants.contains(ID) && !Employees.contains(ID)) {
            Assistants.add(ID);
        }
    }

    // Getter methods
    public String name() {
        return ProjectName;
    }

    public String getProjectID() {
        return ProjectYearID + ProjectNumberID;
    }

    public DateType getProjectStartDate() {
        return StartDate;
    }

    public DateType getProjectEndDate() {
        return EndDate;
    }

    public ArrayList<PSA> getProjectActivities() {
        return ActivityList;
    }

    public void getReport() {
        int ExpectedTime = 0;
        int LoggedTime = 0;
        for(int i = 0; i < ActivityList.size(); i++) {
            PSA selected = ActivityList.get(i);
            ExpectedTime += selected.Time;
            LoggedTime += selected.spent();
        }
        int TimeLeft = ExpectedTime - LoggedTime;
        // Prints project times
        System.out.printf("%-25s %-25s", "Start date: "+StartDate,"End date: "+EndDate);
        System.out.println("");
        System.out.printf("%-20s %-20s %-20s","Expected Hours: " + ExpectedTime, "Logged Hours: " + LoggedTime, "Hours Left: " + TimeLeft);
        System.out.println("\r\n");
        // Prints employees in project
        System.out.printf("%42s", "Employees in project:\r\n");
        for(int i = 0; i < Employees.size(); i++) {
            Employee selected = Employees.get(i);
            System.out.printf("%-26s", selected.Name);
            if((i+1)%3 == 0) {
                System.out.println("");
            }
        }
        // Prints assistants in project
        if(Assistants.size() != 0) {
            System.out.println("\r\n");
            System.out.printf("%42s", "Assistants in project:\r\n");
            for(int i = 0; i < Assistants.size(); i++) {
                Employee selected = Assistants.get(i);
                System.out.printf("%-26s", selected.Name);
                if((i+1)%3 == 0) {
                    System.out.println("");
                }
            }
        }
        System.out.println("");
    }

    // Setter methods
    public void setProjectName(String newName) {
        this.ProjectName = newName;
    }

    public void setProjectStartDate(DateType newStartDate) {
        this.StartDate = newStartDate;
    }

    public void setProjectEndDate(DateType newEndDate) {
        this.EndDate = newEndDate;
    }

    public void setProjectTime(DateType newStartDate, DateType newEndDate) {
        StartDate = newStartDate;
        EndDate = newEndDate;
    }

    // Find methods
    public boolean eContains(Employee ID){
        return Employees.contains(ID) || Assistants.contains(ID);
    }

    public boolean isProjectLeader(Employee EmployeeID) {
        if(EmployeeID.Name.equals(ProjectLeaderName)) {
            return true;
        }
        return false;
    }
}
