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

        
        int userChoice = 0;
        do {
            System.out.print(
                "\n************************************************************************************\n"
                + "\nPlease enter a new command:\n\n"
                + "(1) Max Score\n"
                + "(2) Min Score\n"
                + "(3) Max Letter Grade\n"
                + "(4) Min Letter Grade\n"
                + "(5) Find students letter grade with pantherID\n"
                + "(6) Find students name with pantherID\n"
                + "(7) Change student's grade\n"
                + "(8) Average Score\n"
                + "(9) Average Letter Grade\n"
                + "(10) Median Score\n"
                + "(11) Median Letter Grade\n"
                + "(12) Print students with scores in a tab-separated table\n"
                + "(13) Print students with letter grades in a tab-separated table\n"
                + "(14) Quit the program\n\n"
            );
            try {

                // USER ENTERS THEIR CHOICE
                System.out.print("Enter your choice: ");
                userChoice = input.nextInt();

                // EXECUTE GRADEBOOK METHOD BASED ON USERCHOICE
                switch (userChoice) {
                    case 1:
                        gradebook.getMaxScore();
                        break;
                    case 2:
                        gradebook.getMinScore();
                        break;
                    case 3:
                        gradebook.getMaxLetter();
                        break;
                    case 4:
                        gradebook.getMinLetter();
                        break;
                    case 5:
                        System.out.println("\nPlease input the pantherID of the student\n");
                        userInput = input.next();
                        gradebook.findStudentLetterGrade(Integer.parseInt(userInput));
                        break;
                    case 6:
                        System.out.println("\nPlease input the pantherID of the student\n");
                        userInput = input.next();
                        gradebook.findStudentName(Integer.parseInt(userInput));
                        break;
                    case 7:
                        System.out.println("\nPlease input the pantherID and grade you wish to change to in the format \"pantherID (0-100)\"\n");
                        input.nextLine();
                        userInput = input.nextLine();
                        gradebook.changeGrade(Integer.parseInt(userInput.split(" ")[0]), Integer.parseInt(userInput.split(" ")[1]));
                        break;
                    case 8:
                        gradebook.getAvgScore();
                        break;
                    case 9:
                        gradebook.getAvgLetter();
                        break;
                    case 10:
                        gradebook.getMedianScore();
                        break;
                    case 11:
                        gradebook.getMedianLetter();
                        break;
                    case 12:
                        gradebook.printAllStudentsScores();
                        System.out.println();
                        break;
                    case 13:
                        gradebook.printAllStudentsLetterGrade();
                        System.out.println();
                        break;
                    case 14:
                        System.out.println("\nClosing Gradebook...\n");
                        break;
                }
            } 
            // CATCH ERRORS FROM USERCHOICE INPUT
            catch(Exception e) {
                System.out.println("\nYou entered the information incorrectly!\n");
            }
        } while (userChoice != 14);

        input.close();
    }

}
