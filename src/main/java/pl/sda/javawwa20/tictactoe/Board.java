package pl.sda.javawwa20.tictactoe;

import com.google.common.base.Preconditions;

public class Board {
    int length = 3;
    int width = 3;
    Sign[] signs = new Sign[length * width];    //mozemy tu wpisac tylko X, O
    Sign allowedSign;   //przechowuje informacje o tym, kto teraz wykonuje ruch

    public Board(Sign allowedSign) {
        this.allowedSign = allowedSign;
    }

    public Board(int length, int width) {
        this.length = length;
        this.width = width;
    }

    public void assignSign(int squareNo, Sign sign) {
        //jesli nie jest tura przekazanego znaku, wyrzuca IllegalArgumentException
        Preconditions.checkArgument(signs[squareNo] == null, "Square %s is already taken", squareNo);
        Preconditions.checkArgument(sign.equals(allowedSign), "This is not %s turn", sign);

        //a jesli nie, to jedziemy dalej
        signs[squareNo] = sign;
        allowedSign = sign.other();
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
