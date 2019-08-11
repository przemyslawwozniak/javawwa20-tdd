package pl.sda.javawwa20.tictactoe;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    int length = 3;
    int width = 3;
    Sign[] signs = new Sign[length * width];    //mozemy tu wpisac tylko X, O
    Sign allowedSign;   //przechowuje informacje o tym, kto teraz wykonuje ruch

    public static List<List<Integer>> winningSequences = new ArrayList<>();
    {
        //rows
        winningSequences.add(Arrays.asList(0, 1, 2));
        winningSequences.add(Arrays.asList(3, 4, 5));
        winningSequences.add(Arrays.asList(6, 7, 8));
        //columns
        winningSequences.add(Arrays.asList(0, 3, 6));
        winningSequences.add(Arrays.asList(1, 4, 7));
        winningSequences.add(Arrays.asList(2, 5, 8));
        //diagonals
        winningSequences.add(Arrays.asList(0, 4, 8));
        winningSequences.add(Arrays.asList(6, 4, 2));
    }

    public Board(Sign allowedSign) {
        this.allowedSign = allowedSign;
    }

    public Board(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public void assignSign(int squareNo, Sign sign) {
        //jesli nie jest tura przekazanego znaku, wyrzuca IllegalArgumentException
        Preconditions.checkArgument(squareNo >= 0 && squareNo < signs.length, "Cannot place sign outside of a board");
        Preconditions.checkArgument(signs[squareNo] == null, "Square %s is already taken", squareNo);
        Preconditions.checkArgument(sign.equals(allowedSign), "This is not %s turn", sign);

        //a jesli nie, to jedziemy dalej
        signs[squareNo] = sign;
        allowedSign = sign.other();
    }

    public void print() {
        System.out.println(getPrintableBoard());
    }

    /**
     * |O|X|2|\n
     * |3|X|5|\n
     * |6|O|8|\n
     * @return
     */
    public String getPrintableBoard() {
       StringBuilder stringBuilder = new StringBuilder();

       for(int row = 0; row < length; row++) {
           stringBuilder.append("|");
           for(int col = 0; col < width; col++) {
               final int squareNo = row * length + col;
               final Sign signOnSquare = signs[squareNo];
               //avoids calling .toName on null object
               if(signOnSquare != null)
                   stringBuilder.append(signOnSquare.name());
               else
                   stringBuilder.append(squareNo);

               stringBuilder.append("|");
           }
           stringBuilder.append("\n");
       }

       return stringBuilder.toString();
    }

    public boolean isWinningSign(Sign sign) {
        for(List<Integer> winningSequence : winningSequences) {
            boolean isWinner = false;
            for(int square : winningSequence) { //auto-unboxing: Integer -> int
                if(signs[square] != null) {
                    isWinner = signs[square].equals(sign);
                    if(!isWinner)
                        break;  //jesli ktorykolwiek ze znakow nie jest na pozycji, skoncz sprawdzanie tej sekwencji
                }
            }
            if(isWinner)
                return true;
        }
        return false;
    }

    public enum Sign {
        X {
            @Override
            Sign other() {
                return O;
            }
        },
        O {
            @Override
            Sign other() {
                return X;
            }
        };

        abstract Sign other();
    }
}
