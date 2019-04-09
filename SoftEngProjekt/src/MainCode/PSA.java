package MainCode;
import java.util.ArrayList;

public class PSA extends Activity{
        public ArrayList<Employee> Employees;
        public ArrayList<Employee> Assistants;
        public ArrayList<Integer> TimeSpent;


    public PSA(String name, int hours, date startdate, date enddate, ArrayList<Employee> employees){
        super(name, hours, startdate, enddate);
        super.Time = hours;
        this.Employees = employees;
    }

    public ArrayList<Integer> RegisterTime(int i){
        return Timespent.add(i);
    }

    public ArrayList<Employee> SeekAssistance(Employee assistant){
        return Employee.AssistanceRequest(assistant);
    }

    public void AddEmployee(Employee employee){
        Employees.add(employee);
    }
}
