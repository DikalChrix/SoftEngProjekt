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

    public String getProjectID() {
        return ProjectYearID + ProjectNumberID;
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

    // Find methods
    public boolean eContains(Employee ID){
        return Employees.contains(ID);
    }

    public boolean aContains(Employee ID) {
        return Assistants.contains(ID);
    }

    public boolean isProjectLeader(Employee EmployeeID) {
        if(EmployeeID.Name.equals(ProjectLeaderName)) {
            return true;
        }
        return false;
    }
}