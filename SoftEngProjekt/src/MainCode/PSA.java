import java.util.ArrayList;

public class PSA extends Activity{
    private ArrayList<Employee> employees = new ArrayList<Employee>();;
    private ArrayList<Employee> assistants = new ArrayList<Employee>();
    private ArrayList<Integer> timeSpent = new ArrayList<Integer>();
    private int time;

    public PSA(String name, int hours, DateType startdate, DateType enddate, ArrayList<Employee> employees){
        super(name, startdate, enddate);
        this.time = hours;
        this.employees = employees;
        this.timeSpent = new ArrayList<Integer>();
    }

    // Methods
    public boolean addHelp(Employee ID, Project Number) {
    	// 1
        if(!assistants.contains(ID) && !employees.contains(ID)) {
            assistants.add(ID);
            Number.addHelp(ID);
            return true;
        } 
        return false;
    }

    public int registerTime(int i){
        timeSpent.add(i);
        return spent();
    }

    public void addEmployee(Employee employee){
        employees.add(employee);
    }

    public void removeEmployee(Employee employee){
        employees.remove(employee);
    }

    public void removeHelp(Employee employee) {
        assistants.remove(employee);
    }

    public boolean eContains(Employee employee) {
        return employees.contains(employee);
    }

    public boolean containsAssistant(Employee employee) {
        return assistants.contains(employee);
    }

    // Getter methods
    public int spent() {
        int timespent = 0;
        for(int i = 0; i < timeSpent.size(); i++) {
            timespent += timeSpent.get(i);
        }
        return timespent;
    }

    public ArrayList<Employee> getEmployees(){
    	return employees;
    }
    
    public ArrayList<Employee> getAssistants(){
    	return assistants;
    }
    
    public int getTime() {
    	return time;
    }
    
    // Setter methods   
    public int changeExpectedTime(int hours){
        this.time = hours;
        return hours;
    }
}
