package planningProject;

public abstract class Activity {

    public String Name;
    public DateType StartDate;
    public DateType EndDate;


    public Activity(String name, DateType startdate, DateType enddate){
        this.Name = name;
        this.StartDate = startdate;
        this.EndDate = enddate;
    }
}

    