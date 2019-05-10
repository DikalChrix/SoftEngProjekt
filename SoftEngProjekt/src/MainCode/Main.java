import java.util.*;

public class Main {

    // Fields
    static boolean projectLeader = false;
    static Scanner input = new Scanner(System.in);
    static ArrayList<Project> projectList = new ArrayList<Project>();
    static ArrayList<Employee> employeeList;
    static ArrayList<PSA> employeeActivities = new ArrayList<PSA>();
    static Employee currentEmployeeID;

    public static void main(String[] args) {
        setup();
        View.login();
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
        Main.employeeList = new ArrayList<Employee>(Arrays.asList(ABC,DEF,GHI,JKL,MNO,PQR,STU,WVX,YZA,OEA));


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

        Project project1 = new Project(Name1, "2019", "01", ABC.Name, startDate1, endDate1, activityList1, Main.employeeList);

        //Projekt2:
        DateType startDate2 = new DateType("05/05/2019");
        DateType endDate2 = new DateType("12/05/2019");
        String Name2 = "Project2";
        ArrayList<PSA> activityList2 = new ArrayList<PSA>(Arrays.asList(Activity2));

        Project project2 = new Project(Name2, "2019", "02", DEF.Name, startDate2, endDate2, activityList2, Main.employeeList);

        // Tilføjer projekter til projektlisten
        Main.projectList.add(project1);
        Main.projectList.add(project2);

    }
}

