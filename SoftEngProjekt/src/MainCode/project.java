import java.util.ArrayList;
import java.util.Date;

public class project {

	// Fields
	private String projectName;
	private Integer projectYearID;
	private Integer projectNumberID;
	private Date startDate;
	private Date endDate;
	private ArrayList<PSA> activityList = new ArrayList<PSA>();

	// Constructor
	public project(String projectName, Integer projectYearID, Integer projectNumberID, Date startDate, Date endDate,
			ArrayList<Activities> activityList) {

		this.projectName = projectName;
		this.projectYearID = projectYearID;
		this.projectNumberID = projectNumberID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.activityList = activityList;

	}

	// Methods
	public void addActivity(PSA activity) {
		activityList.add(activity);
	}

	public void removeActivity(PSA activity) {
		activityList.remove(activity);
	}

	// Getter methods
	public String name() {
		return projectName;
	}

	public int getProjectID() {
		return projectYearID + projectNumberID;
	}

	public Date getProjectStartDate() {
		return startDate;
	}

	public Date getProjectEndDate() {
		return endDate;
	}

	public String getProjectActivities() {
		return activityList.toString();
	}

	// Setter methods
	public void setProjectName(String newName) {
		this.projectName = newName;
	}

	public void setProjectStartDate(Date newStartDate) {
		this.startDate = newStartDate;
	}

	public void setProjectEndDate(Date newEndDate) {
		this.endDate = newEndDate;
	}

	public void setProjectTime(Date newStartDate, Date newEndDate) {
		startDate = newStartDate;
		endDate = newEndDate;
	}

}
