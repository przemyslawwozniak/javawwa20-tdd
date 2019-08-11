package pl.sda.javawwa20.tictactoe;

public class Player {

    Board.Sign sign;

    public Player(Board.Sign sign) {
        this.sign = sign;
    }

    public void placeSign(int squareNo, Board board) {
        board.assignSign(squareNo, sign);
    }

    public Board.Sign getSign() {
        return sign;
    }

}
