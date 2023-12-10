import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class sunday1 {
    public static void main(String[] args) {

        String startDateString = "10/19/2023";

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

        try {
            Date startDate = dateFormat.parse(startDateString);

            int sundayCount = countSundays(startDate);
            System.out.println("Number of Sundays between " + startDateString + " and the current date: " + sundayCount);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy format.");
        }
    }

    public static int countSundays(Date startDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar currentDate = Calendar.getInstance();
        int sundayCount = 0;

        while (!startCal.after(currentDate)) {
            if (startCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundayCount++;
            }
            startCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return sundayCount;
    }
}
