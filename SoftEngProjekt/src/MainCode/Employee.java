import java.util.ArrayList;

public class Employee {

    public String Name;
    public ArrayList<NSA> Activities;

    public Employee(String Name) {
        this.Name=Name;
    }

    public void AddNonActivity(NSA name) {
        Activities.add(name);
    }
}
