/* This is part of the starter code! 
 * You need to complete this class yourself!*/
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

        System.out.print(
            "Welcome to my grade book!\n"
            + "Please enter the information of the first student using the following format:\n" 
            + "\"firstName lastName PID grade\".\n"
            + "Press Enter when you are done.\n\n"
        );

        while (true) {
            try {
                userInput = input.nextLine();
                if (userInput.compareTo("DONE") == 0) {
                    break;
                }
                String[] userInputSplit = userInput.split(" ");
                
                firstName = userInputSplit[0];
                if (Character.isLowerCase(firstName.charAt(0))) {
                    throw new Exception();
                }

                lastName = userInputSplit[1];
                p = Pattern.compile("[a-zA-Z]+[.]{2,}[0-9]?");
                m = p.matcher(lastName);
                if (Character.isLowerCase(lastName.charAt(0)) || m.lookingAt()) {
                    throw new Exception();
                }
                
                if (userInputSplit[2].charAt(0) == '0' || userInputSplit[2].length() != 7) {
                    throw new Exception();
                }
                parsedInt = Integer.parseInt(userInputSplit[2]);
                pantherID = parsedInt;
                
                parsedInt = Integer.parseInt(userInputSplit[3]);
                if (parsedInt < 0 || parsedInt > 100) {
                    throw new Exception();
                }
                
                studentList.add(new Student(firstName, lastName, pantherID, new Grade(parsedInt)));
                
            } 
            catch (Exception e) {
                System.out.printf(
                    "Entered information wrong!\n"
                    + "Correct format is:\n"
                    + "\"firstName lastName PID grade\"\n\n"
                    );
                }
            System.out.printf(
                "\nPlease enter the information of the next student using the same format.\n"
                + "If there is no more students, please enter the keyword DONE.\n"
                + "Press Enter when you are done.\n\n"
            );
        }

        Gradebook gradebook = new Gradebook(studentList);
        
        gradebook.calculateAvgLetter();
        gradebook.calculateAvgScore();
        gradebook.calculateMedianLetter();
        gradebook.calculateMedianScore();
        gradebook.findStudentLetterGrade(6337381);
        gradebook.findStudentName(6337381);
        gradebook.getMaxLetter();
        gradebook.getMaxScore();
        gradebook.getMinLetter();
        gradebook.getMinScore();
        gradebook.printAllStudentsLetterGrade();
        gradebook.printAllStudentsScores();

        input.close();
    }

}
