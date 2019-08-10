package pl.sda.javawwa20;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static pl.sda.javawwa20.User.UserStatus.ACTIVATED;
import static pl.sda.javawwa20.User.UserStatus.DEACTIVATED;
import static pl.sda.javawwa20.User.UserStatus.INITIALIZED;

public class User {

    String username;
    UserStatus status;
    LocalDateTime lastLoggedIn;
    LocalDateTime registeredDate;
    public static Map<UserStatus, UserStatus> ALLOWED_STATUS_TRANSITIONS;

    //static initialization block
    //zostanie wywolany przed utworzeniem klasy
    //mapa mozliwych przejsc
    //INITIALIZED -> ACTIVATED
    //ACTIVATED -> DEACTIVATED
    //DEACTIVATED -> ACTIVATED
    {
        ALLOWED_STATUS_TRANSITIONS = new HashMap<>();
        ALLOWED_STATUS_TRANSITIONS.put(INITIALIZED, ACTIVATED);
        //ALLOWED_STATUS_TRANSITIONS.put(INITIALIZED, DEACTIVATED); //spowoduje nadpisanie klucza
        ALLOWED_STATUS_TRANSITIONS.put(ACTIVATED, DEACTIVATED);
        ALLOWED_STATUS_TRANSITIONS.put(DEACTIVATED, ACTIVATED);
    }

    public enum UserStatus {
        INITIALIZED,
        ACTIVATED,
        DEACTIVATED
    }

    //uzytkownik jest tworzony ze statusem INITIALIZED
    public User() {
        this.status = INITIALIZED;
    }

    public void setStatus(UserStatus status) {
        if(ALLOWED_STATUS_TRANSITIONS.get(this.status).equals(status)) {
            this.status = status;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

}
