package main;
import util.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        String firstName = "", lastName = "";
        int pantherID = 0;
        String userInput = "";
        ArrayList<Student> studentList = new ArrayList<>();

        int parsedInt;
        Pattern p;
        Matcher m;

        // WELCOME MESSAGE
        System.out.print(
            "************************************************************************************\n"
            + "Welcome to my grade book!\n\n"
            + "Please enter the information of the first student using the following format:\n" 
            + "\"firstName lastName PID grade\".\n"
            + "Press Enter when you are done.\n\n"
        );

        // ADD STUDENTS UNTIL "DONE" IS INPUTTED
        while (true) {
            try {
                // READ STUDENTS
                userInput = input.nextLine();
                if (userInput.toUpperCase().compareTo("DONE") == 0) {
                    break;
                }
                String[] userInputSplit = userInput.split(" ");
                
                // ASSIGN FIRST NAME
                firstName = userInputSplit[0];
                if (Character.isLowerCase(firstName.charAt(0))) {
                    throw new Exception();
                }

                // ASSIGN LAST NAME
                lastName = userInputSplit[1];
                p = Pattern.compile("[a-zA-Z]+[.]?");
                m = p.matcher(lastName);
                if (Character.isLowerCase(lastName.charAt(0)) || !(m.matches())) {
                    throw new Exception();
                }
                
                // ASSIGN PANTHER ID
                if (userInputSplit[2].charAt(0) == '0' || userInputSplit[2].length() != 7) {
                    throw new Exception();
                }
                parsedInt = Integer.parseInt(userInputSplit[2]);
                pantherID = parsedInt;
                
                // ASSIGN GRADE SCORE
                parsedInt = Integer.parseInt(userInputSplit[3]);
                if (parsedInt < 0 || parsedInt > 100) {
                    throw new Exception();
                }
                
                // ADD STUDENT TO LIST
                studentList.add(new Student(firstName, lastName, pantherID, new Grade(parsedInt)));
                
            }
            // CATCH ERRORS FROM USERINPUT
            catch (Exception e) {
                System.out.print(
                    "\nEntered information wrong!\n"
                    + "Correct format is:\n"
                    + "\"firstName lastName PID grade\"\n"
                    );
                }
            // PRINT OUT MENU UNTIL USER IS INPUTS "DONE"
            System.out.print(
                "\n************************************************************************************\n"
                + "Please enter the information of the next student using the same format.\n"
                + "If there is no more students, please enter the keyword \"DONE\".\n"
                + "Press Enter when you are done.\n\n"
            );
        }

        // ADD STUDENTS TO GRADEBOOK
        Gradebook gradebook = new Gradebook(studentList);

        
        String userChoice = "";

        // ENTER A DO-WHILE LOOP FOR USER INPUT ON GRADEBOOK METHODS
        do {
            System.out.print(
                "\n************************************************************************************\n"
                + "\nPlease enter a new command:\n\n"
                + "- Type \"max score\" to retrieve the highest score\n"
                + "- Type \"min score\" to retrieve the lowest score\n"
                + "- Type \"max letter\" to retrieve the highest letter grade\n"
                + "- Type \"min letter\" to retrieve the lowest letter grade\n"
                + "- Type \"find student grade\" to retrieve the student's letter grade\n"
                + "- Type \"find student name\" to retrieve the student's full name\n"
                + "- Type \"change grade\" to find and change student's letter grade\n"
                + "- Type \"average score\" to retrieve the average score of all students\n"
                + "- Type \"average letter\" to retrieve the average letter grade of all students\n"
                + "- Type \"median score\" to retrieve the median score of all students\n"
                + "- Type \"median letter\" to retrieve the median letter grade of all students\n"
                + "- Type \"tab scores\" to print students with scores in a tab-separated table\n"
                + "- Type \"tab letter\" to print students with letter grades in a tab-separated table\n"
                + "- Type \"quit\" to quit the program\n\n"
            );

            // USER ENTERS THEIR CHOICE
            System.out.print("Enter your choice: ");
            userChoice = input.nextLine();

            // EXECUTE GRADEBOOK METHOD BASED ON USERCHOICE
            switch (userChoice) {
                case ("max score"):
                    gradebook.getMaxScore();
                    break;
                case ("min score"):
                    gradebook.getMinScore();
                    break;
                case ("max letter"):
                    gradebook.getMaxLetter();
                    break;
                case ("min letter"):
                    gradebook.getMinLetter();
                    break;
                case ("find student grade"):
                    System.out.println("\nPlease input the pantherID of the student\n");
                    userInput = input.nextLine();
                    gradebook.findStudentLetterGrade(Integer.parseInt(userInput));
                    break;
                case ("find student name"):
                    System.out.println("\nPlease input the pantherID of the student\n");
                    userInput = input.nextLine();
                    gradebook.findStudentName(Integer.parseInt(userInput));
                    break;
                case ("change grade"):
                    System.out.println("\nPlease input the pantherID and grade you wish to change to in the format \"pantherID (0-100)\"\n");
                    userInput = input.nextLine();
                    gradebook.changeGrade(Integer.parseInt(userInput.split(" ")[0]), Integer.parseInt(userInput.split(" ")[1]));
                    break;
                case ("average score"):
                    gradebook.getAvgScore();
                    break;
                case ("average letter"):
                    gradebook.getAvgLetter();
                    break;
                case ("median score"):
                    gradebook.getMedianScore();
                    break;
                case ("median letter"):
                    gradebook.getMedianLetter();
                    break;
                case ("tab scores"):
                    gradebook.printAllStudentsScores();
                    System.out.println();
                    break;
                case ("tab letter"):
                    gradebook.printAllStudentsLetterGrade();
                    System.out.println();
                    break;
                case ("quit"):
                    System.out.println("\nClosing Gradebook...\n");
                    break;
                default:
                    System.out.print("\nTyped in wrong command, please try again!\n");
                    break;
            }
        } while (!userChoice.equals("quit"));

        input.close();
    }

}
