import java.util.ArrayList;

public class PSA extends Activity{
    public ArrayList<Employee> Employees = new ArrayList<Employee>();;
    public ArrayList<Employee> Assistants = new ArrayList<Employee>();
    private ArrayList<Integer> TimeSpent = new ArrayList<Integer>();
    public int Time;

    public PSA(String name, int hours, DateType startdate, DateType enddate, ArrayList<Employee> employees){
        super(name, startdate, enddate);
        this.Time = hours;
        this.Employees = employees;
        this.TimeSpent = new ArrayList<Integer>();
    }

    // Methods
    public boolean addHelp(Employee ID, Project Number) {
        if(!Assistants.contains(ID) && !Employees.contains(ID)) {
            Assistants.add(ID);
            //System.out.println(ID + " added as an assistant in activity " + Name + "in project " + Number);
            Number.addHelp(ID);
            return true;
        } else {
            return false;

            //System.out.println(ID + " is already part of the team for this activity");
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

    public boolean containsAssistant(Employee employee) {
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

    public int ChangeExpectedTime(int hours){
        this.Time = hours;
        return hours;
    }

    public void setName(String newName) {

        this.Name =newName;
    }

    public void setEndDate(DateType newEndDate) {

        this.EndDate = newEndDate;

    }
}
