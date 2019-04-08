package MainCode;
import java.util.Collection.*;

public class PSA extends Activity{
        public Arralist<Employee> Employees;
        public Arraylist<Employee> Assistants;


    public PSA(String name, int hours, date startdate, date enddate, Arralist<Employee> employees){
        super(name, hours, startdate, enddate);
        super.Time = hours;
        this.Employees = employees;
    }

    public int RegisterTime(int i){
        return this.Time = Time-i;
    }

    public int SetExpectedTime(int hours){
        return this.Time = hours;
    }

    public ArrayList<Employee> SetEmployees(Employee employee){
        return this.Employees.add(employee);
    }

    public void SeekAssistance(Employee assistant){
        Employee.AssistanceRequest(assistant);
    }

    public int ChangeExpectedTime(int hours) {
        return super.Time = hours;
    }
}
