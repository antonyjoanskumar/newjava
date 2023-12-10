import java.util.Calendar;
import java.util.Date;

public class sunday {
    public static void main(String[] args) {
        // Set the start date to '3/4/2023'
        Calendar startDate = Calendar.getInstance();
        startDate.set(2023, 9, 19); // Month is 0-based (0 = January, 1 = February, etc.)

        // Get the current date
        Calendar currentDate = Calendar.getInstance();

        // Count the number of Sundays between the two dates
        int sundayCount = countSundays(startDate, currentDate);

        System.out.println("Number of Sundays between '3/4/2023' and current date: " + sundayCount);
    }

    public static int countSundays(Calendar startDate, Calendar endDate) {
        int sundayCount = 0;

        while (!startDate.after(endDate)) {
            if (startDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                sundayCount++;
            }
            startDate.add(Calendar.DAY_OF_MONTH, 1);
        }

        return sundayCount;
    }
}
