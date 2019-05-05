public abstract class Activity {

    public String Name;
    public int Time;
    public DateType StartDate;
    public DateType EndDate;


    public Activity(String name, int hours, DateType startdate, DateType enddate){
        this.Name = name;
        this.Time = hours;
        this.StartDate = startdate;
        this.EndDate = enddate;
    }

    public int ChangeExpectedTime(int hours){
        this.Time = hours;
        return hours;
    }
}
