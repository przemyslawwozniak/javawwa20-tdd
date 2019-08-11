package pl.sda.javawwa20.tictactoe;

public class Board {
    int length = 3;
    int width = 3;
    Sign[] signs = new Sign[length * width];    //mozemy tu wpisac tylko X, O

    public Board() {
    }

    public Board(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public enum Sign {
        X,
        O
    }
}
