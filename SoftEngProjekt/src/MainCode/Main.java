
import java.util.*;

public class Main {

    // Fields
    static boolean projectLeader = false;
    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Project> projectList = new ArrayList<Project>();
    private static ArrayList<Employee> employeeList = new ArrayList<Employee>();

    public static void main(String[] args) {
        Main.setup();
        View.login();
    }

    public static boolean getProjectLeaderStatus() {
    	return projectLeader;
    }
    
    public static String scanner() {
    	return input.nextLine();
    }
    
    public void changeProjectLeaderStatus(boolean projectLeaderStatus) {
    	projectLeader = projectLeaderStatus;
    }
    
    public static ArrayList<Project> getProjects(){
    	return getProjectList();
    }
    
    public static ArrayList<Employee> getEmployees(){
    	return getEmployeeList();
    }
    
    
    
    
    
    // Setup, fills program with content
    public static void setup() {
        //Laver 10 employees-logins
        Employee ABC = new Employee("ABC");
        Employee DEF = new Employee("DEF");
        Employee GHI = new Employee("GHI");
        Employee JKL = new Employee("JKL");
        Employee MNO = new Employee("MNO");
        Employee PQR = new Employee("PQR");
        Employee STU = new Employee("STU");
        Employee WVX = new Employee("WVX");
        Employee YZA = new Employee("YZA");
        Employee OEA = new Employee("OEA");

        //Laver liste med employees
        setEmployeeList(new ArrayList<Employee>(Arrays.asList(ABC,DEF,GHI,JKL,MNO,PQR,STU,WVX,YZA,OEA)));


        //Opretter aktiviteter
        //Aktivitet 1
        DateType AstartDate1 = new DateType("05/05/2019");
        DateType AendDate1 = new DateType("12/05/2019");
        String AName1 = "Activity1";
        ArrayList<Employee> Ae1 = new ArrayList<Employee>(Arrays.asList(DEF,PQR,YZA));

        PSA Activity1 = new PSA(AName1,10, AstartDate1, AendDate1, Ae1);

        //Aktivitet 2
        DateType AstartDate2 = new DateType("05/05/2019");
        DateType AendDate2 = new DateType("12/05/2019");
        String AName2 = "Activity2";
        ArrayList<Employee> Ae2 = new ArrayList<Employee>(Arrays.asList(JKL,YZA));

        PSA Activity2 = new PSA(AName2,20, AstartDate2, AendDate2, Ae2);

        //Opretter 5 projekter

        //Projekt1:
        DateType startDate1 = new DateType("05/05/2019");
        DateType endDate1 = new DateType("12/05/2019");
        String Name1 = "Project1";
        ArrayList<PSA> activityList1 = new ArrayList<PSA>(Arrays.asList(Activity1));

        Project project1 = new Project(Name1, "2019", "01", ABC.getName(), startDate1, endDate1, activityList1, getEmployeeList());

        //Projekt2:
        DateType startDate2 = new DateType("05/05/2019");
        DateType endDate2 = new DateType("12/05/2019");
        String Name2 = "Project2";
        ArrayList<PSA> activityList2 = new ArrayList<PSA>(Arrays.asList(Activity2));

        Project project2 = new Project(Name2, "2019", "02", DEF.getName(), startDate2, endDate2, activityList2, getEmployeeList());

        // Tilføjer projekter til projektlisten
        getProjectList().add(project1);
        getProjectList().add(project2);

    }

	public static ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	public static void setEmployeeList(ArrayList<Employee> employeeList) {
		Main.employeeList = employeeList;
	}

	public static ArrayList<Project> getProjectList() {
		return projectList;
	}

	public static void setProjectList(ArrayList<Project> projectList) {
		Main.projectList = projectList;
	}
}

