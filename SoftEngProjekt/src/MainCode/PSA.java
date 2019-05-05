import java.util.ArrayList;
import java.util.Scanner;

public class PSA extends Activity{
    public ArrayList<Employee> Employees;
    public ArrayList<Employee> Assistants;
    private ArrayList<Integer> TimeSpent;

    public PSA(String name, int hours, date startdate, date enddate, ArrayList<Employee> employees){
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

    public int RegisterTime(int i){
        TimeSpent.add(i);
        return Spent();
    }

    public ArrayList<Employee> SeekAssistance(Employee assistant){
        return Employee.AssistanceRequest(assistant);
    }

    public void AddEmployee(Employee employee){
        Employees.add(employee);
    }

    public void RemoveEmployee(Employee employee){
        Employees.remove(employee);
    }

    // Getter methods
    public int Spent() {
        int timespent = 0;
        for(int i = 0; i < TimeSpent.size(); i++) {
            timespent += TimeSpent.get(i);
        }
        return timespent;
    }


}
