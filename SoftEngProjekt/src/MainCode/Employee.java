import java.util.ArrayList;

public class Employee {

    private int ActivityNames;
    public String Name;
    public ArrayList<NSA> Activities;

    public Employee(String Name) {
        this.Name=Name;
    }

    public void AddNonActivity(String name, int hours, date startdate, date enddate) {
        ActivityNames++;
        NSA ActivityNames = new NSA(name, hours, startdate, enddate, Name);
        Activities.add(ActivityNames);
    }
}
