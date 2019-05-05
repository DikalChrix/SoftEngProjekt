
public class NSA extends Activity {
    String ID;

    public NSA(String name, int hours, DateType startdate, DateType enddate, Employee employee) {
        super(name, hours, startdate, enddate);
        this.ID = employee.Name;
    }

}
