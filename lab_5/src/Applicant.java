public class Applicant {
    private String lastName;
    private String firstName;
    private int score1;
    private int score2;

    public Applicant(String lastName, String firstName, int score1, int score2) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.score1 = score1;
        this.score2 = score2;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getScore1() {
        return score1;
    }

    public int getScore2() {
        return score2;
    }

    public boolean isFailed() {
        return score1 < 30 || score2 < 30;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " (" + score1 + ", " + score2 + ")";
    }
}