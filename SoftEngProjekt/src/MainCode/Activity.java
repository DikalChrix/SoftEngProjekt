package MainCode;

public abstract class Activity {

    public String Name;
    public int Time;
    public date StartDate;
    public date EndDate;


    public Activity(String name, int hours, date startdate, date enddate){
        this.Name = name;
        this.Time = hours;
        this.StartDate = startdate;
        this.EndDate = enddate;
    }

    public abstract int ChangeExpectedTime(int hours);
}
