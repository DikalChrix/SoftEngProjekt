
public class DateType {

	private String date;

	public DateType (String date) {

		this.date=date;
		datechecker();

	}

	public boolean datechecker() {

		boolean startdate = false;

		// defines our three start-date integers
		int day = Integer.parseInt(date.split("/")[0]);
		int month = Integer.parseInt(date.split("/")[1]);
		int year = Integer.parseInt(date.split("/")[2]);

		// Checks if the start-date is right
		if (day > 31) {
			System.out.println("Incorrect date, try again");
		} else if (month > 12) {
			System.out.println("Incorrect date, try again");
		} else if (date.matches("^[0-3]{1}+[0-9]{1}+[/]{1}+[0-1]{1}+[0-9]{1}+[/]{1}+[0-9]{1}+[0-9]{1}+$")) {
			startdate = true;
		} else {
			System.out.println("Incorrect date, try again");
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

	public String toString()
	{
		return date;
	}
}
