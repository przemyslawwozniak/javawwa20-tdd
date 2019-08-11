package pl.sda.javawwa20.tictactoe;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

    public static void main(String[] args) {
        System.out.println("Witaj w grze 'Kolko i krzyzk'\n\n");
        Scanner sc = new Scanner(System.in);
        System.out.println("Wybierz rodzaj gry:\n(1) Gra dla pojedynczego gracza\n(2) Gra dla wielu graczy\n\n");
        int gameType = sc.nextInt();
        while(gameType != 1 && gameType != 2) {
            System.out.println("Mozesz wybrac tylko opcje (1) lub (2)! Ponow wybor.");
            gameType = sc.nextInt();
        }
        System.out.println("Wybrales " + gameType);

        if(gameType == 1)
            singlePlayer();
        else if(gameType == 2)
            multiPlayer();

    }

    public static void singlePlayer() {
        System.out.println("Rozpoczales gre z AI");
        System.out.println("Game type not supported - shutting down...");
    }

    public static void multiPlayer() {
        System.out.println("Rozpoczales gre z drugim graczem");
        Board.Sign startingSign = getStartingPlayer();
        Board board = new Board(startingSign);
        Player playerA = new Player(startingSign);
        Player playerB = new Player(startingSign.other());
        System.out.println("Gre zaczyna gracz " + startingSign.name());
        Board.Sign currentSign = startingSign;
        Scanner sc = new Scanner(System.in);

        boolean isWinner = false;   //zmienna trzyma info czy mamy wygranego
        int turnsPlayed = 0;    //zmienna trzyma info ile tur rozegrano
        do {
            System.out.println("Jaki jest Twoj ruch, graczu " + currentSign.name());
            boolean validPosition = false;
            do {
                board.print();
                int square = sc.nextInt();
                try {
                    //metoda pobiera gracza grajacego wskaznym znakiem
                    getPlayerBySign(playerA, playerB, currentSign).placeSign(square, board);
                    //jesli udalo postawic sie znak, oznaczamy jako prawidlowy ruch
                    validPosition = true;
                } catch (IllegalArgumentException ex) {
                    //nieprawidlowy ruch
                    System.out.println("Nie mozesz wybrac tej pozycji! Wybierz nowa pozycje!");
                }
            }
            while(!validPosition);  //wykonujesz ruch az wskarzesz prawidlowy ruch

            isWinner = board.isWinningSign(currentSign);
            currentSign = currentSign.other();
            turnsPlayed++;
        }
        while(!isWinner || turnsPlayed < 9);

        if(isWinner)
            System.out.println("Wygral gracz " + currentSign.other());
        else if(turnsPlayed > 8)
            System.out.println("Remis!");
    }

    public static Board.Sign getStartingPlayer() {
        if((new Random()).nextDouble() > 0.5)
            return Board.Sign.X;
        else
            return Board.Sign.O;
    }

    public static Player getPlayerBySign(Player playerA, Player playerB, Board.Sign currentSign) {
        return currentSign.equals(playerA.getSign()) ? playerA : playerB;
    }

}
