
import java.util.ArrayList;

public class Project {

    // Fields
    private String projectName, projectYearID, projectNumberID, projectLeaderName;
    private DateType startDate, endDate;
    private ArrayList<PSA> activityList = new ArrayList<PSA>();
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    private ArrayList<Employee> assistants = new ArrayList<Employee>();

    // Constructor
    public Project(String projectName, String projectYearID, String projectNumberID, String projectLeaderName, DateType startDate, DateType endDate,
                   ArrayList<PSA> activityList, ArrayList<Employee> Employees) {

        this.projectName = projectName;
        this.projectYearID = projectYearID;
        this.projectNumberID = projectNumberID;
        this.projectLeaderName = projectLeaderName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activityList = activityList;
        this.employees = Employees;
    }

    // Methods
    public void addActivity(PSA activity) {
        activityList.add(activity);
    }

    public void removeActivity(PSA activity) {
        activityList.remove(activity);
    }

    public void addHelp(Employee ID) {
        if(!assistants.contains(ID) && !employees.contains(ID)) {
            assistants.add(ID);
        }
    }

    // Getter methods
    public String getProjectID() {
        return projectYearID + projectNumberID;
    }
    
    public ArrayList<PSA> getActivities() {
    	return activityList;
    }
    
    public ArrayList<Employee> getEmployees(){
    	return employees;
    }
    
    public ArrayList<Employee> getAssistants(){
    	return assistants;
    }
    
    public String getName() {
    	return projectName;
    }
    
    public String getProjectLeaderName() {
    	return projectLeaderName;
    }
    
    public DateType getStartDate() {
    	return startDate;
    }
    
    public DateType getEndDate() {
    	return endDate;
    }

    // Setter methods
    public void setProjectName(String newName) {
        this.projectName = newName;
    }

    public void setProjectStartDate(DateType newStartDate) {
        this.startDate = newStartDate;
    }

    public void setProjectEndDate(DateType newEndDate) {
        this.endDate = newEndDate;
    }

    // Find methods
    public boolean eContains(Employee ID){
        return employees.contains(ID);
    }

    public boolean aContains(Employee ID) {
        return assistants.contains(ID);
    }

    public boolean isProjectLeader(Employee EmployeeID) {
        if(EmployeeID.getName().equals(projectLeaderName)) {
            return true;
        }
        return false;
    }
}