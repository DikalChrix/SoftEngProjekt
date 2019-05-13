package planningProject;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;

public class Find {

	public static ArrayList<Project> projectsOfEmployee(Employee ID) {
		// Find Employee and call Employee.java\
		ArrayList<Project> employeeProjects = new ArrayList<Project>();
		if (Main.getEmployees().contains(ID)) {
			employeeProjects = new ArrayList<Project>();
			for (int i = 0; i < Main.getProjects().size(); i++) {
				if (Main.getProjects().get(i).eContains(ID)) {
					employeeProjects.add(Main.getProjects().get(i));
					// System.out.println("Project name: " + Main.getProjects().get(i).getName()
					// + "\t ProjectID: " + Main.getProjects().get(i).getProjectID());
				}
			}
		}
		return employeeProjects;
	}

	// Metode, som søger gennem projektlisten og returnerer rigtigt projekt ud fra
	// ID
	public static Project project(String yearID, String numID) {
		if (Main.getProjects().size() > 0) {
			for (int i = 0; i < Main.getProjects().size(); i++) {
				if (Main.getProjects().get(i).getProjectID().equals(yearID + numID)) {
					return Main.getProjects().get(i);
				}
			}
		}
		return null;
	}

	public static ArrayList<Project> projectLeader(ArrayList<Project> projects, Employee EmployeeID) {
		ArrayList<Project> leaderProjects = new ArrayList<Project>();
		for (int i = 0; i < projects.size(); i++) {
			if (projects.get(i).isProjectLeader(EmployeeID)) {
				leaderProjects.add(projects.get(i));
			}
		}
		return leaderProjects;
	}

	public static Employee employee(String EmployeeID) {

		// Precondition
		assert EmployeeID != null;
		assert Main.getEmployees() != null;

		// 1
		for (int i = 0; i < Main.getEmployees().size(); i++) {
			// 2
			if (Main.getEmployees().get(i).getName().equals(EmployeeID)) {

				// Postcondition
				assert Main.getEmployees().get(i).getName().equals(EmployeeID);

				return Main.getEmployees().get(i);
			}
		}
		return null;
	}

	public static PSA activity(Project currentProject, String name) {

		ArrayList<PSA> activitiesForCurrentProject = currentProject.getActivities();
		// 1
		if (activitiesForCurrentProject.size() > 0) {
			// 2
			for (int i = 0; i < activitiesForCurrentProject.size(); i++) {
				// 3
				if (activitiesForCurrentProject.get(i).getName().equals(name)) {
					return activitiesForCurrentProject.get(i);
				}
			}
		}
		return null;
	}

	// Adds activity to list if employee is part of it
	public static ArrayList<PSA> activitiesOfEmployee(Project currentProject, Employee employeeID) {
		ArrayList<PSA> placeholder = new ArrayList<PSA>();
		ArrayList<PSA> activitiesForCurrentProject = currentProject.getActivities();

		// Preconditions
		assert currentProject != null;
		assert employeeID != null;
		assert currentProject.getActivities() != null;

		// 1
		if (activitiesForCurrentProject.size() > 0) {
			// Looks through all activities in project
			// 2
			for (int i = 0; i < activitiesForCurrentProject.size(); i++) {
				// 3
				if (activitiesForCurrentProject.get(i).getEmployees().size() > 0) {
					// Looks through all employees in activity and compares them to the employee
					// 4
					for (int k = 0; k < activitiesForCurrentProject.get(i).getEmployees().size(); k++) {
						// 5
						if (activitiesForCurrentProject.get(i).getEmployees().get(k).equals(employeeID)) {
							placeholder.add(activitiesForCurrentProject.get(i));

							// Postcondition
							assert placeholder.size() > 0;
						}
					}
				}
			}
		}

		// Postcondition
		assert placeholder != null;

		return placeholder;
	}

	public static NSA activityNSA(Employee employeeID, String activityName) {

		// Precondition
		assert employeeID != null;
		assert activityName != null;
		assert employeeID.getActivities() != null;

		// 1
		if (employeeID.getActivities().size() > 0) {
			// 2
			for (int i = 0; i < employeeID.getActivities().size(); i++) {
				// 3
				if (employeeID.getActivities().get(i).getName().equals(activityName)) {

					// Postcondition
					assert employeeID.getActivities().get(i).getName().equals(activityName);

					return employeeID.getActivities().get(i);
				}
			}
		}
		return null;
	}
}