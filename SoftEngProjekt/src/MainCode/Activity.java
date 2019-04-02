package MainCode;
import java.util.Collection.*;

public class Activity {
        public String Name;
        public int Time;
        public Arralist<Employee> Employees;
        public Arraylist<Employee> Assistants;


    public Activity(String name, int hours, Arralist<Employee> employees){
        this.Name = name;
        this.Time = hours;
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
}
