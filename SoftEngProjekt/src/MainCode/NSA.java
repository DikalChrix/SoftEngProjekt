package MainCode;

public class NSA extends Activity {
    String ID;

    public NSA(String name, int hours, date startdate, date enddate, Employee employee) {
        super(name, hours, startdate, enddate);
        this.ID = employee.name;
    }

}
