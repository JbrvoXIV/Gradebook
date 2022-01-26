/* This is part of the starter code! 
 * You need to complete this class yourself!*/
package util;

import java.util.*;
import java.util.stream.Collectors;

public class Gradebook {
    private ArrayList<Student> listOfStudents = new ArrayList<>();

	public Gradebook(ArrayList<Student> studentList) {
		this.listOfStudents = studentList;
	}

	public void getMaxScore() {
		ArrayList<Integer> scores = new ArrayList<>();

		listOfStudents.stream().forEach(s -> scores.add(s.getGrade().getScore()));

		Collections.sort(scores);
		System.out.println("\nMAX SCORE: " + scores.get(scores.size() - 1));
	}

	public void getMinScore() {
		ArrayList<Integer> scores = new ArrayList<>();
		for (Student s : this.listOfStudents) {
			scores.add(s.getGrade().getScore());
		}
		Collections.sort(scores);
		System.out.println("\nMAX SCORE: " + scores.get(0));
	}

	public void getMaxLetter() {
		ArrayList<String> gradeLetters = new ArrayList<>();
		for (Student s : this.listOfStudents) {
			gradeLetters.add(s.getGrade().getLetterGrade());
		}
		gradeLetters.stream().sorted().collect(Collectors.toList());
		System.out.println("\nMAX LETTER GRADE: " + gradeLetters.get(0));
	}

	public void getMinLetter() {
		ArrayList<String> gradeLetters = new ArrayList<>();
		for (Student s : this.listOfStudents) {
			gradeLetters.add(s.getGrade().getLetterGrade());
		}
		gradeLetters.stream().sorted().collect(Collectors.toList());
		System.out.println("\nMIN LETTER GRADE: " + gradeLetters.get(gradeLetters.size() - 1));
	}

	public void findStudentLetterGrade(int PID) {
		this.listOfStudents.stream()
							.filter(s -> s.getPid() == PID)
							.forEach(s -> System.out.println("\nSTUDENT LETTER GRADE: " + s.getGrade().getLetterGrade()));
	}

	public void findStudentName(int PID) {
		this.listOfStudents.stream()
							.filter(s -> s.getPid() == PID)
							.forEach(s -> System.out.println("\nSTUDENT NAME: " + s.getFirstName() + " " + s.getLastName()));
	}

	public void changeGrade(int PID, int newScore) {
		this.listOfStudents.stream()
							.filter(s -> s.getPid() == PID)
							.forEach(s -> s.getGrade().setScore(newScore));
	}

	public void getAvgScore() {
		double sum = 0;
		for(Student s: listOfStudents)
			sum += s.getGrade().getScore();
		System.out.println("\nAVERAGE SCORE: " + sum / listOfStudents.size());
	}
	
	public void getAvgLetter() {
		List<Integer> scores = this.listOfStudents.stream()
														.map(s -> s.getGrade().getScore())
														.collect(Collectors.toList());
		double avgScore = scores.stream().mapToInt(Integer::intValue).sum() / this.listOfStudents.size();

		System.out.println("\nAVERAGE LETTER GRADE: " + calculateLetter(avgScore));
	}

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
	
	public void printAllStudentsScores() {
		this.listOfStudents.stream()
			.forEach(s -> System.out.printf("\n%s\t%s\t%d\t%d", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getScore()));
	}

	public void printAllStudentsLetterGrade() {
		this.listOfStudents.stream()
			.forEach(s -> System.out.printf("\n%s\t%s\t%d\t%s", s.getFirstName(), s.getLastName(), s.getPid(), s.getGrade().getLetterGrade()));
	}

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
