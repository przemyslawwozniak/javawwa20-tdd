package pl.sda.javawwa20.tictactoe;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

public class TicTacToeTest {

    @Test
    public void board_is_3_by_3() {
        //when
        Board board = new Board(Board.Sign.X);
        //then
        Assertions.assertThat(board.length).isEqualTo(3);
        Assertions.assertThat(board.width).isEqualTo(3);
        Assertions.assertThat(board.signs.length).isEqualTo(9);
        Assertions.assertThat(board.signs).containsOnlyNulls();
    }

    @Test
    public void player_can_place_a_sign_on_an_untaken_square_on_the_board() {
        //given
        Board board = new Board(Board.Sign.X);
        Player player = new Player(Board.Sign.X);
        //when:
        player.placeSign(4, board);
        //then:
        Assertions.assertThat(board.signs[4]).isNotNull();
        Assertions.assertThat(board.signs[4]).isEqualTo(player.getSign());
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void player_cannot_place_a_sign_on_already_taken_square_on_the_board() {
        //given
        Board board = new Board(Board.Sign.X);
        Player playerX = new Player(Board.Sign.X);
        Player playerO = new Player(Board.Sign.O);
        //when
        playerX.placeSign(4, board);
        playerO.placeSign(4, board);
        //then throws an exception
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void player_cannot_place_a_sign_if_its_not_his_turn() {
        //given
        Board board = new Board(Board.Sign.X);
        Player playerX = new Player(Board.Sign.X);
        //when
        playerX.placeSign(4, board);
        playerX.placeSign(4, board);
        //then throws an exception
    }

    @Test
    public void player_can_place_a_sign_if_its_his_turn() {
        //given
        Board board = new Board(Board.Sign.X);
        Player playerX = new Player(Board.Sign.X);
        Player playerO = new Player(Board.Sign.O);
        //when
        playerX.placeSign(4, board);
        playerO.placeSign(0, board);
        //then
        Assertions.assertThat(board.signs[4]).isNotNull();
        Assertions.assertThat(board.signs[4]).isEqualTo(playerX.getSign());
        Assertions.assertThat(board.signs[0]).isNotNull();
        Assertions.assertThat(board.signs[0]).isEqualTo(playerO.getSign());
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void player_cannot_place_sign_outside_of_board() {
        //given
        Board board = new Board(Board.Sign.X);
        Player playerX = new Player(Board.Sign.X);
        //when
        playerX.placeSign(15, board);
        //then throws an exception
    }

    @Test(expectedExceptions = {IllegalArgumentException.class})
    public void player_cannot_start_a_game_if_its_not_his_turn() {
        //given
        Board board = new Board(Board.Sign.X);
        Player playerO = new Player(Board.Sign.O);
        //when
        playerO.placeSign(0, board);
        //then throws an exception
    }

    /**
     * |O|X|2|
     * |3|X|5|
     * |6|O|8|
     */
    @Test
    public void prints_board() {
        //given
        Board board = new Board(Board.Sign.X);
        Player playerX = new Player(Board.Sign.X);
        Player playerO = new Player(Board.Sign.O);
        //when
        playerX.placeSign(4, board);
        playerO.placeSign(0, board);
        playerX.placeSign(1, board);
        playerO.placeSign(7, board);

        board.print();
        String printableBoard = board.getPrintableBoard();

        //then
        Assertions.assertThat(printableBoard).isEqualTo(
                "|O|X|2|\n" +
                "|3|X|5|\n" +
                "|6|O|8|\n");
    }

    //camel case notation example
/*    @Test
    public void playerCannotPlaceSignOutsideOfBoard() {

    }*/



}
