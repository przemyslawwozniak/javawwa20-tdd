package pl.sda.javawwa20;

import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.time.LocalDateTime;

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

    

}
