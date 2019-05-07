import java.util.ArrayList;
import java.util.Scanner;

public class PSA extends Activity{
    public ArrayList<Employee> Employees;
    public ArrayList<Employee> Assistants;
    private ArrayList<Integer> TimeSpent;

    public PSA(String name, int hours, DateType startdate, DateType enddate, ArrayList<Employee> employees){
        super(name, hours, startdate, enddate);
        super.Time = hours;
        this.Employees = employees;
        this.TimeSpent = new ArrayList<Integer>();
    }

    // Methods
    public void addHelp(Employee ID, Project Number) {
        if(!Assistants.contains(ID) && !Employees.contains(ID)) {
            Assistants.add(ID);
            System.out.println(ID + " added as an assistant in activity " + Name + "in project " + Number);
            Number.addHelp(ID);
        } else {
            System.out.println(ID + " is already part of the team for this activity");
        }
    }

    public int registerTime(int i){
        TimeSpent.add(i);
        return spent();
    }

    public void addEmployee(Employee employee){
        Employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        Employees.remove(employee);
    }

    public void removeHelp(Employee employee) {
        Assistants.remove(employee);
    }

    public boolean eContains(Employee employee) {
        return Employees.contains(employee);
    }

    public boolean hContains(Employee employee) {
        return Assistants.contains(employee);
    }

    // Getter methods
    public int spent() {
        int timespent = 0;
        for(int i = 0; i < TimeSpent.size(); i++) {
            timespent += TimeSpent.get(i);
        }
        return timespent;
    }
}
