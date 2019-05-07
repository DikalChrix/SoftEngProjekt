
public class NSA extends Activity {
    String ID;

    public NSA(String name, DateType startdate, DateType enddate, Employee employee) {
        super(name, startdate, enddate);
        this.ID = employee.Name;
    }

}
