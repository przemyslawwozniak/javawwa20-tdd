package pl.sda.javawwa20.tictactoe;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class TicTacToeTest {

    @Test
    public void board_is_3_by_3() {
        //when
        Board board = new Board();
        //then
        Assertions.assertThat(board.length).isEqualTo(3);
        Assertions.assertThat(board.width).isEqualTo(3);
        Assertions.assertThat(board.signs.length).isEqualTo(9);
        Assertions.assertThat(board.signs).containsOnlyNulls();
    }

    @Test
    public void player_can_place_a_sign_on_an_untaken_square_on_the_board() {
        //given
        Board board = new Board();
        Player player = new Player(Board.Sign.X);
        //when:
        player.placeSign(4, board);
        //then:
        Assertions.assertThat(board.signs[4]).isNotNull();
        Assertions.assertThat(board.signs[4]).isEqualTo(player.getSign());
    }

}
