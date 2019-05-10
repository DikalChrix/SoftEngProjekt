package planningProject;
import java.util.ArrayList;

public class Employee {

    public String Name;
    public ArrayList<NSA> Activities = new ArrayList<NSA>();

    public Employee(String Name) {
        this.Name=Name;
    }

    public void AddNonActivity(NSA name) {
        Activities.add(name);
    }

    public String getName()
    {
    	return Name;
    }
    	
}
