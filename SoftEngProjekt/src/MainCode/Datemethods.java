
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

	public static boolean dateDiff(DateType sDate, DateType eDate) {

		//Runs through all possible scenarios and prints out a ETA project finish
		if (sDate.getYear() == eDate.getYear() && sDate.getMonth() == eDate.getMonth()) {
			System.out.println("Total time to complete the project: " + ((eDate.getDay()) - (sDate.getDay())) + " day(s)");
		} else if (sDate.getYear() == eDate.getYear()) {

			if (((eDate.getMonth()) - (sDate.getMonth())) == 1 && eDate.getDay() <= sDate.getDay()) {
				System.out.println(
						"Total time to complete the project: " + ((31) - (sDate.getDay()) + (eDate.getDay())) + " day(s)");
			}

			else if (((eDate.getMonth()) - (sDate.getMonth())) == 1 && eDate.getDay() > sDate.getDay()) {
				System.out.println("Total time to complete the project: 1 month and "
						+ ((((31) - (sDate.getDay()) + (eDate.getDay()))) - (31)) + " day(s)");
			}

			else if (((eDate.getMonth()) - (sDate.getMonth())) != 1) {

				if (eDate.getDay() < sDate.getDay()) {
					System.out
							.println("Total time to complete the project: " + ((((eDate.getMonth()) - (sDate.getMonth()))) - (1))
									+ " month(s) and " + ((31) - (sDate.getDay()) + (eDate.getDay())) + " day(s)");
				} else if (eDate.getDay() > sDate.getDay()) {
					System.out.println("Total time to complete the project: " + ((eDate.getMonth()) - (sDate.getMonth()))
							+ " months and " + ((eDate.getDay()) - (sDate.getDay())) + " day(s)");
				} else if (eDate.getDay() == sDate.getDay()) {
					System.out.println(
							"Total time to complete the project: " + ((eDate.getMonth()) - (sDate.getMonth())) + " months");
				}
			}
		} else {
			int year = eDate.getYear() - sDate.getYear();
			if (year == 1 && eDate.getMonth() == sDate.getMonth() && sDate.getDay() < eDate.getDay()) {
				System.out.println(
						"Total time to complete the project: 1 year and " + ((eDate.getDay()) - (sDate.getDay())) + " day(s)");
			} else if (year == 1 && eDate.getMonth() == sDate.getMonth() && sDate.getDay() > eDate.getDay()) {
				System.out.println("Total time to complete the project: 11 months and " + ((sDate.getDay()) - (eDate.getDay()))
						+ " day(s)");
			} else if (year == 1 && eDate.getMonth() == sDate.getMonth() && sDate.getDay() == eDate.getDay()) {
				System.out.println("Total time to complete the project: 1 year");
			} else if (year == 1 && ((eDate.getMonth()) - (sDate.getMonth())) == 1 && eDate.getDay() <= sDate.getDay()) {
				System.out.println("Total time to complete the project: 1 year and "
						+ ((31) - (sDate.getDay()) + (eDate.getDay())) + " day(s)");
			} else if (year == 1 && ((eDate.getMonth()) - (sDate.getMonth())) == 1 && eDate.getDay() > sDate.getDay()) {
				System.out.println("Total time to complete the project: 1 year and 1 month and "
						+ ((((31) - (sDate.getDay()) + (eDate.getDay()))) - (31)) + " day(s)");
			} else if (year == 1 && eDate.getMonth() < sDate.getMonth() && sDate.getDay() == eDate.getDay()) {
				System.out.println(
						"Total time to complete the project: " + ((sDate.getMonth()) - (eDate.getMonth())) + " month(s)");
			} else if (year == 1 && eDate.getMonth() > sDate.getMonth() && sDate.getDay() == eDate.getDay()) {
				System.out.println("Total time to complete the project: 1 year and " + ((eDate.getMonth()) - (sDate.getMonth()))
						+ " month(s)");
			} else if (year == 1 && eDate.getMonth() < sDate.getMonth() && sDate.getDay() < eDate.getDay()) {
				System.out.println("Total time to complete the project: " + ((12) - (sDate.getMonth()) + (eDate.getMonth()))
						+ " month(s) and " + ((eDate.getDay()) - (sDate.getDay())) + " day(s)");
			} else if (year == 1 && eDate.getMonth() < sDate.getMonth() && sDate.getDay() > eDate.getDay()) {
				System.out.println("Total time to complete the project: " + ((11) - (sDate.getMonth()) + (eDate.getMonth()))
						+ " month(s) and " + ((31) - (sDate.getDay()) + (eDate.getDay())) + " day(s)");
			} else if (year == 1 && eDate.getMonth() > sDate.getMonth() && sDate.getDay() < eDate.getDay()) {
				System.out.println("Total time to complete the project: 1 year and " + ((eDate.getMonth()) - (sDate.getMonth()))
						+ " month(s) and " + ((eDate.getDay()) - (sDate.getDay())) + " day(s)");
			} else if (year == 1 && eDate.getMonth() > sDate.getMonth() && sDate.getDay() > eDate.getDay()) {
				System.out.println("Total time to complete the project: 1 year and "
						+ ((eDate.getMonth()) - (sDate.getMonth()) - (1)) + " month(s) and " + ((31) - (sDate.getDay()) + (eDate.getDay())) + " day(s)");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() == sDate.getMonth() && sDate.getDay() == eDate.getDay()) {
				System.out.println("Total time to complete the project: " + year + " year(s)");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() == sDate.getMonth() && sDate.getDay() < eDate.getDay()) {
				System.out.println("Total time to complete the project: " + year + " year(s) and "
						+ ((eDate.getDay()) - (sDate.getDay())) + " days");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() == sDate.getMonth() && sDate.getDay() > eDate.getDay()) {
				System.out.println("Total time to complete the project: " + ((year) - (1))
						+ " year(s) and 11 months and " + ((31) - (sDate.getDay()) + (eDate.getDay())) + " days");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() > sDate.getMonth() && sDate.getDay() == eDate.getDay()) {
				System.out.println("Total time to complete the project: " + year + " year(s) and "
						+ ((eDate.getMonth()) - (sDate.getMonth())) + " month(s)");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() < sDate.getMonth() && sDate.getDay() == eDate.getDay()) {
				System.out.println("Total time to complete the project: " + ((year) - (1)) + " year(s) and "
						+ ((12) - (sDate.getMonth()) + (eDate.getMonth())) + " month(s)");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() < sDate.getMonth() && sDate.getDay() < eDate.getDay()) {
				System.out.println("Total time to complete the project: " + ((year) - (1)) + " year(s) and "
						+ ((12) - (sDate.getMonth()) + (eDate.getMonth())) + " month(s) and " + ((eDate.getDay()) - (sDate.getDay())) + " days");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() < sDate.getMonth() && sDate.getDay() > eDate.getDay()) {
				System.out.println("Total time to complete the project: " + ((year) - (1)) + " year(s) and "
						+ ((12) - (sDate.getMonth()) + (eDate.getMonth()) - (1)) + " month(s) and " + ((31) - (sDate.getDay()) + (eDate.getDay())) + " days");
			} else if (sDate.getYear() < eDate.getYear() && ((eDate.getMonth()) - (sDate.getMonth())) == 1 && eDate.getDay() <= sDate.getDay()) {
				System.out.println("Total time to complete the project: " + year + " year(s) and "
						+ ((31) - (sDate.getDay()) + (eDate.getDay())) + " day(s)");
			} else if (sDate.getYear() < eDate.getYear() && ((eDate.getMonth()) - (sDate.getMonth())) == 1 && eDate.getDay() > sDate.getDay()) {
				System.out.println("Total time to complete the project: " + year
						+ " year(s) and 1 month and " + ((eDate.getDay()) - (sDate.getDay())) + " day(s)");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() > sDate.getMonth() && sDate.getDay() < eDate.getDay()) {
				System.out.println("Total time to complete the project: " + year + " year(s) and "
						+ ((eDate.getMonth()) - (sDate.getMonth())) + " month(s) and " + ((eDate.getDay()) - (sDate.getDay())) + " day(s)");
			} else if (sDate.getYear() < eDate.getYear() && eDate.getMonth() > sDate.getMonth() && sDate.getDay() > eDate.getDay()) {
				System.out.println("Total time to complete the project: " + year + " year(s) and "
						+ ((eDate.getMonth()) - (sDate.getMonth()) - (1)) + " month(s) and " + ((31) - (sDate.getDay()) + (eDate.getDay())) + " day(s)");
			}
		}
		return true;
	}
}
