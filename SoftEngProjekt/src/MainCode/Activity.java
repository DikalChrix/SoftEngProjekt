public abstract class Activity {

    public String name;
    public DateType startDate;
    public DateType endDate;


    public Activity(String name, DateType startdate, DateType enddate){
        this.name = name;
        this.startDate = startdate;
        this.endDate = enddate;
    }
    
    // Getter methods
    public String getName() {
    	return name;
    }
    
    public DateType getStartDate() {
    	return startDate;
    }
    
    public DateType getEndDate() {
    	return endDate;
    }
    
    // Setter methods
    public void setName(String newName) {
    	name = newName;
    }
    
    public void setStartDate(DateType newStartDate) {
    	startDate = newStartDate;
    }
    
    public void setEndDate(DateType newEndDate) {
    	endDate = newEndDate;
    }
}

    