package pl.sda.javawwa20;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void newly_created_user_has_status_INITIALIZED() {
        User user = new User();
        assertThat(user.status).isEqualTo(User.UserStatus.INITIALIZED);
    }
    //INITIALIZED -> ACTIVATED
    @Test
    public void user_with_status_INITIALIZED_can_be_set_to_ACTIVATED() {
        User user = new User();
        user.setStatus(User.UserStatus.ACTIVATED);
        assertThat(user.status).isEqualTo(User.UserStatus.ACTIVATED);
    }
    //ACTIVATED -> DEACTIVATED
    @Test
    public void user_with_status_ACTIVATED_can_be_set_to_DEACTIVATED() {
        User user = new User();
        user.setStatus(User.UserStatus.ACTIVATED);
        user.setStatus(User.UserStatus.DEACTIVATED);
        assertThat(user.status).isEqualTo(User.UserStatus.DEACTIVATED);
    }

    @Test(expected = IllegalArgumentException.class)
    public void user_with_status_ACTIVATED_cannot_be_set_to_INITIALIZED() {
        User user = new User();
        user.setStatus(User.UserStatus.ACTIVATED);
        user.setStatus(User.UserStatus.INITIALIZED);
    }

    //DEACTIVATED -> ACTIVATED
    @Test
    public void user_with_status_DEACTIVATED_can_be_set_to_ACTIVATED() {
        User user = new User();
        user.setStatus(User.UserStatus.ACTIVATED);
        user.setStatus(User.UserStatus.DEACTIVATED);
        user.setStatus(User.UserStatus.ACTIVATED);
        assertThat(user.status).isEqualTo(User.UserStatus.ACTIVATED);
    }

    //DEACTIVATED -> DEACTIVATED
    @Test(expected = IllegalArgumentException.class)
    public void user_with_status_DEACTIVATED_cannot_be_set_to_DEACTIVATED() {
        User user = new User();
        user.setStatus(User.UserStatus.ACTIVATED);
        user.setStatus(User.UserStatus.DEACTIVATED);
        user.setStatus(User.UserStatus.DEACTIVATED);
    }

}
