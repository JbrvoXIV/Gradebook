package util;

import java.util.*;
import java.util.stream.Collectors;

public class Gradebook {
	
	// CLASS ARRAYLIST TO STORE ALL STUDENTS
    private ArrayList<Student> listOfStudents = new ArrayList<>();

	// CONSTRUCTOR
	public Gradebook(ArrayList<Student> studentList) {
		this.listOfStudents = studentList;
	}

	// PRINT OUT MAX SCORE OF ALL STUDENTS
	public void getMaxScore() {
		ArrayList<Integer> scores = new ArrayList<>();

		listOfStudents.stream().forEach(s -> scores.add(s.getGrade().getScore()));

		Collections.sort(scores);
		System.out.println("\nMAX SCORE: " + scores.get(scores.size() - 1));
	}
	
	// PRINT MIN SCORE OF ALL STUDENTS
	public void getMinScore() {
		ArrayList<Integer> scores = new ArrayList<>();
		for (Student s : this.listOfStudents) {
			scores.add(s.getGrade().getScore());
		}
		Collections.sort(scores);
		System.out.println("\nMAX SCORE: " + scores.get(0));
	}

	// PRINT MAX LETTER GRADE OF ALL STUDENTS
	public void getMaxLetter() {
		ArrayList<String> gradeLetters = new ArrayList<>();
		for (Student s : this.listOfStudents) {
			gradeLetters.add(s.getGrade().getLetterGrade());
		}
		gradeLetters.stream().sorted().collect(Collectors.toList());
		System.out.println("\nMAX LETTER GRADE: " + gradeLetters.get(0));
	}

	// PRINT MIN LETTER GRADE OF ALL STUDENTS
	public void getMinLetter() {
		ArrayList<String> gradeLetters = new ArrayList<>();
		for (Student s : this.listOfStudents) {
			gradeLetters.add(s.getGrade().getLetterGrade());
		}
		gradeLetters.stream().sorted().collect(Collectors.toList());
		System.out.println("\nMIN LETTER GRADE: " + gradeLetters.get(gradeLetters.size() - 1));
	}

	// FIND AND PRINT LETTER GRADE OF STUDENT THROUGH PANTHERID
	public void findStudentLetterGrade(int PID) {
		this.listOfStudents.stream()
							.filter(s -> s.getPid() == PID)
							.forEach(s -> System.out.println("\nSTUDENT LETTER GRADE: " + s.getGrade().getLetterGrade()));
	}

	// FIND AND PRINT FULL NAME OF STUDENT THROUGH PANTHERID
	public void findStudentName(int PID) {
		this.listOfStudents.stream()
							.filter(s -> s.getPid() == PID)
							.forEach(s -> System.out.println("\nSTUDENT NAME: " + s.getFirstName() + " " + s.getLastName()));
	}

	// CHANGE THE GRADE OF AN EXISTING STUDENT
	public void changeGrade(int PID, int newScore) {
		this.listOfStudents.stream()
							.filter(s -> s.getPid() == PID)
							.forEach(s -> s.getGrade().setScore(newScore));
	}

	// PRINT THE AVERAGE SCORE OF ALL STUDENT GRADES
	public void getAvgScore() {
		double sum = 0;
		for(Student s: listOfStudents)
			sum += s.getGrade().getScore();
		System.out.println("\nAVERAGE SCORE: " + sum / listOfStudents.size());
	}
	
	// PRINT THE AVERAGE LETTER SCORE OF ALL STUDENT GRADES
	public void getAvgLetter() {
		List<Integer> scores = this.listOfStudents.stream()
														.map(s -> s.getGrade().getScore())
														.collect(Collectors.toList());
		double avgScore = scores.stream().mapToInt(Integer::intValue).sum() / this.listOfStudents.size();

		System.out.println("\nAVERAGE LETTER GRADE: " + calculateLetter(avgScore));
	}

	// PRINT THE MEDIAN SCORE OF ALL STUDENT GRADES
    public void getMedianScore() {
		int i = 0, n = listOfStudents.size();
		int[] scores = new int[n];
		for(Student s: listOfStudents)
			scores[i++] = s.getGrade().getScore();
		Arrays.sort(scores);
		if (n % 2 == 0)
			 System.out.println("\nMEDIAN SCORE: " + (scores[n / 2] + scores[n / 2 - 1]) / 2.0);
		else
			System.out.println("\nMEDIAN SCORE: " + scores[n / 2]);
    }

	// PRINT THE MEDIAN LETTER GRADE OF ALL STUDENT GRADES
	public void getMedianLetter() {
		int i = 0, n = listOfStudents.size();
		int[] scores = new int[n];
		double medianScores;

		for(Student s: listOfStudents)
			scores[i++] = s.getGrade().getScore();
		Arrays.sort(scores);
		if (n % 2 == 0)
			medianScores = (scores[n / 2] + scores[n / 2 - 1]) / 2.0;
		else
			medianScores = scores[n / 2];

		System.out.println("\nMEDIAN LETTER GRADE: " + calculateLetter(medianScores));
    }
	
	// PRINT ALL STUDENTS WITH RESPECTIVE GRADE SCORES IN TAB-SPACED GRAPH
	public void printAllStudentsScores() {
		this.listOfStudents.stream()
			.forEach(s -> System.out.printf("\n%s\t%s\t%d\t%d", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore()));
	}

	// PRINT ALL STUDENTS WITH RESPECTIVE LETTER GRADES IN TAB-SPACED GRAPH
	public void printAllStudentsLetterGrade() {
		this.listOfStudents.stream()
			.forEach(s -> System.out.printf("\n%s\t%s\t%d\t%s", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade()));
	}

	// METHOD TO CALCULATE LETTER GRADE WITH GRADE SCORE PASSED AS A PARAMETER - USED BY GETAVERAGELETTER() AND GETMEDIANLETTER()
	private String calculateLetter(double num) {

		if (num >= 90) {
			return "A";
		} else if (num >= 85 && num <= 89) {
			return "A-";
		} else if (num >= 80 && num <= 84) {
			return "B+";
		} else if (num >= 75 && num <= 79) {
			return "B";
		} else if (num >= 70 && num <= 74) {
			return "B-";
		} else if (num >= 65 && num <= 69) {
			return "C+";
		} else if (num >= 60 && num <= 64) {
			return "C";
		} else if (num >= 50 && num <= 59) {
			return "D";
		} else {
			return "F";
		}	
	}
}
