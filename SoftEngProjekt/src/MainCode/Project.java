import MainCode.PSA;

import java.util.ArrayList;
import java.util.Date;

public class Project {

	// Fields
	public String ProjectName;
	public Integer ProjectYearID;
	public Integer ProjectNumberID;
	public String ProjectLeaderName;
	public Date StartDate;
	public Date EndDate;
	public ArrayList<PSA> ActivityList;
    public ArrayList<Employee> Employees;

	// Constructor
	public Project(String projectName, Integer projectYearID, Integer projectNumberID, String projectLeaderName, Date startDate, Date endDate,
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

	// Getter methods
	public String name() {
		return ProjectName;
	}

	public String getProjectID() {
		return ProjectYearID +" "+ ProjectNumberID;
	}

	public Date getProjectStartDate() {
		return StartDate;
	}

	public Date getProjectEndDate() {
		return EndDate;
	}

	public String getProjectActivities() {
		return ActivityList.toString();
	}

	// Setter methods
	public void setProjectName(String newName) {
		this.ProjectName = newName;
	}

	public void setProjectStartDate(Date newStartDate) {
		this.StartDate = newStartDate;
	}

	public void setProjectEndDate(Date newEndDate) {
		this.EndDate = newEndDate;
	}

	public void setProjectTime(Date newStartDate, Date newEndDate) {
		StartDate = newStartDate;
		EndDate = newEndDate;
	}

	public boolean contains(Employee ID){
	    return Employees.contains(ID);
    }

}
