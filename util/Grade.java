package util;

public class Grade {
    private int score;
    private String letterGrade;

    // CONSTRUCTOR
    public Grade (int score) {
        this.score = score;
        calculateLetterGrade(score);
    }

    // SETTERS AND GETTERS BELOW

    public int getScore() {
        return score;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public void setScore(int score) {
        this.score = score;
        calculateLetterGrade(score);
    }

    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
    }

    // CALCULATE LETTER GRADE BASED ON INITIAL SCORE INPUTTED BY USER
    private void calculateLetterGrade(int score) {
        if (score >= 90) {
            this.letterGrade = "A";
        } else if (score >= 85 && score <= 89) {
            this.letterGrade = "A-";
        } else if (score >= 80 && score <= 84) {
            this.letterGrade = "B+";
        } else if (score >= 75 && score <= 79) {
            this.letterGrade = "B";
        } else if (score >= 70 && score <= 74) {
            this.letterGrade = "B-";
        } else if (score >= 65 && score <= 69) {
            this.letterGrade = "C+";
        } else if (score >= 60 && score <= 64) {
            this.letterGrade = "C";
        } else if (score >= 50 && score <= 59) {
            this.letterGrade = "D";
        } else {
            this.letterGrade = "F";
        }
    }
}
