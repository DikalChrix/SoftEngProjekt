import java.util.ArrayList;
import java.util.Scanner;

public class Employee {

    private String Name;
    private ArrayList<Employee> Employee = new ArrayList<Employee>();
    private boolean invalidCommand = true;

    public Employee(String Name) {
        this.Name=Name;
        ProjectList();
        AssistanceRequest();

        Scanner command = new Scanner(System.in);
        String nextcommand = command.nextLine();

        //make an if so that a project can b selected
        while(true){

            if(nextcommand.equalsIgnoreCase("add")) {
                AddNonActivity();


            } else if(nextcommand.contains(get.projectlist())) {


            } else {
                System.out.println("Invalid command! Please enter a valid commnad to continue");
            }
        }
    }

    public static void ProjectList() {
        // Use Employee id to get list of project, and display them along with the list
        // of all projects

    }

    public static void AddNonActivity() {
        // Method to make the employee able to add an activity to all projects

    }

    public static void AssistanceRequest() {
        // Updates when employee logs in, and check is someone called for his help.

    }
}
