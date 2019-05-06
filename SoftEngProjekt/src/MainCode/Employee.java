import java.util.ArrayList;

public class Employee {

    public String Name;
    public ArrayList<NSA> Activities = new ArrayList<NSA>();

    public Employee(String Name) {
        this.Name=Name;
    }

    public void AddNonActivity(NSA name) {
        Activities.add(name);
    }

    public NSA findActivity(String name) {
        if(Activities.size() == 0) {

        } else {
            for(int i = 0; i < Activities.size(); i++) {
                if(Activities.get(i).Name == name) {
                    return Activities.get(i);
                }
            }
        }
        return null;
    }
}
