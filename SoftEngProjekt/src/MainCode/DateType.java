
public class DateType {

	private String date;

	public DateType (String date) {

		this.date=date;

	}

	public static boolean dateChecker(String date) {

		boolean startdate = false;

		// defines our three start-date integers
		int day = Integer.parseInt(date.split("/")[0]);
		int month = Integer.parseInt(date.split("/")[1]);

		// Checks if the start-date is right
		if (day > 31) {
			startdate = false;
		} else if (month > 12) {
			startdate = false;
		} else if (date.matches("^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{4}+$")) {
			startdate = true;
		} else {
			startdate = false;
		}

		if (startdate == true) {
			return true;
		} else {
			return false;
		}
	}

	public int getDay() {
		int day = Integer.parseInt(date.split("/")[0]);
		return day;
	}

	public int getMonth() {
		int month = Integer.parseInt(date.split("/")[1]);
		return month;
	}

	public int getYear() {
		int year = Integer.parseInt(date.split("/")[2]);
		return year;
	}

	public String toString(){
		return date;
	}

	public static boolean validDate(DateType sDate, DateType eDate) {
		//Checks if end-date is later than start-date
		if (sDate.getYear() > eDate.getYear()) {
			return false;
		}

		else if (sDate.getYear() == eDate.getYear() && sDate.getMonth() > eDate.getMonth()) {
			return false;
		}

		else if (sDate.getYear() == eDate.getYear() && sDate.getMonth() == eDate.getMonth() && sDate.getDay() > eDate.getDay()) {
			return false;
		}
		return true;
	}
}
