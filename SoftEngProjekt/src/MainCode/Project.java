import java.util.ArrayList;
import java.util.Date;

public class Project {

    // Fields
    public String ProjectName;
    public Integer ProjectYearID;
    public Integer ProjectNumberID;
    public String ProjectLeaderName;
    public DateType StartDate;
    public DateType EndDate;
    public ArrayList<PSA> ActivityList;
    public ArrayList<Employee> Employees;
    public ArrayList<Employee> Assistants;

    // Constructor
    public Project(String projectName, Integer projectYearID, Integer projectNumberID, String projectLeaderName, DateType startDate, DateType endDate,
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
        return ProjectYearID +" "+ ProjectNumberID;
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
            LoggedTime += selected.Spent();
        }
        int TimeLeft = ExpectedTime - LoggedTime;
        // Prints project times
        System.out.printf("%-20s %-20s %-20s","Expected Time: " + ExpectedTime, "Logged Time: " + LoggedTime, "Time Left: " + TimeLeft);
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
    public boolean Econtains(Employee ID){
        return Employees.contains(ID) || Assistants.contains(ID);
    }

    public PSA findActivity(String name) {
        for(int i = 0; i < ActivityList.size(); i++) {
            if(ActivityList.get(i).Name == name) {
                return ActivityList.get(i);
            }
        }
        return null;
    }
}
