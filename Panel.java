import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Panel Class - Representation of single instance of a panel
 * @author Sergio Vasquez
 */
public class Panel extends JPanel implements ActionListener {
    /**
     * Label that corresponds to the task's left
     */
    private JLabel taskLeftLabel;
    /**
     * Label that corresponds to the current task
     */
    private JLabel currentTaskLabel;
    /**
     * Label that corresponds to the task's due-date
     */
    private JLabel completeByLabel;
    /**
     * Label that corresponds to the task's current add or post state
     */
    private JLabel addOrPostLabel;
    /**
     * Label that corresponds to modifying the date
     */
    private JLabel dateLabel;
    /**
     * Label that corresponding to modifying the time
     */
    private JLabel timeLabel;
    /**
     * Label that correspond to modifying the task
     */
    private JLabel taskLabel;

    /**
     * Display the number of tasks left
     */
    private JTextField taskLeftText;
    /**
     * Display the current task to be completed
     */
    private JTextField currentTaskText;
    /**
     * Display the dueDate of the task
     */
    private JTextField completeByText;
    /**
     * Display the month user is inputting
     */
    private JTextField monthText;
    /**
     * Display the day user is inputting
     */
    private JTextField dayText;
    /**
     * Display the year user is inputting
     */
    private JTextField yearText;
    /**
     * Display the hour user is inputting
     */
    private JTextField hourText;
    /**
     * Display the minute user is inputting
     */
    private JTextField minuteText;
    /**
     * Display the task user is inputting
     */
    private JTextField taskText;

    /**
     * Button that will control complete functionality
     */
    private JButton completedButton;
    /**
     * Button that will control posting or adding task functionality
     */
    private JButton postOrAddButton;
    /**
     * Button that will control quit functionality
     */
    private JButton quitButton;
    /**
     * Button that will control submit functionality
     */
    private JButton submitButton;

    /**
     * String that will represent state of the postOrAddButton
     */
    private String postOrAddButtonState;

    /**
     * list of tasks
     */
    private Heap<Task> tasklist;

    /** The filename that the tasklist reads from */
    private String fileName = "taskList.txt";

    /**
     * Constructor - Creates single instance of Panel
     */
    public Panel() {

        setLayout(new FlowLayout(FlowLayout.CENTER));

        final int SMALL_TEXT_FIELDS = 5;
        final int LARGE_TEXT_FIELDS = 20;

        taskLeftLabel = new JLabel("Tasks Left:");
        taskLeftText = new JTextField(SMALL_TEXT_FIELDS);

        currentTaskLabel = new JLabel("Current Task:");
        currentTaskText = new JTextField(LARGE_TEXT_FIELDS);

        completeByLabel = new JLabel("Current Task:");
        completeByText = new JTextField(LARGE_TEXT_FIELDS);

        completedButton = new JButton("Completed!");
        postOrAddButton = new JButton("Postpone");

        addOrPostLabel = new JLabel("Add Task:");
        dateLabel = new JLabel("Date:");
        monthText = new JTextField("MM", SMALL_TEXT_FIELDS);
        dayText = new JTextField("DD", SMALL_TEXT_FIELDS);
        yearText = new JTextField("YYYY", SMALL_TEXT_FIELDS);

        timeLabel = new JLabel("Time:");
        hourText = new JTextField(SMALL_TEXT_FIELDS);
        minuteText = new JTextField(SMALL_TEXT_FIELDS);

        taskLabel = new JLabel("Task:");
        taskText = new JTextField(LARGE_TEXT_FIELDS);

        submitButton = new JButton("Submit");
        quitButton = new JButton("Quit");

        submitButton.addActionListener(this);
        quitButton.addActionListener(this);
        completedButton.addActionListener(this);
        postOrAddButton.addActionListener(this);
        add(taskLeftLabel);
        add(taskLeftText);
        add(currentTaskLabel);
        add(currentTaskText);
        add(completeByLabel);
        add(completeByText);
        add(completedButton);
        add(postOrAddButton);
        add(addOrPostLabel);
        add(dateLabel);
        add(monthText);
        add(dayText);
        add(yearText);
        add(timeLabel);
        add(hourText);
        add(minuteText);
        add(taskLabel);
        add(taskText);
        add(submitButton);
        add(quitButton);

        postOrAddButtonState = "add";


        loadFile(new File(fileName));
        displayInfo();
    }

