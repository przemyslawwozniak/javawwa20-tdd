package pl.sda.javawwa20;

public class ProductRating {

    private short score;
    //private String s;

    public ProductRating() {
        //s is not initialized - NullPointerException
        //s.toLowerCase();
        this.score = 1;
    }

    //alt + insert -> getter and setter
    public short getScore() {
        return score;
    }

    //musi wyrzucac wyjatek
    public void setScore(short score) {
        if(score < 1 || score > 10) {
            throw new IllegalArgumentException();
        }

        this.score = score;
    }
}
