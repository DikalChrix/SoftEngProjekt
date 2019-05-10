package planningProject;

import java.util.ArrayList;

public class Find {

    public static void projectOfEmployee(Employee ID) {
        // Find Employee and call Employee.java
        if (Main.employeeList.contains(ID)) {
            for (int i = 0; i < Main.projectList.size(); i++) {
                if (Main.projectList.get(i).eContains(ID)) {
                    System.out.println("Project name: " + Main.projectList.get(i).ProjectName
                            + "\tProjectID: " + Main.projectList.get(i).getProjectID());
                }
            }
        } else {
            System.out.println("Employee not found. Please enter employee agian(Remember All CAPS");
        }
    }

    // Metode, som søger gennem projektlisten og returnerer rigtigt projekt ud fra
    // ID
    public static Project project(String yearID, String numID) {
        if(Main.projectList.size() > 0) {
            for (int i = 0; i < Main.projectList.size(); i++) {
                if (Main.projectList.get(i).getProjectID().equals(yearID + numID)) {
                    return Main.projectList.get(i);
                }
            }
        }
        return null;
    }

    public static ArrayList<Project> projectLeader(ArrayList<Project> projects, Employee EmployeeID) {
        ArrayList<Project> leaderProjects = new ArrayList<Project>();
        for(int i = 0; i < projects.size(); i++) {
            if(projects.get(i).isProjectLeader(EmployeeID)) {
                leaderProjects.add(projects.get(i));
            }
        }
        return leaderProjects;
    }

    public static Employee employee(String EmployeeID) {
        for (int i = 0; i < Main.employeeList.size(); i++) {
            if (Main.employeeList.get(i).Name.equals(EmployeeID)) {
                return Main.employeeList.get(i);
            }
        }
        return null;
    }

    public static PSA activity(Project currentProject,String name) {
        if(currentProject.ActivityList.size() > 0) {
            for(int i = 0; i < currentProject.ActivityList.size(); i++) {
                if(currentProject.ActivityList.get(i).Name.equals(name)) {
                    return currentProject.ActivityList.get(i);
                }
            }
        }
        return null;
    }

    // Adds activity to list if employee is part of it
    public static ArrayList<PSA> activityOfEmployee(Project currentProject, Employee name){
        ArrayList<PSA> placeholder = new ArrayList<PSA>();
        if(currentProject.ActivityList.size() > 0) {
            // Looks through all activities in project
            for(int i = 0; i < currentProject.ActivityList.size(); i++) {
                if(currentProject.ActivityList.get(i).Employees.size() > 0) {
                    // Looks through all employees in activity and compares them to the employee
                    for(int k = 0; k < currentProject.ActivityList.get(i).Employees.size(); k++) {
                        if(currentProject.ActivityList.get(i).Employees.get(k).equals(name)) {
                            placeholder.add(currentProject.ActivityList.get(i));
                        }
                    }
                }
            }
        }
        return placeholder;
    }

    public static NSA activityNSA(Employee ID, String activityName) {
        if(ID.Activities.size() == 0) {

        } else {
            for(int i = 0; i < ID.Activities.size(); i++) {
                if(ID.Activities.get(i).Name == activityName) {
                    return ID.Activities.get(i);
                }
            }
        }
        return null;
    }
}