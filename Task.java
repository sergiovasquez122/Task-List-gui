import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Task Class - representation of a single Task
 * @author Sergio Vasquez
 */
public class Task implements Comparable<Task> {

    /**
     * The Date of the dueDate
     */
    private Date dueDate;
    /**
     * The taskName
     */
    private String taskName;

    /** The format of a valid date */
    public static String DATE_PATTERN = "MM/dd/yyyy HH:mm";

    /**
     * Constructor - constructs Task with name n and Date d
     * @param n The name of the new Task
     * @param d the date of the new Task
     */
    public Task(String n, Date d) {
        this.taskName = n;
        this.dueDate = d;
    }

    /**
     * The date of the task
     * @return the date of the task
     */
    public Date getDate() {
        return dueDate;
    }

    /**
     * The name of the task
     * @return the name of the task
     */
    public String getName() {
        return taskName;
    }

    /**
     * Compares to task first by date and if equal then by name
     * @param other the other task to be compared to
     * @return negative number if inorder, zero if equal and a positive number if unordered
     */
    @Override
    public int compareTo(Task other) {
        // Compare by date and then string if date were equal
        if (dueDate.compareTo(other.dueDate) == 0) {
            return taskName.compareTo(other.taskName);
        } else {
            return dueDate.compareTo(other.dueDate);
        }
    }

    /**
     * Returns a string representation of the task in the form
     * @return string representation fo the task
     */
    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Task.DATE_PATTERN);
        String dateValue = simpleDateFormat.format(dueDate);
        return taskName + "," + dateValue;
    }
}