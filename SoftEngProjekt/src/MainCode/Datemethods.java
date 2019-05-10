
public class Datemethods {

	public static boolean validDate(DateType sDate, DateType eDate) {
		//Checks if end-date is later than start-date
		if (sDate.getYear() > eDate.getYear()) {
			System.out.println("Incorrect end-date, try again");
			return false;
		}

		else if (sDate.getYear() == eDate.getYear() && sDate.getMonth() > eDate.getMonth()) {
			System.out.println("Incorrect end-date, try again");
			return false;
		}

		else if (sDate.getYear() == eDate.getYear() && sDate.getMonth() == eDate.getMonth() && sDate.getDay() > eDate.getDay()) {
			System.out.println("Incorrect end-date, try again");
			return false;
		}
		return true;
	}
}
