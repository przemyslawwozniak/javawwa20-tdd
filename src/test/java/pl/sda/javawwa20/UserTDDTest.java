package pl.sda.javawwa20;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class UserTDDTest {

    @Test
    public void creates_user_with_initialized_properties() {
        //when
        UserTDD user = new UserTDD();
        //then
        Assertions.assertThat(user.getRegisteredDate()).isBetween(
                LocalDateTime.now().minusSeconds(1), LocalDateTime.now().plusSeconds(1));
        Assertions.assertThat(user.getStatus()).isEqualTo(UserTDD.UserTDDStatus.INITIALIZED);
    }

    @Test
    public void calcs_time_between_registered_date_and_now() {
        //given
        UserTDD user = new UserTDD();
        LocalDateTime userRegisteredDate = user.getRegisteredDate();
        //when
        Period timeSinceRegistered = user.calcTimeSinceRegistered();
        //then
        Assertions.assertThat(timeSinceRegistered).isEqualTo(
                Period.between(userRegisteredDate.toLocalDate(), LocalDate.now()));
    }

    @Test
    public void calcs_time_between_last_logged_in_date_and_now() {
        //given
        UserTDD user = new UserTDD();
        user.login();
        LocalDateTime userLastLoggedInDate = user.getLastLoggedInDate();
        //when
        //korzysta z LocalDateTime.now() ktore jest inne (o ns) juz w kolejnej linijce!
        Duration timeSinceLastLoggedIn = user.calcTimeSinceLastLoggedIn();
        //then
        Assertions.assertThat(timeSinceLastLoggedIn).isBetween(
                Duration.between(userLastLoggedInDate, LocalDateTime.now()).minusSeconds(1),
                Duration.between(userLastLoggedInDate, LocalDateTime.now()).plusSeconds(1));
    }

}
