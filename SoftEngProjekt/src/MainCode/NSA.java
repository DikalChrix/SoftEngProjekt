public class NSA extends Activity {
    String ID;

    public NSA(String name, DateType startdate, DateType enddate, Employee employee) {
        super(name, startdate, enddate);
        this.ID = employee.getName();
    }

    public void setName(String newName) {

        this.Name=newName;

    }

    public void setStartDate(DateType newStartDate) {

        this.StartDate=newStartDate;

    }

    public void setEndDate(DateType newEndDate) {

        this.EndDate = newEndDate;

    }
}