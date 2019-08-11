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

    @Test
    public void prints_last_logged_in_text() {
        //given
        UserTDD user = new UserTDD("PWozniak92");
        user.login();
        //when
        String lastLoggedInText = user.getLastLoggedInText();
        //format tekstu: username@2019-08-11T10:20:08.858
        user.printLastLoggedInText();
        //then
        Assertions.assertThat(lastLoggedInText).matches(
                ".*@\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d\\d\\.\\d{3}");
    }

    @Test
    public void changes_user_status_from_INITIALIZED_to_ACTIVATED() {
        //given user in INITIALIZED state
        UserTDD userTDD = new UserTDD();
        //when
        userTDD.switchState(UserTDD.UserTDDStatus.ACTIVATED);
        //then
        Assertions.assertThat(userTDD.getStatus()).isEqualTo(UserTDD.UserTDDStatus.ACTIVATED);
    }

    @Test
    public void changes_user_status_from_ACTIVATED_to_DEACTIVATED() {
        //given user in ACTIVATED state
        UserTDD userTDD = new UserTDD();
        userTDD.switchState(UserTDD.UserTDDStatus.ACTIVATED);
        //when
        userTDD.switchState(UserTDD.UserTDDStatus.DEACTIVATED);
        //then
        Assertions.assertThat(userTDD.getStatus()).isEqualTo(UserTDD.UserTDDStatus.DEACTIVATED);
    }

    @Test
    public void changes_user_status_from_DEACTIVATED_to_ACTIVATED() {
        //given user in DEACTIVATED state
        UserTDD userTDD = new UserTDD();
        userTDD.switchState(UserTDD.UserTDDStatus.ACTIVATED);
        userTDD.switchState(UserTDD.UserTDDStatus.DEACTIVATED);
        //when
        userTDD.switchState(UserTDD.UserTDDStatus.ACTIVATED);
        //then
        Assertions.assertThat(userTDD.getStatus()).isEqualTo(UserTDD.UserTDDStatus.ACTIVATED);
    }

    @Test(expectedExceptions = {StatusTransitionNotPossibleException.class})
    public void cannot_change_user_status_from_INITIALIZED_to_DEACTIVATED() {
        //given user in INITIALIZED state
        UserTDD userTDD = new UserTDD();
        //when
        userTDD.switchState(UserTDD.UserTDDStatus.DEACTIVATED);
        //then exception is thrown
    }

    //activated -> initialized
    @Test(expectedExceptions = {StatusTransitionNotPossibleException.class})
    public void cannot_change_user_status_from_ACTIVATED_to_INITIALIZED() {
        //given user in ACTIVATED state
        UserTDD userTDD = new UserTDD();
        userTDD.switchState(UserTDD.UserTDDStatus.ACTIVATED);
        //when
        userTDD.switchState(UserTDD.UserTDDStatus.INITIALIZED);
        //then exception is thrown
    }

    //deactivated -> initialized
    @Test(expectedExceptions = {StatusTransitionNotPossibleException.class})
    public void cannot_change_user_status_from_DEACTIVATED_to_INITIALIZED() {
        //given user in DEACTIVATED state
        UserTDD userTDD = new UserTDD();
        userTDD.switchState(UserTDD.UserTDDStatus.ACTIVATED);
        userTDD.switchState(UserTDD.UserTDDStatus.DEACTIVATED);
        //when
        userTDD.switchState(UserTDD.UserTDDStatus.INITIALIZED);
        //then exception is thrown
    }
}