    /**
     * Perform various tasks GUI given ActionEvent
     * @param e the ActionEvent that occurred
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == completedButton) {
            if (!tasklist.isEmpty()) {
                tasklist.removeItem();
            }

            taskLeftText.setText(Integer.toString(tasklist.size()));

            // Switch to add task mode if we have no more tasks or we deleted task that we were postponing
            if (tasklist.isEmpty() || postOrAddButtonState.equals("post")) {
                postOrAddButton.setText("Postpone");
                postOrAddButtonState = "add";
                taskLabel.setVisible(true);
                taskText.setVisible(true);
                addOrPostLabel.setText("Add Task:");
            }
            displayInfo();
        } else if (e.getSource() == quitButton) {
            writeFile(new File(fileName));
            System.exit(0);
        } else if (e.getSource() == submitButton) {
            if (postOrAddButtonState.equals("add")) {
                // Extract all the values of the TextField
                // Check if the fields are valid(non-empty and appropriate dates)
                // Create Date and call add task to the TaskList
                String taskName = taskText.getText();
                String taskMonth = monthText.getText();
                String taskDay = dayText.getText();
                String taskYear = yearText.getText();
                String taskHour = hourText.getText();
                String taskMinute = minuteText.getText();
                if (!taskName.isEmpty() && !taskMonth.isEmpty() && !taskDay.isEmpty() && !taskYear.isEmpty()) {

                    String dateContent = taskMonth + "/" + taskDay + "/" + taskYear + " " + taskHour + ":" + taskMinute;

                    Date date = CheckInput.validDate(dateContent, Task.DATE_PATTERN);

                    if (date != null) {
                        tasklist.addItem(new Task(taskName, date));
                        displayInfo();
                    }
                }
            } else {
                // Extract all the values of the TextField
                // Check if the fields are valid(non-empty and appropriate dates)
                // Create Date and call postpone method to set the current date to the new one
                String taskMonth = monthText.getText();
                String taskDay = dayText.getText();
                String taskYear = yearText.getText();
                String taskHour = hourText.getText();
                String taskMinute = minuteText.getText();
                if (!taskMonth.isEmpty() && !taskDay.isEmpty() && !taskYear.isEmpty()) {
                    String dateContent = taskMonth + "/" + taskDay + "/" + taskYear + " " + taskHour + ":" + taskMinute;

                    Date date = CheckInput.validDate(dateContent, Task.DATE_PATTERN);

                    if (date != null) {
                        Task t = tasklist.removeItem();
                        tasklist.addItem(new Task(t.getName(), date));
                        displayInfo();
                    }
                }
            }
        } else if (e.getSource() == postOrAddButton && postOrAddButtonState.equals("post")) {
            postOrAddButton.setText("Postpone");
            postOrAddButtonState = "add";
            taskLabel.setVisible(true);
            taskText.setVisible(true);
            addOrPostLabel.setText("Add Task:");
            displayInfo();
        } else if (e.getSource() == postOrAddButton && postOrAddButtonState.equals("add") && !tasklist.isEmpty()) {
            postOrAddButton.setText("Add Task");
            postOrAddButtonState = "post";
            taskLabel.setVisible(false);
            taskText.setVisible(false);
            addOrPostLabel.setText("Postpone Task:");
            displayInfo();
        }
    }

    /**
     * Displays all relevant information to the user
     */
    private void displayInfo() {
        taskLeftText.setText(Integer.toString(tasklist.size()));

        String taskName = "";
        String dateString = "";
        if(!tasklist.isEmpty()){
            Task task = tasklist.getCurrent();
            taskName = task.getName();
            dateString = new SimpleDateFormat(Task.DATE_PATTERN).format(task.getDate());
        }

        currentTaskText.setText(taskName);
        completeByText.setText(dateString);

        taskText.setText("New Task");
        monthText.setText("MM");
        dayText.setText("DD");
        yearText.setText("YYYY");
        hourText.setText("HH");
        minuteText.setText("MM");
    }

    /**
     * Writes the content of the TaskList
     * @param f the file to write the content of the TaskList
     */
    private void writeFile(File f) {
        try {
            PrintWriter writer = new PrintWriter(f);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Task.DATE_PATTERN);
            while (!tasklist.isEmpty()) {
                Task t = tasklist.removeItem();
                String taskName = t.getName();
                String date = simpleDateFormat.format(t.getDate());
                writer.println(taskName + "," + date);
            }
            writer.close();
        } catch (FileNotFoundException fnf) {
            System.out.println("File was not found");
        }
    }

    /**
     * Loads tasks into the TaskList
     * @param f the file to to the TaskList
     */
    private void loadFile(File f) {
        try {
            tasklist = new Heap<>();
            Scanner read = new Scanner(f);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Task.DATE_PATTERN);
            while (read.hasNextLine()) {
                // valid task name and date information is separated by ','
                String tokens[] = read.nextLine().split(",");
                String task_name = tokens[0];
                String date_value = tokens[1];
                Date date = simpleDateFormat.parse(date_value);
                tasklist.addItem(new Task(task_name, date));
            }
        } catch (FileNotFoundException fnf) {
            System.out.println("File was not found");
        } catch (ParseException e) {
            System.out.println("File format was incorrect");
        }
    }
}
