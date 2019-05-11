import java.util.ArrayList;

public class Employee {

    private String name;
    private ArrayList<NSA> activities = new ArrayList<NSA>();

    public Employee(String newName) {
        this.name = newName;
    }

    // Setter methods
    public void AddNonActivity(NSA name) {
        activities.add(name);
    }

    public void RemoveNonActivity(NSA name) {
        activities.remove(name);
    }

    // Getter methods
    public String getName() {
        return name;
    }
    
    public ArrayList<NSA> getActivities(){
    	return activities;
    }
}
